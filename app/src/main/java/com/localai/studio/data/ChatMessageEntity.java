package com.localai.studio.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat_messages")
public class ChatMessageEntity {
    @PrimaryKey(autoGenerate = true) public long id;
    public String role;
    public String content;
    public long createdAt;
}
