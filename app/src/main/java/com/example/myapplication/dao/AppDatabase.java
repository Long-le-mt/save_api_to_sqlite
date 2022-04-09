package com.example.myapplication.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.helper.Converters;
import com.example.myapplication.model.DogBreed;

@Database(entities = {DogBreed.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase  extends RoomDatabase {
    private static Object Converters;

    public abstract DogDAO dogDAO();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "DogBreed1").allowMainThreadQueries().build();
        }

        return instance;
    }
}
