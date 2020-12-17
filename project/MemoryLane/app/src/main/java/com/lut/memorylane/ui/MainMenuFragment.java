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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lut.memorylane.R;
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

    @Override
    public void onResume() {
        super.onResume();
        fragmentOwner.changeActionbar(R.drawable.ic_baseline_home_24, R.string.main_activity_action_bar_title_home, null);
    }

    private void initializeMainMenuButtons() {
        // Switch to Speed Test fragment when corresponding button is clicked
        binding.fragmentMainButtonSpeedTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOwner.changeFragment(new SpeedTestFragment(), true);
                fragmentOwner.changeActionbar(R.drawable.ic_baseline_arrow_back_24, R.string.main_activity_action_bar_title_speed_test, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentOwner.popBackStack();
                    }
                });
            }
        });
        // Switch to Memory Test fragment when corresponding button is clicked
        binding.fragmentMainButtonMemoryTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOwner.changeFragment(new MemoryTestFragment(), true);
                fragmentOwner.changeActionbar(R.drawable.ic_baseline_arrow_back_24, R.string.main_activity_action_bar_title_memory_test, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentOwner.popBackStack();
                    }
                });
            }
        });
        // Switch to High Score fragment when corresponding button is clicked
        binding.fragmentMainButtonHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOwner.changeFragment(new HighScoreFragment(), true);
                fragmentOwner.changeActionbar(R.drawable.ic_baseline_arrow_back_24, R.string.main_activity_action_bar_title_high_score, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentOwner.popBackStack();
                    }
                });
            }
        });
    }

}
