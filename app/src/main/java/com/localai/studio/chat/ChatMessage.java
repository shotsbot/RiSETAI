package com.localai.studio.chat;

public class ChatMessage {
    public final String role;
    public String content;
    public ChatMessage(String role, String content) { this.role = role; this.content = content; }
}
