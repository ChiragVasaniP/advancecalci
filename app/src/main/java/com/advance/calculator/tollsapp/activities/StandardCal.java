package com.advance.calculator.tollsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.advance.calculator.R;
import com.advance.calculator.tollsapp.utils.DBHelper;
import com.advance.calculator.tollsapp.utils.ExtendedDoubleEvaluator;


public class StandardCal extends AppCompatActivity {
    private DBHelper dbHelper;
    private EditText e1;
    private EditText e2;
    private int count = 0;
    private String expression = "";
    private String text = "";
    private Double result = Double.valueOf(0.0d);

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_standard_cal);
        this.e1 = (EditText) findViewById(R.id.editText1);
        this.e2 = (EditText) findViewById(R.id.editText2);
        this.dbHelper = new DBHelper(this);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollET2);
        scrollView.fullScroll(1);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                super.onBackPressed();
                return;
            case R.id.backSpace:
                String obj = this.e2.getText().toString();
                this.text = obj;
                if (obj.length() > 0) {
                    if (this.text.endsWith(".")) {
                        this.count = 0;
                    }
                    String str = this.text;
                    String newText = str.substring(0, str.length() - 1);
                    if (this.text.endsWith(")")) {
                        char[] a = this.text.toCharArray();
                        int pos = a.length - 2;
                        int counter = 1;
                        int i = a.length - 2;
                        while (true) {
                            if (i >= 0) {
                                if (a[i] == ')') {
                                    counter++;
                                } else if (a[i] == '(') {
                                    counter--;
                                } else if (a[i] == '.') {
                                    this.count = 0;
                                }
                                if (counter != 0) {
                                    i--;
                                } else {
                                    pos = i;
                                }
                            }
                        }
                    }
                    if (newText.equals("-") || newText.endsWith("sqrt")) {
                        newText = "";
                    } else if (newText.endsWith("^")) {
                        newText = newText.substring(0, newText.length() - 1);
                    }
                    this.e2.setText(newText);
                    return;
                }
                return;
            case R.id.clear:
                this.e1.setText("");
                this.e2.setText("");
                this.count = 0;
                this.expression = "";
                return;
            case R.id.closeBracket:
                this.e1.setText(((Object) this.e1.getText()) + ")");
                return;
            case R.id.divide:
                operationClicked("/");
                return;
            case R.id.dot:
                if (this.count == 0 && this.e2.length() != 0) {
                    this.e2.setText(((Object) this.e2.getText()) + ".");
                    this.count = this.count + 1;
                    return;
                }
                return;
            case R.id.equal:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    this.expression = this.e1.getText().toString() + this.text;
                }
                this.e1.setText("");
                if (this.expression.length() == 0) {
                    this.expression = "0.0";
                }
                new DoubleEvaluator();
                try {
                    this.result = new ExtendedDoubleEvaluator().evaluate(this.expression);
                    if (!this.expression.equals("0.0")) {
                        this.dbHelper.insert("STANDARD", this.expression + " = " + this.result);
                    }
                    this.e2.setText(this.result + "");
                    return;
                } catch (Exception e) {
                    this.e2.setText("Invalid Expression");
                    this.e1.setText("");
                    this.expression = "";
                    e.printStackTrace();
                    return;
                }
            case R.id.history:
                Intent i2 = new Intent(this, HistoryActivity.class);
                i2.putExtra("calcName", "STANDARD");
                startActivity(i2);
                return;
            case R.id.minus:
                operationClicked("-");
                return;
            case R.id.multiply:
                operationClicked("*");
                return;
            case R.id.num0:
                this.e2.setText(((Object) this.e2.getText()) + "0");
                return;
            case R.id.num1:
                this.e2.setText(((Object) this.e2.getText()) + "1");
                return;
            case R.id.num2:
                this.e2.setText(((Object) this.e2.getText()) + "2");
                return;
            case R.id.num3:
                this.e2.setText(((Object) this.e2.getText()) + "3");
                return;
            case R.id.num4:
                this.e2.setText(((Object) this.e2.getText()) + "4");
                return;
            case R.id.num5:
                this.e2.setText(((Object) this.e2.getText()) + "5");
                return;
            case R.id.num6:
                this.e2.setText(((Object) this.e2.getText()) + "6");
                return;
            case R.id.num7:
                this.e2.setText(((Object) this.e2.getText()) + "7");
                return;
            case R.id.num8:
                this.e2.setText(((Object) this.e2.getText()) + "8");
                return;
            case R.id.num9:
                this.e2.setText(((Object) this.e2.getText()) + "9");
                return;
            case R.id.openBracket:
                this.e1.setText(((Object) this.e1.getText()) + "(");
                return;
            case R.id.plus:
                operationClicked("+");
                return;
            case R.id.posneg:
                if (this.e2.length() != 0) {
                    String s = this.e2.getText().toString();
                    char[] arr = s.toCharArray();
                    if (arr[0] == '-') {
                        this.e2.setText(s.substring(1, s.length()));
                        return;
                    }
                    this.e2.setText("-" + s);
                    return;
                }
                return;
            case R.id.sqrt:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    this.e2.setText("sqrt(" + this.text + ")");
                    return;
                }
                return;
            case R.id.square:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    this.e2.setText("(" + this.text + ")^2");
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void operationClicked(String op) {
        if (this.e2.length() != 0) {
            String text = this.e2.getText().toString();
            this.e1.setText(((Object) this.e1.getText()) + text + op);
            this.e2.setText("");
            this.count = 0;
            return;
        }
        String text2 = this.e1.getText().toString();
        if (text2.length() > 0) {
            String newText = text2.substring(0, text2.length() - 1) + op;
            this.e1.setText(newText);
        }
    }
}
