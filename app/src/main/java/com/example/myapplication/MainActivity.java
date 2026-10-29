package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SzkolaDatabase szkolaDatabase;
    Button button, buttonAktualizacja;
    EditText editText;
    Spinner spinner;
    ListView listViewNauczyciele;
    List<Nauczyciel> nauczycielList;
    ArrayAdapter<Nauczyciel>  arrayAdapter;
    Nauczyciel kliknietyNauczyciel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        szkolaDatabase = Room.databaseBuilder(
                getApplicationContext(),SzkolaDatabase.class,"szkola_db")
                .allowMainThreadQueries().build();

       // szkolaDatabase.get_NauczycielDAO().wstaw_Nauczyciela_Do_Bazy(
         //       new Nauczyciel("Anna","matematyka"));
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        spinner = findViewById(R.id.spinner);
        listViewNauczyciele = findViewById(R.id.ListWiev);
        nauczycielList = szkolaDatabase.get_NauczycielDAO().zwroc_wszytkich_nauczycieli_z_bazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, nauczycielList);
        listViewNauczyciele.setAdapter(arrayAdapter);
        buttonAktualizacja = findViewById(R.id.buttonaktualizuj);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nauczyciel nauczyciel = new Nauczyciel(
                        editText.getText().toString(),
                        spinner.getSelectedItem().toString()
                );
                szkolaDatabase.get_NauczycielDAO().wstaw_Nauczyciela_Do_Bazy(nauczyciel);
//                nauczycielList = szkolaDatabase.get_NauczycielDAO().zwroc_wszytkich_nauczycieli_z_bazy();
                nauczycielList.add(nauczyciel);
                arrayAdapter.notifyDataSetChanged();

            }
        });
        listViewNauczyciele.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        szkolaDatabase.get_NauczycielDAO().ususn_Nauczyciela_z_Bazy(nauczycielList.get(i));
                        nauczycielList.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );
        listViewNauczyciele.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        kliknietyNauczyciel = nauczycielList.get(i);
                        //wypelniony formularz
                        editText.setText(kliknietyNauczyciel.imie);
                        ArrayList<String> przedmioty = new ArrayList<>(
                                Arrays.asList(
                                        "wf",
                                        "informatyka",
                                        "programowanie",
                                        "fizyka",
                                        "język angielski",
                                        "język chiński"
                                )
                        );
                        spinner.setSelection(przedmioty.indexOf(kliknietyNauczyciel.przedmiot));
                        buttonAktualizacja.setVisibility(view.VISIBLE);
                    }

                }

        );
//        buttonAktualizacja.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        szkolaDatabase.get_NauczycielDAO().aktualizuj_tabele(
//
//                        );
//                    }
//                }
//        );

    }

}