/*
 * Author: Jani Heinikoski
 * Created: 18.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * */
package com.lut.memorylane.utility;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.lut.memorylane.ui.GameProgressBar;

/* Handles all the animations in the game (button highlighting and progressbar)
* Static final variables can be tweaked to fine tune functionality */
public class GameAnimationHandler {
    private static final int PROGRESS_BAR_ANIM_DURATION = 50000;
    private static final int GAME_BUTTON_HIGHLIGHT_DURATION = 100;

    private static final int PROGRESS_BAR_MAX = 5000;

    private static final float GAME_BUTTON_ANIM_MIN_X_SCALE = 0.95f;
    private static final float GAME_BUTTON_ANIM_MIN_Y_SCALE = 0.95f;
    private static final float GAME_BUTTON_ANIM_MIN_ALPHA = 0.3f;

    private static GameAnimationHandler gameAnimationHandler;

    private ObjectAnimator gameProgressBarAnimator = null;
    private ObjectAnimator[] gameButtonAnimators = null;
    private long currentProgressBarPlayTime;

    private GameAnimationHandler() {
        currentProgressBarPlayTime = 0;
    }

    public static GameAnimationHandler getInstance() {
        if (gameAnimationHandler == null) {
            gameAnimationHandler = new GameAnimationHandler();
        }
        return gameAnimationHandler;
    }

    public void startGameProgressBar(@NonNull GameProgressBar gameProgressBar) {
        if (gameProgressBarAnimator == null) {
            gameProgressBarAnimator = ObjectAnimator.ofInt(gameProgressBar, "progress", 0, PROGRESS_BAR_MAX);
            gameProgressBarAnimator.setInterpolator(new LinearInterpolator());
            gameProgressBarAnimator.setDuration(PROGRESS_BAR_ANIM_DURATION);
        }
        currentProgressBarPlayTime = gameProgressBarAnimator.getCurrentPlayTime();
        gameProgressBarAnimator.setTarget(gameProgressBar);
        gameProgressBarAnimator.setCurrentPlayTime(currentProgressBarPlayTime);
        gameProgressBarAnimator.start();
    }

    public void highlightGameButton(@NonNull Button buttonToHighlight) {
        if (gameButtonAnimators == null) {
            gameButtonAnimators = new ObjectAnimator[] {
                    ObjectAnimator.ofFloat(buttonToHighlight, "scaleX", 1.0f, GAME_BUTTON_ANIM_MIN_X_SCALE),
                    ObjectAnimator.ofFloat(buttonToHighlight, "scaleY", 1.0f, GAME_BUTTON_ANIM_MIN_Y_SCALE),
                    ObjectAnimator.ofFloat(buttonToHighlight, "alpha", 1.0f, GAME_BUTTON_ANIM_MIN_ALPHA)
            };
            for (ObjectAnimator oa : gameButtonAnimators) {
                oa.setInterpolator(new LinearInterpolator());
                oa.setDuration(GAME_BUTTON_HIGHLIGHT_DURATION);
                oa.setRepeatMode(ValueAnimator.REVERSE);
                oa.setRepeatCount(1);
            }
        } else {
            updateAnimatorArrayTargets(buttonToHighlight, gameButtonAnimators);
        }

        for (ObjectAnimator oa : gameButtonAnimators) {
            oa.start();
        }
    }

    public void pauseProgressBar() {
        gameProgressBarAnimator.pause();
    }

    public void resumeProgressBar() {
        gameProgressBarAnimator.resume();
    }

    public void resetProgressBar() {
        if (gameProgressBarAnimator != null) {
            gameProgressBarAnimator.setCurrentPlayTime(0);
        }
    }

    private void updateAnimatorArrayTargets(@NonNull Object target, @NonNull ObjectAnimator[] animators) {
        for (ObjectAnimator oa : animators) {
            oa.cancel();
            oa.setTarget(target);
        }
    }

}
