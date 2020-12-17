/*
* Author: Jani Heinikoski
* Created: 14.12.2020
* Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
* Sources:
* */
package com.lut.memorylane.ui;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.lut.memorylane.R;
import com.lut.memorylane.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IFragmentOwner {

    private FragmentManager fragmentManager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
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

    @Override
    public void changeActionbar(@DrawableRes int iconDrawableID, @StringRes int titleStringResID, View.OnClickListener onClickListener) {
        Drawable drawable = ContextCompat.getDrawable(this, iconDrawableID);
        String title = getString(titleStringResID);

        if (drawable != null) {
            binding.mainActivityActionBarButtonHomeIcon.setBackground(drawable);
        } else {
            System.err.println("Failed to retrieve drawable resource, id was " + iconDrawableID);
        }

        if (title != null) {
            binding.mainActivityActionBarTextviewTitle.setText(title);
        } else {
            System.err.println("Failed to retrieve string resource, id was " + titleStringResID);
        }

        binding.mainActivityActionBarButtonHomeIcon.setOnClickListener(onClickListener);
    }

    @Override
    public void popBackStack() {
        fragmentManager.popBackStack();
    }
}