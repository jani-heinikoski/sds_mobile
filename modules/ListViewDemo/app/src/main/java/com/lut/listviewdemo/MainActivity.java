/*
 * Author: Jani Heinikoski
 * Date created: 05.11.2020
 * Last modified: 05.11.2020
 */
package com.lut.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lut.listviewdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}