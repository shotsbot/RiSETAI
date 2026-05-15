package com.localai.studio.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatRepository {
    private final List<ChatMessage> messages = new ArrayList<>();
    public List<ChatMessage> all() { return messages; }
    public void add(ChatMessage message) { messages.add(message); }
}
