package com.matthias.game.prjdevmob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {

    private int score;
    private TextView textScore;
    private TextView namePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // Récupere le Scores et le pseudo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        textScore = findViewById(R.id.textView2);
        namePlayer = findViewById(R.id.editText);


        Bundle extras = getIntent().getExtras();
        score = 0;
        if (extras != null) {
            score = extras.getInt("count");
        }
        textScore.append(" " + Integer.toString(score,10));
    }

    public void sendScore(View v) { // Sauvegarde du Score et lancement de l'activité Score
        String name = namePlayer.getText().toString();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(pref.getInt(name, -1) == -1 ) {
            editor.putInt(name,score);
        } else if(pref.getInt(name, -1) < score) {
            editor.putInt(name,score);
        }

        editor.apply();

        Intent intent = new Intent(EndGame.this, ScoreActivity.class);
        this.startActivity(intent);



    }
}
