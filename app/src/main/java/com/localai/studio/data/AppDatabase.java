package com.localai.studio.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ModelEntity.class, ChatMessageEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ModelDao modelDao();
}
