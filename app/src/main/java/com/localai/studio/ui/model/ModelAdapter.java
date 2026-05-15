package com.localai.studio.ui.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.localai.studio.R;
import com.localai.studio.model.ModelCatalogItem;
import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.VH> {
    public interface Listener { void onDownload(ModelCatalogItem item); }
    private final List<ModelCatalogItem> items;
    private final Listener listener;

    public ModelAdapter(List<ModelCatalogItem> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int vt) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull VH h, int p) {
        ModelCatalogItem m = items.get(p);
        h.name.setText(m.name + " (" + m.format + ")");
        String heavy = m.isHeavyFor8Gb() ? "\n⚠ Cocok terbatas untuk RAM 8GB" : "\n✅ Cocok untuk RAM 8GB";
        h.meta.setText(m.repo + "\n" + m.recommended_quant + "\n" + m.use_case + heavy);
        h.download.setOnClickListener(v -> listener.onDownload(m));
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, meta;
        Button download;
        VH(View v){
            super(v);
            name = v.findViewById(R.id.modelName);
            meta = v.findViewById(R.id.modelMeta);
            download = v.findViewById(R.id.btnDownloadModel);
        }
    }
}
