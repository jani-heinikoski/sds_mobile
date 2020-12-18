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

public class GameAnimationHandler {
    private static final int PROGRESS_BAR_ANIM_DURATION = 30000;
    private static final int GAME_BUTTON_HIGHLIGHT_DURATION = 150;

    private static final int PROGRESS_BAR_MAX = 5000;

    private static final float GAME_BUTTON_ANIM_MIN_X_SCALE = 0.95f;
    private static final float GAME_BUTTON_ANIM_MIN_Y_SCALE = 0.95f;
    private static final float GAME_BUTTON_ANIM_MIN_ALPHA = 0.3f;

    private static GameAnimationHandler gameAnimationHandler;

    private ObjectAnimator gameProgressBarAnimator = null;
    private ObjectAnimator[] gameButtonAnimators = null;
    private long currentProgressBarPlayTime, currentButtonAnimationPlayTime;

    private GameAnimationHandler() {
        currentButtonAnimationPlayTime = 0;
        currentProgressBarPlayTime = 0;
    }

    public static GameAnimationHandler getInstance() {
        if (gameAnimationHandler == null) {
            gameAnimationHandler = new GameAnimationHandler();
        }
        return gameAnimationHandler;
    }

    public void pauseAnimations() {
        if (gameProgressBarAnimator != null) {
            currentProgressBarPlayTime = gameProgressBarAnimator.getCurrentPlayTime();
            gameProgressBarAnimator.pause();
        }

        if (gameButtonAnimators != null) {
            for (ObjectAnimator oa : gameButtonAnimators) {
                if (oa != null) {
                    currentButtonAnimationPlayTime = oa.getCurrentPlayTime();// TODO check if needed
                    oa.pause();
                }
            }
        }
    }

    public void continueAnimations() {
        if (gameProgressBarAnimator != null) {
            gameProgressBarAnimator.setCurrentPlayTime(currentProgressBarPlayTime);
            gameProgressBarAnimator.start();
        }

        if (gameButtonAnimators != null) {
            for (ObjectAnimator oa : gameButtonAnimators) {
                if (oa != null) {
                    oa.setCurrentPlayTime(currentButtonAnimationPlayTime); // TODO check if needed
                    oa.start();
                }
            }
        }
    }

    public void startGameProgressBar(@NonNull ProgressBar gameProgressBar) {
        if (gameProgressBarAnimator == null) {
            gameProgressBarAnimator = ObjectAnimator.ofInt(gameProgressBar, "progress", 0, PROGRESS_BAR_MAX);
            gameProgressBarAnimator.setInterpolator(new LinearInterpolator());
            gameProgressBarAnimator.setDuration(PROGRESS_BAR_ANIM_DURATION);
        }
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

    private void updateAnimatorArrayTargets(@NonNull Object target, @NonNull ObjectAnimator[] animators) {
        for (ObjectAnimator oa : animators) {
            oa.setTarget(target);
        }
    }
}
