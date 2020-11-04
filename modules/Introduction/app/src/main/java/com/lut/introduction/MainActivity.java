/*
* Author: Jani Heinikoski
* Created: 03.11.2020
* Last modified: 04.11.2020
*/
package com.lut.introduction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.lut.introduction.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    // Since we only have one MutableLiveData, it has not been added to its own ViewModel
    private final MutableLiveData<Integer> resultValue = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        initEditTexts();
        setContentView(binding.getRoot());
    }

    private void initEditTexts() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                addNumbersToResult(); // Triggers after user has edited text of the EditText component
            }
        };

        binding.edittextFirstNumber.addTextChangedListener(textWatcher);
        binding.edittextSecondNumber.addTextChangedListener(textWatcher);

        resultValue.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textviewResult.setText(String.format(
                        Locale.getDefault(),
                        "%s\n%d",
                        getString(R.string.activity_main_result_label_text), resultValue.getValue()
                ));
            }
        });
    }

    private void addNumbersToResult() {
        // Parse numbers from the EditText components in the MainActivity and set them to the resultValue.
        int firstNum, secondNum;

        try {
            firstNum = Integer.parseInt(
                    binding.edittextFirstNumber.getText().toString().trim()
            );
        } catch (Exception e) {
            firstNum = 0;
        }

        try {
            secondNum = Integer.parseInt(
                    binding.edittextSecondNumber.getText().toString().trim()
            );
        } catch (Exception e) {
            secondNum = 0;
        }

        resultValue.setValue(firstNum + secondNum);
    }
}