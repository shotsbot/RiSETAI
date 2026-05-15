package com.localai.studio.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ModelDao {
    @Insert void insert(ModelEntity model);
    @Query("SELECT * FROM models") List<ModelEntity> all();
}
