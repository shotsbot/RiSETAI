package com.localai.studio.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.localai.studio.R;
import com.localai.studio.ui.build.BuildFragment;
import com.localai.studio.ui.chat.ChatFragment;
import com.localai.studio.ui.dashboard.DashboardFragment;
import com.localai.studio.ui.editor.EditorFragment;
import com.localai.studio.ui.model.ModelManagerFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottomNav);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_dashboard) show(new DashboardFragment());
            else if (id == R.id.menu_chat) show(new ChatFragment());
            else if (id == R.id.menu_editor) show(new EditorFragment());
            else if (id == R.id.menu_model) show(new ModelManagerFragment());
            else if (id == R.id.menu_build) show(new BuildFragment());
            return true;
        });

        if (savedInstanceState == null) {
            nav.setSelectedItemId(R.id.menu_dashboard);
        }
    }

    private void show(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
