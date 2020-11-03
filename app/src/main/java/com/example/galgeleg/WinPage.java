package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinPage extends AppCompatActivity implements View.OnClickListener {

    TextView amountOfTries;
    Button playWon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet2);
        Intent intent = getIntent();
        amountOfTries = findViewById(R.id.txtTries);
        amountOfTries.setText("Du har brugt " + intent.getStringExtra("amountTries") + " forsøg.");
        playWon = findViewById(R.id.buttonWon);
        playWon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent screen = new Intent(this, GameStart.class);
        startActivity(screen);
        finish();
    }
}