/*
* Author: Jani Heinikoski
* Created: 14.12.2020
* Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
* Sources:
* */
package com.lut.memorylane.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.lut.memorylane.R;
import com.lut.memorylane.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IFragmentOwner {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        changeFragment(new MainMenuFragment(), false);
    }

    @Override
    public void changeFragment(Fragment newFragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_fragment_container, newFragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}