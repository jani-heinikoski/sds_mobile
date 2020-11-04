/*
* Author: Jani Heinikoski
* Date created: 04.11.2020
* Last modified: 04.11.2020
 */
package com.lut.coreelements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.lut.coreelements.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        initButtons();
        setContentView(binding.getRoot());
    }

    private void initButtons() {
        binding.buttonLaunchSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(SecondActivity.class, "Hello From MainActivity");
            }
        });

        binding.buttonLaunchGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogle();
            }
        });
    }

    private void changeActivity(Class activity, String extraText) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("com.lut.coreelements.TAG", extraText);
        startActivity(intent);
    }

    private void openGoogle() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}