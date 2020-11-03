package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import galgeleg.Galgelogik;

public class GameStart extends AppCompatActivity implements View.OnClickListener {

    Galgelogik spilLogik;
    TextView gættetOrd;
    EditText rdgInput;
    TextView brugteBogstaver;
    TextView forsøg;
    ImageView billede;
    Button gætteKnap;
    Executor bgThread = Executors.newSingleThreadExecutor(); // en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // forgrundstråden



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start2);
        spilLogik = new Galgelogik();
        spilLogik.startNytSpil();
        gættetOrd = findViewById(R.id.mitOrd);


        bgThread.execute(() -> {
            try {
                spilLogik.hentOrdFraDr();
                uiThread.post(() -> {
                    gættetOrd.setText(spilLogik.getSynligtOrd());
                });
            } catch (Exception e) {
                e.printStackTrace();
                uiThread.post(() -> {
                    gættetOrd.setText("Opstod en fejl under afhenting af ordet.");
                });
            }
        });

        rdgInput = findViewById(R.id.inputBogstav);
        brugteBogstaver = findViewById(R.id.brugteBogstaver);
        forsøg = findViewById(R.id.forsøgTilbage);
        billede = findViewById(R.id.forkertBillede);
        gætteKnap = findViewById(R.id.submit);
        gætteKnap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        spilLogik.gætBogstav(rdgInput.getText().toString());
        gættetOrd.setText(spilLogik.getSynligtOrd());
        switch (spilLogik.getAntalForkerteBogstaver()){
            case 1:
                billede.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                billede.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                billede.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                billede.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                billede.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                billede.setImageResource(R.drawable.forkert6);
                break;
        }
        brugteBogstaver.setText("Brugte bogstaver: " + spilLogik.getBrugteBogstaver());
        forsøg.setText(Integer.toString(6- spilLogik.getAntalForkerteBogstaver()));
        rdgInput.getText().clear();
        if(spilLogik.erSpilletVundet()){
            /*Intent vundet = new Intent(this, Vundet.class);
            startActivity(vundet);
            finish();*/
            ErSpilletSlut(true);
        }
        if (spilLogik.erSpilletTabt()) {
            /*Intent tabt = new Intent(this, Tabt.class);
            startActivity(tabt);
            finish();*/
            ErSpilletSlut(false);
        }
    }
    public void ErSpilletSlut(boolean tilstand){
        if (tilstand){
            Intent vundet = new Intent(this, Vundet.class);
            startActivity(vundet);
            finish();
        } else{
            Intent tabt = new Intent(this, Tabt.class);
            startActivity(tabt);
            finish();
        }
    }
}