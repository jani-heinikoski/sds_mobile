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
    private MainListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listAdapter = new MainListAdapter(
                getResources().getStringArray(R.array.list_items),
                getResources().getStringArray(R.array.list_prices),
                getResources().getStringArray(R.array.list_descriptions),
                this
        );

        binding.mainActivityListViewMain.setAdapter(
                listAdapter
        );
    }
}