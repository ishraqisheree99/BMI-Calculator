package com.example.bmi_calculator;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {

    private TextView tvBMICategory;
    private TextView tvBMIValue;
    private TextView tvBMIDescription;
    private MaterialButton btnRecalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initializeViews();
        displayResult();
        setListeners();
    }

    private void initializeViews() {
        tvBMICategory = findViewById(R.id.tvBMICategory);
        tvBMIValue = findViewById(R.id.tvBMIValue);
        tvBMIDescription = findViewById(R.id.tvBMIDescription);
        btnRecalculate = findViewById(R.id.btnRecalculate);
    }

    private void displayResult() {
        BMIResult result = getIntent().getParcelableExtra("BMI_RESULT");

        if (result != null) {
            tvBMICategory.setText(result.getCategory());
            tvBMIValue.setText(String.format("%.1f", result.getBmiValue()));
            tvBMIDescription.setText(result.getDescription());

            // Set category color
            int color = ContextCompat.getColor(this, result.getCategoryColor());
            tvBMICategory.setTextColor(color);
        }
    }

    private void setListeners() {
        btnRecalculate.setOnClickListener(v -> {
            finish(); // Go back to MainActivity
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}