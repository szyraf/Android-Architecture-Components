package com.example.viewbindingapp002;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.viewbindingapp002.databinding.ActivityColorBindingImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends ArrayAdapter {

    private ArrayList<String> _list;
    private Context _context;
    private int _resource;

    public ColorAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this._list = (ArrayList<String>) objects;
        this._context = context;
        this._resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(_resource, null);

        TextView text = convertView.findViewById(R.id.idText);
        text.setText(_list.get(position));

        ImageView iv1 = (ImageView) convertView.findViewById(R.id.idImage);
        iv1.setBackgroundColor(Color.parseColor(_list.get(position)));

        return convertView;
    }

    @Override
    public int getCount() {
        return _list.size();
    }
}

