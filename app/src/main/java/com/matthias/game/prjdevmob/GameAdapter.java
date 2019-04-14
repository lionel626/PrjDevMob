package com.matthias.game.prjdevmob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GameAdapter extends ArrayAdapter<Integer> {
    // Gére l'affichage de la grille
    private final Context context;

    public GameAdapter(Context context, Integer val[]) {
        super(context, R.layout.cells, val);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView = convertView;

        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.cells, parent, false);
        }

        TextView textView = cellView.findViewById(R.id.textView);
        String s = " ";
        if(getItem(position)!= null) {
            s = Integer.toString(getItem(position),10);
        }

        textView.setText(s);
        return cellView;
    }
}
