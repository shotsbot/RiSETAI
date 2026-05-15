package com.localai.studio.ui.chat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.localai.studio.R;
import com.localai.studio.chat.ChatMessage;
import com.localai.studio.chat.ChatRepository;
import com.localai.studio.engine.LlamaCppEngine;

public class ChatFragment extends Fragment {
    private final ChatRepository repo = new ChatRepository();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        RecyclerView recycler = view.findViewById(R.id.chatList);
        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        ChatMessageAdapter adapter = new ChatMessageAdapter(repo.all());
        recycler.setAdapter(adapter);
        EditText input = view.findViewById(R.id.inputPrompt);
        Button send = view.findViewById(R.id.sendBtn);

        send.setOnClickListener(v -> {
            String prompt = input.getText().toString().trim();
            if (prompt.isEmpty()) return;
            repo.add(new ChatMessage("user", prompt));
            ChatMessage assistant = new ChatMessage("assistant", "");
            repo.add(assistant);
            adapter.notifyDataSetChanged();
            input.setText("");

            LlamaCppEngine engine = new LlamaCppEngine();
            engine.load("mock.gguf");
            String response = engine.generate(prompt, 256);
            streamMock(assistant, response, adapter);
            engine.close();
        });
        return view;
    }

    private void streamMock(ChatMessage msg, String response, ChatMessageAdapter adapter) {
        final int[] idx = {0};
        Runnable r = new Runnable() {
            @Override public void run() {
                if (idx[0] < response.length()) {
                    msg.content += response.charAt(idx[0]++);
                    adapter.notifyDataSetChanged();
                    handler.postDelayed(this, 12);
                }
            }
        };
        handler.post(r);
    }
}
