package com.advance.calculator.tollsapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.advance.calculator.R;
import com.advance.calculator.tollsapp.utils.ConvertingUnits;


public class UnitWeight extends AppCompatActivity {
    private ConvertingUnits.Weight ca;
    private int count1 = 0;
    private EditText e1;
    private EditText e2;
    private Spinner s1;
    private Spinner s2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_weight);
        e1 = (EditText) findViewById(R.id.item1);
        e2 = (EditText) findViewById(R.id.item2);
        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        ca = new ConvertingUnits.Weight();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                super.onBackPressed();
                return;
            case R.id.backSpace:
                if (e1.length() != 0) {
                    String text = e1.getText().toString();
                    if (text.endsWith(".")) {
                        count1 = 0;
                    }
                    String newText = text.substring(0, text.length() - 1);
                    e1.setText(newText);
                    return;
                }
                return;
            case R.id.clear:
                e1.setText("");
                e2.setText("");
                count1 = 0;
                return;
            case R.id.dot:
                int item1 = count1;
                if (item1 == 0) {
                    EditText editText = e1;
                    editText.setText(((Object) e1.getText()) + ".");
                    count1 = count1 + 1;
                    return;
                }
                return;
            case R.id.equal:
                int item12 = s1.getSelectedItemPosition();
                int item2 = s2.getSelectedItemPosition();
                double value1 = Double.parseDouble(e1.getText().toString());
                double result = evaluate(item12, item2, value1);
                EditText editText2 = e2;
                editText2.setText(result + "");
                return;
            case R.id.num0:
                EditText editText3 = e1;
                editText3.setText(((Object) e1.getText()) + "0");
                return;
            case R.id.num1:
                EditText editText4 = e1;
                editText4.setText(((Object) e1.getText()) + "1");
                return;
            case R.id.num2:
                EditText editText5 = e1;
                editText5.setText(((Object) e1.getText()) + "2");
                return;
            case R.id.num3:
                EditText editText6 = e1;
                editText6.setText(((Object) e1.getText()) + "3");
                return;
            case R.id.num4:
                EditText editText7 = e1;
                editText7.setText(((Object) e1.getText()) + "4");
                return;
            case R.id.num5:
                EditText editText8 = e1;
                editText8.setText(((Object) e1.getText()) + "5");
                return;
            case R.id.num6:
                EditText editText9 = e1;
                editText9.setText(((Object) e1.getText()) + "6");
                return;
            case R.id.num7:
                EditText editText10 = e1;
                editText10.setText(((Object) e1.getText()) + "7");
                return;
            case R.id.num8:
                EditText editText11 = e1;
                editText11.setText(((Object) e1.getText()) + "8");
                return;
            case R.id.num9:
                EditText editText12 = e1;
                editText12.setText(((Object) e1.getText()) + "9");
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
                temp = ca.MilliToKilo(value);
                break;
            case 1:
                temp = ca.CentiToKilo(value);
                break;
            case 2:
                temp = ca.DeciToKilo(value);
                break;
            case 3:
                temp = ca.GramToKilo(value);
                break;
            case 4:
                temp = value;
                break;
            case 5:
                temp = ca.MetricTonnesToKilo(value);
                break;
            case 6:
                temp = ca.PoundsToKilo(value);
                break;
            case 7:
                temp = ca.OuncesToKilo(value);
                break;
        }
        switch (item2) {
            case 0:
                return ca.KiloToMilli(temp);
            case 1:
                return ca.KiloToCenti(temp);
            case 2:
                return ca.KiloToDeci(temp);
            case 3:
                return ca.KiloToGram(temp);
            case 4:
            default:
                return temp;
            case 5:
                return ca.KiloToMetricTonnes(temp);
            case 6:
                return ca.KiloToPounds(temp);
            case 7:
                return ca.KiloToOunces(temp);
        }
    }
}
