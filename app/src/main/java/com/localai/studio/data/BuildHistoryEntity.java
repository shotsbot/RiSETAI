package com.localai.studio.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "build_history")
public class BuildHistoryEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String projectPath;
    public String task;
    public int exitCode;
    public String startedAtIso;
    public String finishedAtIso;
    public String outputApkPath;
}
