package com.advance.calculator.tollsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.advance.calculator.R;

public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(1024, 1024);
    }

    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.btnNormalCalculator:
                i = new Intent(MainActivity.this, StandardCal.class);
                startActivity(i);
                break;
            case R.id.btnScientificCal:
                i = new Intent(MainActivity.this, ScientificCal.class);
                startActivity(i);
                break;
            case R.id.btnUnitCal:
                i = new Intent(MainActivity.this, UnitConverter.class);
                startActivity(i);
                break;
            case R.id.btnAgeCal:
                i = new Intent(MainActivity.this, AgeCalculatorActivity.class);
                startActivity(i);
                break;
            case R.id.btnBMICal:
                i = new Intent(MainActivity.this, BMICalculatorActivity.class);
                startActivity(i);
                break;
            case R.id.btnEMI:
                i = new Intent(MainActivity.this, EMIActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        showBackDialog();
    }

    public void showBackDialog() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }
        doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(), "Double tap to exit", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000L);
    }
}
