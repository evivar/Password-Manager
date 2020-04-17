package com.ernesto.passwordmanager.presentation.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.domain.entity.Password;
import com.ernesto.passwordmanager.presentation.adapter.AddEditPasswordDialogAdapter;
import com.ernesto.passwordmanager.presentation.fragment.AddEditAppPasswordFragment;
import com.ernesto.passwordmanager.presentation.fragment.AddEditOtherPasswordFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class AddEditPasswordTabbedDialog extends DialogFragment {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private boolean isEditing;

    private Password password;

    public AddEditPasswordTabbedDialog(boolean isEditing, Password password) {
        this.isEditing = isEditing;
        this.password = password;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_add_edit_password_v2, container, false);

        tabLayout = rootView.findViewById(R.id.tabLayour_AddPassword);
        AddEditPasswordDialogAdapter adapter = new AddEditPasswordDialogAdapter(getChildFragmentManager());
        viewPager = rootView.findViewById(R.id.viewPager);

        adapter.addFragment("Añadir/Editar Contraseña", AddEditAppPasswordFragment.createInstance());
        adapter.addFragment("Añadir/Editar Otra Contraseña", AddEditOtherPasswordFragment.createInstance());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }
}
