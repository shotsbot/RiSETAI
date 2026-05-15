package com.localai.studio.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.localai.studio.R;
import com.localai.studio.chat.ChatMessage;
import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.VH> {
    private final List<ChatMessage> data;
    public ChatMessageAdapter(List<ChatMessage> data) { this.data = data; }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new VH(v);
    }
    @Override public void onBindViewHolder(@NonNull VH h, int p) {
        ChatMessage m = data.get(p);
        h.role.setText(m.role);
        h.content.setText(m.content);
    }
    @Override public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView role, content;
        VH(View itemView) { super(itemView); role = itemView.findViewById(R.id.msgRole); content = itemView.findViewById(R.id.msgText); }
    }
}
