/*
 * Author: Jani Heinikoski
 * Created: 15.12.2020
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lut.memorylane.data.HighScore;
import com.lut.memorylane.databinding.FragmentHighScoreBinding;

import java.util.List;

public class HighScoreFragment extends Fragment {

    private FragmentHighScoreBinding binding;
    private HighScoreViewModel viewModel;
    private HighScoreRecyclerAdapter recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHighScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(HighScoreViewModel.class);
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.fragmentHighScoreRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        binding.fragmentHighScoreRecyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new HighScoreRecyclerAdapter(viewModel.getHighScoresOrdered().getValue());
        binding.fragmentHighScoreRecyclerView.setAdapter(recyclerAdapter);

        viewModel.getHighScoresOrdered().observe(getViewLifecycleOwner(), new Observer<List<HighScore>>() {
            @Override
            public void onChanged(List<HighScore> highScores) {
                recyclerAdapter.changeHighScores(highScores);
            }
        });

        binding.fragmentHighScoreButtonDeleteHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteAll();
            }
        });
    }
}
