/*
 * Author: Jani Heinikoski
 * Created: 25.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * Android Developers, 2020. Android Room with a View - Java. [Website]. [Referred 25.12.2020]. Available: https://developer.android.com/codelabs/android-room-with-a-view#0
 */
package com.lut.memorylane.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HighScoreRepository { // (Android Developers, 2020. Section 9 - Create the Repository)
    private HighScoreDao highScoreDao;
    private LiveData<List<HighScore>> highScores;

    // Note that in order to unit test the HighScoreRepository, you have to remove the Application
    // dependency.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public HighScoreRepository(Application application) {
        HighScoreRoomDatabase db = HighScoreRoomDatabase.getDatabase(application);
        highScoreDao = db.highScoreDao();
        highScores = highScoreDao.getHighScoresOrdered();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<HighScore>> getHighScoresOrdered() {
        return highScores;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(HighScore highScore) {
        HighScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            highScoreDao.insert(highScore);
        });
    }

    public void deleteAll() {
        HighScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            highScoreDao.deleteAll();
        });
    }
}
