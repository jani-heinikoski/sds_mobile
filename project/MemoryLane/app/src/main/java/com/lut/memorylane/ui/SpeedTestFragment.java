/*
 * Author: Jani Heinikoski
 * Created: 15.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * Android Developers, 2020. Fragment Lifecycle. [Website]. [Referred 14.12.2020]. Available: https://developer.android.com/reference/android/app/Fragment#Lifecycle
 * */
package com.lut.memorylane.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lut.memorylane.data.GameState;
import com.lut.memorylane.databinding.DialogItemBinding;
import com.lut.memorylane.databinding.FragmentSpeedTestBinding;
import com.lut.memorylane.utility.GameAnimationHandler;
import com.lut.memorylane.utility.SpeedTestViewModel;

public class SpeedTestFragment extends Fragment implements IProgressCallback {

    private static final int DECREASE_DELAY_MILLIS = 15;

    private FragmentSpeedTestBinding binding;
    private SpeedTestViewModel speedTestViewModel;
    private GameAnimationHandler gameAnimationHandler;

    private Dialog dialog;
    private DialogItemBinding dialogItemBinding;

    private Handler uiThreadHandler;

    private Button[] gameButtonsOrdered;

    private void continueGame() {
        uiThreadHandler.postDelayed(speedTestViewModel.getButtonHighlighter(), 1000);
        uiThreadHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!speedTestViewModel.getCurrentSpeedTestGameState().isGameOver()) {
                    gameAnimationHandler.resumeProgressBar();
                }
            }
        }, 1000);
    }

    /*
    * Triggered when the game ends. End conditions are:
    * User clicks a game button in a different sequence than dictated in the current GameState object
    * (see GameState - clickMatchesSequence(int clickedIndex))
    * or the GameProgressBar hits max value
    * */
    private void gameOver() {
        GameState gs = speedTestViewModel.getCurrentSpeedTestGameState();
        gs.setGameOver(true);
        speedTestViewModel.setCurrentSpeedTestGameState(gs);
        gameAnimationHandler.pauseProgressBar();
        uiThreadHandler.removeCallbacks(speedTestViewModel.getButtonHighlighter());
        for (Button b : gameButtonsOrdered) {
            b.setOnClickListener(null);
        }
        //TODO Replace with GameOver dialog
        dialogItemBinding.dialogItemGameOverText.setText((CharSequence) ("Game over, score: " + gs.getPlayerScore()));
        dialog.show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiThreadHandler = new Handler(); /* new Handler in order to post timed tasks (automatically attaches itself to the UI thread) */
        gameAnimationHandler = GameAnimationHandler.getInstance(); /* Handles all the animations in the game (button highlighting and progressbar) */
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSpeedTestBinding.inflate(inflater, container, false);
        /* SpeedTestViewModel stores all lifecycle aware data */
        speedTestViewModel = new ViewModelProvider(requireActivity()).get(SpeedTestViewModel.class);
        /* If the fragment is created for the first time and not retrieved from the backstack
        * a new game is created and the game animation handler is reset */
        if (savedInstanceState == null) {
            speedTestViewModel.setCurrentDelay(1000);
            gameAnimationHandler.resetProgressBar();
            speedTestViewModel.setCurrentSpeedTestGameState(new GameState());
        }

        this.gameButtonsOrdered = new Button[] {
                binding.fragmentSpeedTestGameButton1,
                binding.fragmentSpeedTestGameButton2,
                binding.fragmentSpeedTestGameButton3,
                binding.fragmentSpeedTestGameButton4
        };
        /* Set click listeners for all of the game buttons */
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            gameButtonsOrdered[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!speedTestViewModel.getCurrentSpeedTestGameState().clickMatchesSequence(finalI)) {
                        gameOver();
                    }
                }
            });
        }
        /* Button highlighter for the current game (needs to be renewed when the fragment is recreated) */
        speedTestViewModel.setButtonHighlighter(new Runnable() {
            @Override
            public void run() {
                int i = speedTestViewModel.getCurrentSpeedTestGameState().getNextInSequence();
                gameAnimationHandler.highlightGameButton(gameButtonsOrdered[i]);
                if (speedTestViewModel.getCurrentDelay() >= 300) {
                    speedTestViewModel.setCurrentDelay(speedTestViewModel.getCurrentDelay() - DECREASE_DELAY_MILLIS);
                }
                uiThreadHandler.postDelayed(this, speedTestViewModel.getCurrentDelay());
            }
        });

        this.dialog = new Dialog(getContext());
        this.dialogItemBinding = DialogItemBinding.inflate(inflater);
        this.dialog.setContentView(dialogItemBinding.getRoot());

        dialogItemBinding.dialogItemContainer.setClickable(false);
        dialogItemBinding.dialogItemButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getParentFragmentManager().popBackStack();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface d) {
                getParentFragmentManager().popBackStack();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!speedTestViewModel.getCurrentSpeedTestGameState().isGameOver()) {
            GameProgressBar gameProgressBar = binding.fragmentSpeedTestProgressBar;
            gameProgressBar.setIProgressCallback(this);
            gameAnimationHandler.startGameProgressBar(gameProgressBar);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        GameState gs = speedTestViewModel.getCurrentSpeedTestGameState();
        if (gs.isGameOver()) {
            gameOver();
        } else {
            continueGame();
        }
    }

    @Override
    public void onPause() {
        gameAnimationHandler.pauseProgressBar();
        uiThreadHandler.removeCallbacks(speedTestViewModel.getButtonHighlighter());
        super.onPause();
    }

    @Override
    public void progressbarFinished() {
        gameOver();
    }
}
