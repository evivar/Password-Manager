package com.ernesto.passwordmanager.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ernesto.passwordmanager.R;

public class AddEditOtherPasswordFragment extends Fragment {

    public static AddEditOtherPasswordFragment createInstance(){
        AddEditOtherPasswordFragment fragment = new AddEditOtherPasswordFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_edith_other_password, container, false);
        return v;
    }

}
