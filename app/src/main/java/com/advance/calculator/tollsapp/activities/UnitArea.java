package com.advance.calculator.tollsapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.advance.calculator.R;
import com.advance.calculator.tollsapp.utils.ConvertingUnits;


public class UnitArea extends AppCompatActivity {
    private ConvertingUnits.Area ca;
    private int count1 = 0;
    private EditText e1;
    private EditText e2;
    private Spinner s1;
    private Spinner s2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_area);
        this.e1 = (EditText) findViewById(R.id.item1);
        this.e2 = (EditText) findViewById(R.id.item2);
        this.s1 = (Spinner) findViewById(R.id.spinner1);
        this.s2 = (Spinner) findViewById(R.id.spinner2);
        this.ca = new ConvertingUnits.Area();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                super.onBackPressed();
                return;
            case R.id.backSpace:
                if (this.e1.length() != 0) {
                    String text = this.e1.getText().toString();
                    if (text.endsWith(".")) {
                        this.count1 = 0;
                    }
                    String newText = text.substring(0, text.length() - 1);
                    this.e1.setText(newText);
                    return;
                }
                return;
            case R.id.clear:
                this.e1.setText("");
                this.e2.setText("");
                this.count1 = 0;
                return;
            case R.id.dot:
                if (this.count1 == 0) {
                    EditText editText = this.e1;
                    editText.setText(((Object) this.e1.getText()) + ".");
                    this.count1 = this.count1 + 1;
                    return;
                }
                return;
            case R.id.equal:
                try {
                    int item1 = this.s1.getSelectedItemPosition();
                    int item2 = this.s2.getSelectedItemPosition();
                    double value1 = Double.parseDouble(this.e1.getText().toString());
                    double result = evaluate(item1, item2, value1);
                    EditText editText2 = this.e2;
                    editText2.setText(result + "");
                    return;
                } catch (Exception e) {
                    return;
                }
            case R.id.num0:
                EditText editText3 = this.e1;
                editText3.setText(((Object) this.e1.getText()) + "0");
                return;
            case R.id.num1:
                EditText editText4 = this.e1;
                editText4.setText(((Object) this.e1.getText()) + "1");
                return;
            case R.id.num2:
                EditText editText5 = this.e1;
                editText5.setText(((Object) this.e1.getText()) + "2");
                return;
            case R.id.num3:
                EditText editText6 = this.e1;
                editText6.setText(((Object) this.e1.getText()) + "3");
                return;
            case R.id.num4:
                EditText editText7 = this.e1;
                editText7.setText(((Object) this.e1.getText()) + "4");
                return;
            case R.id.num5:
                EditText editText8 = this.e1;
                editText8.setText(((Object) this.e1.getText()) + "5");
                return;
            case R.id.num6:
                EditText editText9 = this.e1;
                editText9.setText(((Object) this.e1.getText()) + "6");
                return;
            case R.id.num7:
                EditText editText10 = this.e1;
                editText10.setText(((Object) this.e1.getText()) + "7");
                return;
            case R.id.num8:
                EditText editText11 = this.e1;
                editText11.setText(((Object) this.e1.getText()) + "8");
                return;
            case R.id.num9:
                EditText editText12 = this.e1;
                editText12.setText(((Object) this.e1.getText()) + "9");
                return;
            default:
                return;
        }
    }

    public double evaluate(int item1, int item2, double value) {
        double temp = 0.0d;
        if (item1 == item2) {
            return value;
        }
        switch (item1) {
            case 0:
                temp = this.ca.sqMilliToMeter(value);
                break;
            case 1:
                temp = this.ca.sqCentiToMeter(value);
                break;
            case 2:
                temp = value;
                break;
            case 3:
                temp = this.ca.sqKiloToMeter(value);
                break;
            case 4:
                temp = this.ca.AcreToMeter(value);
                break;
            case 5:
                temp = this.ca.HectareToMeter(value);
                break;
        }
        switch (item2) {
            case 0:
                return this.ca.sqMeterToMilli(temp);
            case 1:
                return this.ca.sqMeterToCenti(temp);
            case 2:
            default:
                return temp;
            case 3:
                return this.ca.sqMeterToKilo(temp);
            case 4:
                return this.ca.sqMeterToAcre(temp);
            case 5:
                return this.ca.sqMeterToHectare(temp);
        }
    }
}
