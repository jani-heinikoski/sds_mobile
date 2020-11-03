/*
* Author: Jani Heinikoski
* Created: 03.11.2020
* Last modified: 03.11.2020
*/
package com.lut.introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lut.introduction.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}