package com.advance.calculator.tollsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.advance.calculator.R;


public class UnitConverter extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.area:
                Intent i = new Intent(UnitConverter.this, UnitArea.class);
                UnitConverter.this.startActivity(i);
                return;
            case R.id.length:
                Intent i1 = new Intent(UnitConverter.this, UnitLength.class);
                UnitConverter.this.startActivity(i1);
                return;
            case R.id.temperature:
                Intent i3 = new Intent(UnitConverter.this, UnitTemperature.class);
                UnitConverter.this.startActivity(i3);
                return;
            case R.id.weight:
                Intent i4 = new Intent(UnitConverter.this, UnitWeight.class);
                UnitConverter.this.startActivity(i4);
                return;
            default:
                return;
        }
    }
}
