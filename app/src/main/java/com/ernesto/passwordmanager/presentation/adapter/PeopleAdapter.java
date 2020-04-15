package com.ernesto.passwordmanager.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.presentation.item.PeopleItem;

import java.util.ArrayList;

public class PeopleAdapter extends ArrayAdapter<PeopleItem> {

    public PeopleAdapter(Context context, ArrayList<PeopleItem> peopleList) {
        super(context, 0, peopleList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.people_spinner, parent, false
            );
        }

        ImageView peopleView = convertView.findViewById(R.id.personImg_AddPerson);

        PeopleItem currentItem = getItem(position);
        if (currentItem != null) {
            peopleView.setImageResource(currentItem.getPeopleImg());
        }
        return convertView;
    }
}
