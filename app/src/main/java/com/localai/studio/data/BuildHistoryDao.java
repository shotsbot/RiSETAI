package com.localai.studio.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface BuildHistoryDao {
    @Insert
    long insert(BuildHistoryEntity entity);

    @Query("SELECT * FROM build_history ORDER BY id DESC")
    List<BuildHistoryEntity> all();
}
