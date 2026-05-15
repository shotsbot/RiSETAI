package com.localai.studio.ui.build;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.localai.studio.R;
import com.localai.studio.build.GradleBuildRunner;
import java.io.File;
import java.util.concurrent.Executors;

public class BuildFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle s) {
        View v = i.inflate(R.layout.fragment_build, c, false);
        TextView log = v.findViewById(R.id.buildLog);
        Button run = v.findViewById(R.id.btnAssembleDebug);

        run.setOnClickListener(x -> Executors.newSingleThreadExecutor().execute(() -> {
            try {
                File projectDir = new File("/workspace/RiSETAI/sample-project");
                int code = new GradleBuildRunner().run(projectDir, "assembleDebug", line ->
                        requireActivity().runOnUiThread(() -> log.append(line + "\n")));
                requireActivity().runOnUiThread(() -> log.append("\nExit code: " + code));
            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> log.append("\nError: " + e.getMessage()));
            }
        }));

        return v;
    }
}
