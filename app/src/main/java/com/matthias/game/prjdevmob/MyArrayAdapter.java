package com.matthias.game.prjdevmob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<String> {
    //Gére l'affichage des Scores
    private final Context context;


    public MyArrayAdapter(Context context, String[] values) {
        super(context, R.layout.cell_activity, values);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView = convertView;
        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.cell_activity, parent, false);
        }

        TextView textView = (TextView)cellView.findViewById(R.id.label);
        String s = getItem(position);
        textView.setText(s);
        return cellView;
    }
}
