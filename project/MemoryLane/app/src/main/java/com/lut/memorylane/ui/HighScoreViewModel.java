/*
 * Author: Jani Heinikoski
 * Created: 25.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * Android Developers, 2020. Android Room with a View - Java. [Website]. [Referred 25.12.2020]. Available: https://developer.android.com/codelabs/android-room-with-a-view#0
 */
package com.lut.memorylane.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lut.memorylane.data.HighScore;
import com.lut.memorylane.data.HighScoreRepository;

import java.util.List;

public class HighScoreViewModel extends AndroidViewModel { // (Android Developers, 2020. Section 10 - Create the ViewModel)

    private HighScoreRepository repository;
    private final LiveData<List<HighScore>> highScoresOrdered;

    public HighScoreViewModel(Application application) {
        super(application);
        repository = new HighScoreRepository(application);
        highScoresOrdered = repository.getHighScoresOrdered();
    }


    public LiveData<List<HighScore>> getHighScoresOrdered() { return highScoresOrdered; }

    public void insert(HighScore highScore) { repository.insert(highScore); }

    public void deleteAll() {
        repository.deleteAll();
    }
}
