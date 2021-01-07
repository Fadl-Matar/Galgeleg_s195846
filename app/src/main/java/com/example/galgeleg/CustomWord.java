package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.galgeleg.GalgeLogik.Galgelogik;

import java.util.ArrayList;
import java.util.List;

public class CustomWord extends AppCompatActivity implements AdapterView.OnItemClickListener {



    String[] words = {"hus", "bil", "computer", "telefon", "seng", "kop", "ord fra DR"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_word);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, words);
        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        setContentView(listView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String wordTransfer = parent.getItemAtPosition(position).toString();
        Intent transfer = new Intent(this, GameStart.class);
        transfer.putExtra("ListViewValue", wordTransfer);
        startActivity(transfer);
    }
}