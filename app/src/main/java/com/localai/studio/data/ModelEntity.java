package com.localai.studio.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "models")
public class ModelEntity {
    @PrimaryKey(autoGenerate = true) public int id;
    public String name;
    public String repo;
    public String format;
    public String recommendedQuant;
    public String localPath;
    public String backend;
}
