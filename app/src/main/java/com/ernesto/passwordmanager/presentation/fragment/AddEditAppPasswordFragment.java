package com.ernesto.passwordmanager.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.presentation.dialog.AddEditPasswordDialog;

public class AddEditAppPasswordFragment extends Fragment {

    public static AddEditAppPasswordFragment createInstance(){
        AddEditAppPasswordFragment fragment = new AddEditAppPasswordFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_edit_app_password, container, false);
        return v;
    }
}
