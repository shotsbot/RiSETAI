package com.localai.studio.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "model_download_manifest")
public class ModelDownloadManifestEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String modelId;
    public String repo;
    public String filename;
    public long sizeBytes;
    public String sha256;
    public String localPath;
    public String backend;
    public long installedAt;
}
