package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Tabt extends AppCompatActivity {
    galgeleg.Galgelogik logik;
    TextView tabtOrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt);
        tabtOrd = findViewById(R.id.tabtOrd);
        tabtOrd.setText(logik.getOrdet());
    }
}