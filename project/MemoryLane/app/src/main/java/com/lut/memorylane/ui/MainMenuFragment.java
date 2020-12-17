/*
 * Author: Jani Heinikoski
 * Created: 14.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * Android Developers, 2020. Fragment Lifecycle. [Website]. [Referred 14.12.2020]. Available: https://developer.android.com/reference/android/app/Fragment#Lifecycle
 * */
package com.lut.memorylane.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lut.memorylane.R;
import com.lut.memorylane.databinding.FragmentMainMenuBinding;
import com.lut.memorylane.utility.SharedViewModel;

public class MainMenuFragment extends Fragment {
    private FragmentMainMenuBinding binding;
    private IFragmentOwner fragmentOwner;
    private SharedViewModel viewModel;

    private final Observer<IFragmentOwner> fragmentOwnerObserver = new Observer<IFragmentOwner>() {
        @Override
        public void onChanged(IFragmentOwner iFragmentOwner) {
            fragmentOwner = iFragmentOwner;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        viewModel.getFragmentOwner().removeObserver(fragmentOwnerObserver);
        viewModel.getFragmentOwner().observeForever(fragmentOwnerObserver);

        viewModel.setActionBarTitleID(R.string.main_activity_action_bar_title_home);
        viewModel.setActionBarIconID(R.drawable.ic_baseline_home_24);

        initializeMainMenuButtons();

        return binding.getRoot();
    }

    private void initializeMainMenuButtons() {
        // Switch to Speed Test fragment when corresponding button is clicked
        binding.fragmentMainButtonSpeedTest.setOnClickListener(getButtonOnClickListener(
                R.string.main_activity_action_bar_title_speed_test, new SpeedTestFragment()
        ));
        // Switch to Memory Test fragment when corresponding button is clicked
        binding.fragmentMainButtonMemoryTest.setOnClickListener(getButtonOnClickListener(
                R.string.main_activity_action_bar_title_memory_test, new MemoryTestFragment()
        ));
        // Switch to High Score fragment when corresponding button is clicked
        binding.fragmentMainButtonHighScores.setOnClickListener(getButtonOnClickListener(
                R.string.main_activity_action_bar_title_high_score, new HighScoreFragment()
        ));
    }

    private final View.OnClickListener onClickListenerPopBackStack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentOwner.popBackStack();
        }
    };

    private View.OnClickListener getButtonOnClickListener(@StringRes int titleID, @NonNull Fragment fragment) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOwner.changeFragment(fragment, true);
                viewModel.setActionBarTitleID(titleID);
                viewModel.setActionBarIconID(R.drawable.ic_baseline_arrow_back_24);
                viewModel.setActionBarButtonOnClickListener(onClickListenerPopBackStack);
            }
        };
    }

}
