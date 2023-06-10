package com.example.viewbindingapp6_001.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.viewbindingapp6_001.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertOne(User user);

    @Query("SELECT * FROM userTBL")
    List<User> getAll();

    @Insert
    void insertAll(User... users);

    @Query("DELETE FROM userTBL")
    void deleteAll();

    @Delete
    void delete(User user);
}
