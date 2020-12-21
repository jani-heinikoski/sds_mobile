/*
* Author: Jani Heinikoski
* Created: 14.12.2020
* Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
* Sources:
* */
package com.lut.memorylane.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.lut.memorylane.R;
import com.lut.memorylane.databinding.ActivityMainBinding;
import com.lut.memorylane.utility.SharedViewModel;

public class MainActivity extends AppCompatActivity implements IFragmentOwner {

    private FragmentManager fragmentManager;
    private ActivityMainBinding binding;
    private SharedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.setFragmentOwner(this);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            viewModel.setActionBarIconID(R.drawable.ic_baseline_home_24);
            viewModel.setActionBarTitleID(R.string.main_activity_action_bar_title_home);
            viewModel.setActionBarButtonOnClickListener(null);
            changeFragment(new MainMenuFragment(), false);
        }

        viewModel.getActionBarIconID().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.mainActivityActionBarButtonHomeIcon.setBackground(
                        ContextCompat.getDrawable(getOuterClass(), integer)
                );
            }
        });

        viewModel.getActionBarTitleID().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.mainActivityActionBarTextviewTitle.setText(
                        integer
                );
            }
        });

        viewModel.getActionBarButtonOnClickListener().observe(this, new Observer<View.OnClickListener>() {
            @Override
            public void onChanged(View.OnClickListener onClickListener) {
                binding.mainActivityActionBarButtonHomeIcon.setOnClickListener(onClickListener);
            }
        });

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
    public void popBackStack() {
        fragmentManager.popBackStack();
    }

    private MainActivity getOuterClass() {
        return this;
    }
}