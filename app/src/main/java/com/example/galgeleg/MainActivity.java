package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startButton, helpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.start);
        helpButton = findViewById(R.id.hjælp);

        startButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == startButton){
            Intent start = new Intent(this, CustomWord.class);
            startActivity(start);
            finish();
        }
        else if (v == helpButton){
            Intent help = new Intent(this, Help.class);
            startActivity(help);
        }
    }
}