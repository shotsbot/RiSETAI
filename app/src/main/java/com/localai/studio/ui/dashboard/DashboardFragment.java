package com.localai.studio.ui.dashboard;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.localai.studio.R;

public class DashboardFragment extends Fragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) requireContext().getSystemService(Context.ACTIVITY_SERVICE);
        am.getMemoryInfo(mi);
        ((TextView)v.findViewById(R.id.statusText)).setText("RAM avail: " + (mi.availMem / (1024*1024)) + "MB\nBackend: llama.cpp (CPU fallback)");
        return v;
    }
}
