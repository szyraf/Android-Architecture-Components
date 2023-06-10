package com.example.viewbindingapp6_001.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.viewbindingapp6_001.model.User;

@androidx.room.Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase roomDatabase;
    private static final String DATABASE_NAME = "usersDB";

    public static synchronized UserDatabase getInstance(Context context){
        if(roomDatabase == null) {
            roomDatabase = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDatabase;
    }

    public abstract UserDAO userDAO();

}
