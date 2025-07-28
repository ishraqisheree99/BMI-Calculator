package com.example.bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import com.example.bmi_calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isMaleSelected = true;
    private int currentWeight = 80;
    private int currentAge = 18;
    private int currentHeight = 180;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

        initializeViews();
        setListeners();
        updateGenderSelection();
    }

    private void initializeViews() {
        binding.tvWeight.setText(String.valueOf(currentWeight));
        binding.tvAge.setText(String.valueOf(currentAge));
        binding.tvHeight.setText(String.valueOf(currentHeight));
        binding.seekBarHeight.setProgress(currentHeight - 120); // 120cm to 240cm range
    }

    private void setListeners() {
        // Gender selection
        binding.cardMale.setOnClickListener(v -> {
            isMaleSelected = true;
            updateGenderSelection();
        });

        binding.cardFemale.setOnClickListener(v -> {
            isMaleSelected = false;
            updateGenderSelection();
        });

        // Height slider
        binding.seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentHeight = progress + 120; // 120cm to 240cm
                binding.tvHeight.setText(String.valueOf(currentHeight));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Weight buttons
        binding.btnIncreaseWeight.setOnClickListener(v -> {
            if (currentWeight < 200) {
                currentWeight++;
                binding.tvWeight.setText(String.valueOf(currentWeight));
            }
        });

        binding.btnDecreaseWeight.setOnClickListener(v -> {
            if (currentWeight > 1) {
                currentWeight--;
                binding.tvWeight.setText(String.valueOf(currentWeight));
            }
        });

        // Age buttons
        binding.btnIncreaseAge.setOnClickListener(v -> {
            if (currentAge < 120) {
                currentAge++;
                binding.tvAge.setText(String.valueOf(currentAge));
            }
        });

        binding.btnDecreaseAge.setOnClickListener(v -> {
            if (currentAge > 1) {
                currentAge--;
                binding.tvAge.setText(String.valueOf(currentAge));
            }
        });

        // Calculate button
        binding.btnCalculate.setOnClickListener(v -> calculateBMI());
    }

    private void updateGenderSelection() {
        if (isMaleSelected) {
            binding.cardMale.setCardBackgroundColor(getResources().getColor(R.color.accent_gold));
            binding.cardFemale.setCardBackgroundColor(getResources().getColor(R.color.card_background));
        } else {
            binding.cardFemale.setCardBackgroundColor(getResources().getColor(R.color.accent_gold));
            binding.cardMale.setCardBackgroundColor(getResources().getColor(R.color.card_background));
        }
    }

    private void calculateBMI() {
        double heightInMeters = currentHeight / 100.0;
        double bmi = currentWeight / (heightInMeters * heightInMeters);

        BMIResult result = new BMIResult(bmi, isMaleSelected, currentAge);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("BMI_RESULT", result);
        startActivity(intent);
    }
}