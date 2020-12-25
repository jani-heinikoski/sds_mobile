/*
* Author: Jani Heinikoski
* Created: 25.12.2020
* Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
* Sources:
* Android Developers, 2020. Defining data using Room entities. [Website]. [Referred 25.12.2020]. Available: https://developer.android.com/training/data-storage/room/defining-data
 */
package com.lut.memorylane.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "high_score")
public class HighScore {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int score;
    private String timestamp;

    public HighScore(int score) {
        this.score = score;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(new Date());;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
