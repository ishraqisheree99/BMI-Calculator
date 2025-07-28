package com.example.bmi_calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class BMIResult implements Parcelable {
    private double bmiValue;
    private boolean isMale;
    private int age;
    private String category;
    private String description;
    private int categoryColor;

    public BMIResult(double bmiValue, boolean isMale, int age) {
        this.bmiValue = bmiValue;
        this.isMale = isMale;
        this.age = age;
        calculateCategory();
    }

    protected BMIResult(Parcel in) {
        bmiValue = in.readDouble();
        isMale = in.readByte() != 0;
        age = in.readInt();
        category = in.readString();
        description = in.readString();
        categoryColor = in.readInt();
    }

    public static final Creator<BMIResult> CREATOR = new Creator<BMIResult>() {
        @Override
        public BMIResult createFromParcel(Parcel in) {
            return new BMIResult(in);
        }

        @Override
        public BMIResult[] newArray(int size) {
            return new BMIResult[size];
        }
    };

    private void calculateCategory() {
        if (bmiValue < 18.5) {
            category = "UNDERWEIGHT";
            description = "You have a lower than normal body weight. You can eat a bit more.";
            categoryColor = R.color.underweight;
        } else if (bmiValue >= 18.5 && bmiValue < 25) {
            category = "NORMAL";
            description = "You have a normal body weight. Good job!";
            categoryColor = R.color.normal;
        } else if (bmiValue >= 25 && bmiValue < 30) {
            category = "OVERWEIGHT";
            description = "You have a higher than normal body weight. Try to exercise more.";
            categoryColor = R.color.overweight;
        } else {
            category = "OBESE";
            description = "You have a much higher than normal body weight. Please consult a doctor.";
            categoryColor = R.color.obese;
        }
    }

    // Getters
    public double getBmiValue() {
        return bmiValue;
    }

    public boolean isMale() {
        return isMale;
    }

    public int getAge() {
        return age;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryColor() {
        return categoryColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(bmiValue);
        dest.writeByte((byte) (isMale ? 1 : 0));
        dest.writeInt(age);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeInt(categoryColor);
    }
}