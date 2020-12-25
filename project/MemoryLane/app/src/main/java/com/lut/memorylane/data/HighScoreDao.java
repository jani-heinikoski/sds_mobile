/*
 * Author: Jani Heinikoski
 * Created: 25.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * Android Developers, 2020. Android Room with a View - Java. [Website]. [Referred 25.12.2020]. Available: https://developer.android.com/codelabs/android-room-with-a-view#0
 */
package com.lut.memorylane.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HighScoreDao {
    // allowing the insert of the same high score multiple times by passing a
    // conflict resolution strategy (Android Developers, 2020. Section 6 - Create the DAO)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(HighScore highScore);

    @Query("DELETE FROM high_score")
    void deleteAll();

    @Query("SELECT * FROM high_score ORDER BY score DESC")
    LiveData<List<HighScore>> getHighScoresOrdered();
}
