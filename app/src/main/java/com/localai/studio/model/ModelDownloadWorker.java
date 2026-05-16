package com.localai.studio.model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.io.File;
import java.io.RandomAccessFile;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ModelDownloadWorker extends Worker {
    public static final String KEY_URL = "url";
    public static final String KEY_FILENAME = "filename";

    public ModelDownloadWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        String url = getInputData().getString(KEY_URL);
        String filename = getInputData().getString(KEY_FILENAME);
        if (url == null || filename == null) {
            return Result.failure(new Data.Builder().putString("error", "Missing url/filename").build());
        }

        File modelDir = new File(getApplicationContext().getFilesDir(), "models");
        if (!modelDir.exists() && !modelDir.mkdirs()) {
            return Result.failure(new Data.Builder().putString("error", "Cannot create model directory").build());
        }

        File target = new File(modelDir, filename);
        long existing = target.exists() ? target.length() : 0L;

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request.Builder reqBuilder = new Request.Builder().url(url);
        if (existing > 0) {
            reqBuilder.addHeader("Range", "bytes=" + existing + "-");
        }

        try (Response response = client.newCall(reqBuilder.build()).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                return Result.retry();
            }
            boolean resuming = existing > 0;
            if (resuming && response.code() != 206) {
                if (response.code() == 200) {
                    existing = 0L;
                } else {
                    return Result.retry();
                }
            }
            try (RandomAccessFile out = new RandomAccessFile(target, "rw")) {
                if (existing > 0) {
                    out.seek(existing);
                } else {
                    out.setLength(0);
                }
                byte[] buffer = new byte[8192];
                int read;
                long total = existing;
                while ((read = response.body().byteStream().read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                    total += read;
                    setProgressAsync(new Data.Builder().putLong("downloaded", total).build());
                }
                return Result.success(new Data.Builder().putString("local_path", target.getAbsolutePath()).build());
            }
        } catch (Exception e) {
            return Result.retry();
        }
    }
}
