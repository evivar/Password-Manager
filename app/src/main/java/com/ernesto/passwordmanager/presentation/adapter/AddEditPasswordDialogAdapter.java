package com.ernesto.passwordmanager.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddEditPasswordDialogAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentCollection = new ArrayList<>();

    private List<String> mTitleCollection = new ArrayList<>();

    public AddEditPasswordDialogAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(String title, Fragment fragment){
        mFragmentCollection.add(fragment);
        mTitleCollection.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleCollection.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentCollection.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentCollection.size();
    }
}
