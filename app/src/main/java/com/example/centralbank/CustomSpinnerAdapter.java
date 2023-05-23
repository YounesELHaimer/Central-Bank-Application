package com.example.centralbank;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] items;

    public CustomSpinnerAdapter(Context context, int resource, String[] items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (position == 0) { // First item is the hint
            ((TextView) view.findViewById(android.R.id.text1)).setTextColor(context.getResources().getColor(R.color.hint_color));
            ((TextView) view.findViewById(android.R.id.text1)).setTypeface(null, Typeface.ITALIC);
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.spinner_item, parent, false);
        }

        if (position == 0) { // First item is the hint
            ((TextView) view.findViewById(android.R.id.text1)).setTextColor(context.getResources().getColor(R.color.hint_color));
            ((TextView) view.findViewById(android.R.id.text1)).setTypeface(null, Typeface.ITALIC);
        }

        return view;
    }
}
