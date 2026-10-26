package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Nauczyciel.class}, version = 1)
public abstract class SzkolaDatabase extends RoomDatabase {
    public abstract Nauczyciel_Dao get_NauczycielDAO();

}
