/*
 * Author: Jani Heinikoski
 * Created: 25.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * Android Developers, 2020. Android Room with a View - Java. [Website]. [Referred 25.12.2020]. Available: https://developer.android.com/codelabs/android-room-with-a-view#0
 */
package com.lut.memorylane.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {HighScore.class}, version = 1, exportSchema = false)
public abstract class HighScoreRoomDatabase extends RoomDatabase { // (Android Developers, 2020. Section 8 - Add a Room database)

    public abstract HighScoreDao highScoreDao();

    private static volatile HighScoreRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HighScoreRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HighScoreRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HighScoreRoomDatabase.class, "high_score_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
