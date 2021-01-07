package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class WinPage extends AppCompatActivity implements View.OnClickListener {

    TextView amountOfTries, score;
    Button playWon;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet2);
        Intent intent = getIntent();
        player = MediaPlayer.create(this, R.raw.vundet);
        player.start();
        amountOfTries = findViewById(R.id.txtTries);
        score = findViewById(R.id.score);
        String ordet = intent.getStringExtra("ordet");
        String highscore = intent.getStringExtra("score");
        score.setText("Du har fået " + intent.getStringExtra("score") + " point");
        amountOfTries.setText("Du har brugt " + intent.getStringExtra("amountTries") + " forsøg.");
        playWon = findViewById(R.id.buttonWon);
        playWon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == playWon){
            Intent screen = new Intent(this, CustomWord.class);
            startActivity(screen);
            finish();
        }
    }
}