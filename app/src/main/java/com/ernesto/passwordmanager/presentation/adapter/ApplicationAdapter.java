package com.ernesto.passwordmanager.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.presentation.item.ApplicationItem;
import com.ernesto.passwordmanager.presentation.item.PeopleItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ApplicationAdapter extends ArrayAdapter<ApplicationItem> {

    public ApplicationAdapter(Context context, ArrayList<ApplicationItem> applicationList) {
        super(context, 0, applicationList);
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

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.application_spinner, parent, false
            );
        }

        ImageView applicationView = convertView.findViewById(R.id.applicationImg_AddPassword);
        TextView applicationName = convertView.findViewById(R.id.applicationName_AddPassword);

        ApplicationItem currentItem = getItem(position);
        if (currentItem != null) {
            applicationView.setImageResource(currentItem.getApplicationImg());
            applicationName.setText(currentItem.getApplicationName());
        }
        return convertView;
    }
}
