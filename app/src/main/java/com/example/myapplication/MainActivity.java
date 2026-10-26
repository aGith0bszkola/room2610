package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    SzkolaDatabase szkolaDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        szkolaDatabase = Room.databaseBuilder(
                getApplicationContext(),SzkolaDatabase.class,"szkola_db")
                .allowMainThreadQueries().build();

        szkolaDatabase.get_NauczycielDAO().wstaw_Nauczyciela_Do_Bazy(
                new Nauczyciel("Anna","matematyka"));
    }
}