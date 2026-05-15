package com.localai.studio.ui.editor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.localai.studio.R;

public class EditorFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle s) {
        View v = i.inflate(R.layout.fragment_editor, c, false);
        EditText code = v.findViewById(R.id.editorText);
        TextView diff = v.findViewById(R.id.diffPreview);
        Button explain = v.findViewById(R.id.btnExplain);
        Button patch = v.findViewById(R.id.btnPatch);
        explain.setOnClickListener(x -> diff.setText("Explain: Pilih kode -> AI menjelaskan konteks, bug potensial, dan optimasi."));
        patch.setOnClickListener(x -> diff.setText("--- a/File.java\n+++ b/File.java\n@@\n- old\n+ new // patch AI (preview only)"));
        return v;
    }
}
