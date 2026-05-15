package com.localai.studio.ui.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.localai.studio.R;
import com.localai.studio.model.ModelCatalogItem;
import com.localai.studio.model.ModelCatalogRepository;
import java.util.Collections;
import java.util.List;

public class ModelManagerFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle s) {
        View v = i.inflate(R.layout.fragment_model_manager, c, false);
        RecyclerView rv = v.findViewById(R.id.modelList);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        TextView warning = v.findViewById(R.id.modelWarning);
        warning.setText("Prioritas Q4_K_M. Model 7B/8B: bisa jalan di RAM 8 GB tapi lambat.");
        try {
            String json = new ModelCatalogRepository(requireContext()).loadCatalogJson();
            List<ModelCatalogItem> models = new Gson().fromJson(json, new TypeToken<List<ModelCatalogItem>>(){}.getType());
            rv.setAdapter(new ModelAdapter(models));
        } catch (Exception e) {
            rv.setAdapter(new ModelAdapter(Collections.emptyList()));
        }
        return v;
    }
}
