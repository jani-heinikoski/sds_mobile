/*
 * Author: Jani Heinikoski
 * Date created: 04.11.2020
 * Last modified: 04.11.2020
 */
package com.lut.coreelements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lut.coreelements.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding binding = ActivitySecondBinding.inflate(getLayoutInflater());
        if (getIntent().hasExtra("com.lut.coreelements.TAG")) {
            binding.textviewTxtToDisplay.setText(
                    getIntent().getStringExtra("com.lut.coreelements.TAG")
            );
        }
        setContentView(binding.getRoot());
    }
}