package com.example.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.DogBreed;

import java.util.List;

@Dao
public interface DogDAO {
    @Query("SELECT * FROM DogBreed")
    List<DogBreed> getAllContacts();

    @Insert
    void insertAll(DogBreed... dogBreeds);

    @Update
    void update(DogBreed contact);

    @Query("DELETE FROM DogBreed")
    void deteleAll();
}
