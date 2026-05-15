package com.localai.studio.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ModelDownloadManifestDao {
    @Insert
    long insert(ModelDownloadManifestEntity entity);

    @Query("SELECT * FROM model_download_manifest ORDER BY installedAt DESC")
    List<ModelDownloadManifestEntity> all();
}
