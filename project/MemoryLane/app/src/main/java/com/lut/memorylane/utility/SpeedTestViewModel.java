/*
 * Author: Jani Heinikoski
 * Created: 21.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * */
package com.lut.memorylane.utility;

import androidx.lifecycle.ViewModel;

import com.lut.memorylane.data.GameState;


public class SpeedTestViewModel extends ViewModel {

    private Runnable buttonHighlighter = null;
    private GameState currentSpeedTestGameState = null;
    private int currentDelay = 1000;

    public void setCurrentSpeedTestGameState(GameState gameState) {
        this.currentSpeedTestGameState = gameState;
    }

    public GameState getCurrentSpeedTestGameState() {
        return currentSpeedTestGameState;
    }

    public void setButtonHighlighter(Runnable runnable) {
        buttonHighlighter = runnable;
    }

    public Runnable getButtonHighlighter() {
        return buttonHighlighter;
    }

    public int getCurrentDelay() {
        return currentDelay;
    }

    public void setCurrentDelay(int currentDelay) {
        this.currentDelay = currentDelay;
    }
}
