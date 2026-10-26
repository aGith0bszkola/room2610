package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Nauczyciel_Dao {
    @Insert
    void wstaw_Nauczyciela_Do_Bazy(Nauczyciel nauczyciel);

    @Insert
    void wstaw_liste_nauczycieli(Nauczyciel ... nauczyciele);

    @Delete
    void ususn_Nauczyciela_z_Bazy(Nauczyciel nauczyciel);

    @Update
    void aktualizuj_tabele(Nauczyciel nauczyciel);

    @Query("SELECT * from nauczyciele")
    List<Nauczyciel> zwroc_wszytkich_nauczycieli_z_bazy(Nauczyciel nauczyciel);

    @Query("SELECT imie from nauczyciele WHERE przedmiot = :przedmiotArgument")
    List<String> zwroc_Imion_nauczycieli_ZPrzedmiotu(String przedmiotArgument);

}
