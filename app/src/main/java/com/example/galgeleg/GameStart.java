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
    private Handler uiThread = new Handler(Looper.getMainLooper());  // en forgrundstråden
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        Intent intent = getIntent();
        String holder = intent.getStringExtra("ListViewValue");
        gameLogic = Galgelogik.getInstance();
        gameLogic.setOrdet(holder);
        guessedWord = findViewById(R.id.mitOrd);
        if (holder.equals("ord fra DR")){
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
        } else {
            guessedWord.setText(gameLogic.getSynligtOrd());
        }

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
        if (gameLogic.getOrdet().length() <= 3 && gameLogic.erSidsteBogstavKorrekt()){
            score += 3;
        }
        else if (gameLogic.getOrdet().length() > 3 && gameLogic.erSidsteBogstavKorrekt()) {
            score += 5;
        }
        if(gameLogic.erSpilletVundet()){
            Intent vundet = new Intent(this, WinPage.class);
            vundet.putExtra("amountTries", Integer.toString(gameLogic.getAntalForkerteBogstaver()));
            vundet.putExtra("score", Integer.toString(score));
            startActivity(vundet);
            score = 0;
            finish();
        }
        if (gameLogic.erSpilletTabt()) {
            score = 0;
            Intent tabt = new Intent(this, LostPage.class);
            tabt.putExtra("wordLost", gameLogic.getOrdet());
            startActivity(tabt);
            finish();
        }
    }
}