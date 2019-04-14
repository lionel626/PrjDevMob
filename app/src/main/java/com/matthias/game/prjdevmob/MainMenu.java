package com.matthias.game.prjdevmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void play(View v) { // Lance lactivée du jeu
        Intent intent = new Intent(MainMenu.this, GameActivity.class);
        this.startActivity(intent);
    }

    public void score(View v) { // Lance lactivée de l'affichage des Scores
        Intent intent = new Intent(MainMenu.this, ScoreActivity.class);
        this.startActivity(intent);
    }

}
