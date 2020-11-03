package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startKnap, hjælpKnap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startKnap = findViewById(R.id.start);
        hjælpKnap = findViewById(R.id.hjælp);

        startKnap.setOnClickListener(this);
        hjælpKnap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == startKnap){
            Intent start = new Intent(this, GameStart.class);
            startActivity(start);
            finish();
        }
        else if (v == hjælpKnap){
            Intent hjælp = new Intent(this, Help.class);
            startActivity(hjælp);
            finish();
        }
    }
}