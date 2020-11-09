package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.example.galgeleg.GalgeLogik.Galgelogik;

public class GameStart extends AppCompatActivity implements View.OnClickListener {

    private Galgelogik gameLogic;
    private TextView guessedWord;
    private EditText edtInput;
    private TextView usedCharacters;
    private TextView tries;
    private ImageView picture;
    private Button guessButton;
    private Executor bgThread = Executors.newSingleThreadExecutor(); // en baggrundstråd
    private Handler uiThread = new Handler(Looper.getMainLooper());  // forgrundstråden



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        gameLogic = Galgelogik.getInstance();
        guessedWord = findViewById(R.id.mitOrd);

        bgThread.execute(() -> {
            try {
                gameLogic.hentOrdFraDr();
                uiThread.post(() -> {
                    guessedWord.setText(gameLogic.getSynligtOrd());
                });
            } catch (Exception e) {
                e.printStackTrace();
                uiThread.post(() -> {
                    guessedWord.setText("Opstod en fejl under afhenting af ordet.");
                });
            }
        });

        edtInput = findViewById(R.id.inputBogstav);
        usedCharacters = findViewById(R.id.brugteBogstaver);
        tries = findViewById(R.id.forsøgTilbage);
        picture = findViewById(R.id.forkertBillede);
        guessButton = findViewById(R.id.submit);
        guessButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        edtInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
        gameLogic.gætBogstav(edtInput.getText().toString());
        guessedWord.setText(gameLogic.getSynligtOrd());
        switch (gameLogic.getAntalForkerteBogstaver()){
            case 1:
                picture.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                picture.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                picture.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                picture.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                picture.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                picture.setImageResource(R.drawable.forkert6);
                break;
        }
        usedCharacters.setText("Brugte bogstaver: " + gameLogic.getBrugteBogstaver());
        tries.setText(Integer.toString(6- gameLogic.getAntalForkerteBogstaver()));
        edtInput.getText().clear();
        if(gameLogic.erSpilletVundet()){
            Intent vundet = new Intent(this, WinPage.class);
            vundet.putExtra("amountTries", Integer.toString(gameLogic.getAntalForkerteBogstaver()));
            startActivity(vundet);
            finish();
        }
        if (gameLogic.erSpilletTabt()) {
            Intent tabt = new Intent(this, LostPage.class);
            tabt.putExtra("wordLost", gameLogic.getOrdet());
            startActivity(tabt);
            finish();
        }
    }
}