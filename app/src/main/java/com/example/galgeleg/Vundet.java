package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Vundet extends AppCompatActivity {
    galgeleg.Galgelogik logik;
    TextView txtForsøg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);
        txtForsøg = findViewById(R.id.antalForsøg);
        txtForsøg.setText("antal forsøg: " + Integer.toString(logik.getAntalForkerteBogstaver()));
    }
}