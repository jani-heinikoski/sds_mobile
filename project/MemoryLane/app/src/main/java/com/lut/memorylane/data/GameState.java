/*
 * Author: Jani Heinikoski
 * Created: 19.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * */
package com.lut.memorylane.data;

import java.util.ArrayList;
import java.util.Collections;

public class GameState {
    public static final int SEQUENCE_LEN = 100;

    private boolean gameOver;
    private int playerScore, sequenceIndex;

    private int[] clickSequence;

    public GameState() {
        playerScore = 0;
        sequenceIndex = 0;
        gameOver = false;
        clickSequence = new int[SEQUENCE_LEN];
        // Generate a random clicking sequence where two same numbers are not adjacent
        // SpeedTestFragment's buttons are highlighted in the random order
        ArrayList<Integer> possibleNext = new ArrayList<>(3);
        possibleNext.add(0);
        possibleNext.add(1);
        possibleNext.add(2);
        possibleNext.add(3);

        Collections.shuffle(possibleNext);
        int previous = possibleNext.get(0);
        possibleNext.remove(0);

        for (int i = 0; i < SEQUENCE_LEN; i++) {
            Collections.shuffle(possibleNext);
            clickSequence[i] = possibleNext.get(0);
            possibleNext.add(previous);
            previous = clickSequence[i];
            possibleNext.remove(0);
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getNextInSequence() {
        if (sequenceIndex == SEQUENCE_LEN) {
            sequenceIndex = 0;
        }
        return clickSequence[sequenceIndex++];
    }

    public boolean clickMatchesSequence(int clickedIndex) {
        boolean b =
                clickSequence[playerScore - (int)((float) playerScore / (float) SEQUENCE_LEN) * SEQUENCE_LEN]
                        == clickedIndex;
        if (b) {
            playerScore++;
        }
        return b;
    }
}
