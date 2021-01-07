package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LostPage extends AppCompatActivity implements View.OnClickListener {

    TextView txtWord;
    Button playLost;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_page);
        Intent intent = getIntent();
        player = MediaPlayer.create(this, R.raw.gameover);
        player.start();
        txtWord = findViewById(R.id.txtWord);
        txtWord.setText("Ordet var: " + intent.getStringExtra("wordLost"));
        playLost = findViewById(R.id.playAgain);
        playLost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent startScreen = new Intent(this, GameStart.class);
        startActivity(startScreen);
        finish();
    }
}