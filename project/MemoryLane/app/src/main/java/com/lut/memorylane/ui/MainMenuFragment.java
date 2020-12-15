/*
 * Author: Jani Heinikoski
 * Created: 14.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * Android Developers, 2020. Fragment Lifecycle. [Website]. [Referred 14.12.2020]. Available: https://developer.android.com/reference/android/app/Fragment#Lifecycle
 * */
package com.lut.memorylane.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lut.memorylane.databinding.FragmentMainMenuBinding;

public class MainMenuFragment extends Fragment {
    private FragmentMainMenuBinding binding;
    private IFragmentOwner fragmentOwner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentOwner) {
            fragmentOwner = (IFragmentOwner) context;
        } else {
            System.err.println("OwnerActivity must implement IFragmentOwner interface.");
            System.exit(-1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false);
        initializeMainMenuButtons();
        return binding.getRoot();
    }

    private void initializeMainMenuButtons() {
        // Switch to Speed Test fragment when corresponding button is clicked
        binding.fragmentMainButtonSpeedTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOwner.changeFragment(new SpeedTestFragment(), true);
            }
        });
        // Switch to Memory Test fragment when corresponding button is clicked
        binding.fragmentMainButtonMemoryTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOwner.changeFragment(new MemoryTestFragment(), true);
            }
        });
        // Switch to High Score fragment when corresponding button is clicked
        binding.fragmentMainButtonHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOwner.changeFragment(new HighScoreFragment(), true);
            }
        });
    }

}
