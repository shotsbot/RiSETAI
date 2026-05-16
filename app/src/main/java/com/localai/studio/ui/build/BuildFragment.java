package com.localai.studio.ui.build;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.localai.studio.R;
import com.localai.studio.build.BuildLogParser;
import com.localai.studio.build.GradleBuildRunner;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BuildFragment extends Fragment {
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private volatile boolean cancelled = false;

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle s) {
        View v = i.inflate(R.layout.fragment_build, c, false);
        TextView log = v.findViewById(R.id.buildLog);
        Button run = v.findViewById(R.id.btnAssembleDebug);
        Button clean = v.findViewById(R.id.btnClean);
        Button cancel = v.findViewById(R.id.btnCancel);
        BuildLogParser parser = new BuildLogParser();

        run.setOnClickListener(x -> runTask(log, parser, "assembleDebug"));
        clean.setOnClickListener(x -> runTask(log, parser, "clean"));
        cancel.setOnClickListener(x -> cancelled = true);
        return v;
    }

    private void runTask(TextView log, BuildLogParser parser, String task) {
        cancelled = false;
        log.setText("");
        exec.execute(() -> {
            try {
                File projectDir = new File(requireContext().getFilesDir(), "sample-project");
                if (!projectDir.exists()) {
                    requireActivity().runOnUiThread(() -> appendLog(
                            log,
                            "Error: Project directory not found at " + projectDir.getAbsolutePath(),
                            true
                    ));
                    return;
                }
                int code = new GradleBuildRunner().run(projectDir, task, line -> {
                    if (cancelled) return;
                    requireActivity().runOnUiThread(() -> appendLog(log, line, parser.isErrorLine(line)));
                });
                requireActivity().runOnUiThread(() -> appendLog(log, "Exit code: " + code, code != 0));
            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> appendLog(log, "Error: " + e.getMessage(), true));
            }
        });
    }

    private void appendLog(TextView tv, String line, boolean error) {
        SpannableStringBuilder sb = new SpannableStringBuilder(line + "\n");
        if (error) {
            sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(requireContext(), android.R.color.holo_red_light)), 0, sb.length(), 0);
        }
        tv.append(sb);
    }
}
