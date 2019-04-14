package com.matthias.game.prjdevmob;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.Map;

public class ScoreActivity extends AppCompatActivity {

    private ListView mListView;
    private MyArrayAdapter mAdapter;
    private String[] scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mListView = findViewById(R.id.list);
        setScores();

    }

    public void setScores() { // Recuperation des score enregistré et affichage des Scores
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        scores = new String[pref.getAll().size()];
        int i=0;
        for(Map.Entry<String, Integer> pairs: ((Map<String,Integer>)pref.getAll()).entrySet()) {
            scores[i] = pairs.getKey() + " : " + pairs.getValue();
            i++;
        }
        mAdapter = new MyArrayAdapter(ScoreActivity.this, scores);
        mListView.setAdapter(mAdapter);
    }
}
