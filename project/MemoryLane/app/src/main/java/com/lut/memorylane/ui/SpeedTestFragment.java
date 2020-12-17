/*
 * Author: Jani Heinikoski
 * Created: 15.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * Android Developers, 2020. Fragment Lifecycle. [Website]. [Referred 14.12.2020]. Available: https://developer.android.com/reference/android/app/Fragment#Lifecycle
 * */
package com.lut.memorylane.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lut.memorylane.databinding.FragmentSpeedTestBinding;
import com.lut.memorylane.utility.SharedViewModel;

public class SpeedTestFragment extends Fragment {

    private FragmentSpeedTestBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSpeedTestBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(binding.fragmentSpeedTestProgressBar, "progress", 0, 5000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(25000);
        objectAnimator.start();


    }
}
