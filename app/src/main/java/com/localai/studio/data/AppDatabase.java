package com.localai.studio.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {
                ModelEntity.class,
                ChatMessageEntity.class,
                ModelDownloadManifestEntity.class,
                BuildHistoryEntity.class
        },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ModelDao modelDao();
    public abstract ModelDownloadManifestDao modelDownloadManifestDao();
    public abstract BuildHistoryDao buildHistoryDao();
}
