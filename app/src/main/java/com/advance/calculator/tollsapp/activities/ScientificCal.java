package com.advance.calculator.tollsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.advance.calculator.R;
import com.advance.calculator.tollsapp.utils.CalculateFactorial;
import com.advance.calculator.tollsapp.utils.DBHelper;
import com.advance.calculator.tollsapp.utils.ExtendedDoubleEvaluator;


public class ScientificCal extends AppCompatActivity {
    private TextView cos;
    private DBHelper dbHelper;
    private EditText e1;
    private EditText e2;
    private TextView fact;
    private TextView log;
    private TextView mode;
    private TextView sin;
    private TextView sqrt;
    private TextView square;
    private TextView tan;
    private TextView toggle;
    private TextView xpowy;
    private int count = 0;
    private String expression = "";
    private String text = "";
    private Double result = Double.valueOf(0.0d);
    private int toggleMode = 1;
    private int angleMode = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_scientific_cal);
        this.e1 = (EditText) findViewById(R.id.editText1);
        this.e2 = (EditText) findViewById(R.id.editText2);
        this.mode = (TextView) findViewById(R.id.mode);
        this.toggle = (TextView) findViewById(R.id.toggle);
        this.square = (TextView) findViewById(R.id.square);
        this.xpowy = (TextView) findViewById(R.id.xpowy);
        this.log = (TextView) findViewById(R.id.log);
        this.sin = (TextView) findViewById(R.id.sin);
        this.cos = (TextView) findViewById(R.id.cos);
        this.tan = (TextView) findViewById(R.id.tan);
        this.sqrt = (TextView) findViewById(R.id.sqrt);
        this.fact = (TextView) findViewById(R.id.factorial);
        this.dbHelper = new DBHelper(this);
        this.mode.setTag(1);
        this.toggle.setTag(1);
    }

    public void onClick(View v) {
        this.toggleMode = ((Integer) this.toggle.getTag()).intValue();
        this.angleMode = ((Integer) this.mode.getTag()).intValue();
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
                    if (newText.equals("-") || newText.endsWith("sqrt") || newText.endsWith("log") || newText.endsWith("ln") || newText.endsWith("sin") || newText.endsWith("asin") || newText.endsWith("asind") || newText.endsWith("sinh") || newText.endsWith("cos") || newText.endsWith("acos") || newText.endsWith("acosd") || newText.endsWith("cosh") || newText.endsWith("tan") || newText.endsWith("atan") || newText.endsWith("atand") || newText.endsWith("tanh") || newText.endsWith("cbrt")) {
                        newText = "";
                    } else if (newText.endsWith("^") || newText.endsWith("/")) {
                        newText = newText.substring(0, newText.length() - 1);
                    } else if (newText.endsWith("pi") || newText.endsWith("e^")) {
                        newText = newText.substring(0, newText.length() - 2);
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
                if (this.e2.length() != 0) {
                    this.e1.setText(((Object) this.e1.getText()) + this.e2.getText().toString() + ")");
                    return;
                }
                this.e1.setText(((Object) this.e1.getText()) + ")");
                return;
            case R.id.cos:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    if (this.angleMode == 1) {
                        double angle = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(this.text).doubleValue());
                        int i2 = this.toggleMode;
                        if (i2 == 1) {
                            this.e2.setText("cos(" + angle + ")");
                            return;
                        } else if (i2 == 2) {
                            this.e2.setText("acosd(" + this.text + ")");
                            return;
                        } else {
                            this.e2.setText("cosh(" + this.text + ")");
                            return;
                        }
                    }
                    int i3 = this.toggleMode;
                    if (i3 == 1) {
                        this.e2.setText("cos(" + this.text + ")");
                        return;
                    } else if (i3 == 2) {
                        this.e2.setText("acos(" + this.text + ")");
                        return;
                    } else {
                        this.e2.setText("cosh(" + this.text + ")");
                        return;
                    }
                }
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
                try {
                    Double evaluate = new ExtendedDoubleEvaluator().evaluate(this.expression);
                    this.result = evaluate;
                    if (String.valueOf(evaluate).equals("6.123233995736766E-17")) {
                        this.result = Double.valueOf(0.0d);
                        this.e2.setText(this.result + "");
                    } else if (String.valueOf(this.result).equals("1.633123935319537E16")) {
                        this.e2.setText("infinity");
                    } else {
                        this.e2.setText(this.result + "");
                    }
                    if (!this.expression.equals("0.0")) {
                        this.dbHelper.insert("SCIENTIFIC", this.expression + " = " + this.result);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    this.e2.setText("Invalid Expression");
                    this.e1.setText("");
                    this.expression = "";
                    e.printStackTrace();
                    return;
                }
            case R.id.factorial:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    if (this.toggleMode == 2) {
                        this.e1.setText("(" + this.text + ")%");
                        this.e2.setText("");
                        return;
                    }
                    String res = "";
                    try {
                        CalculateFactorial cf = new CalculateFactorial();
                        int[] arr = cf.factorial((int) Double.parseDouble(String.valueOf(new ExtendedDoubleEvaluator().evaluate(this.text))));
                        int res_size = cf.getRes();
                        if (res_size > 20) {
                            for (int i4 = res_size - 1; i4 >= res_size - 20; i4--) {
                                if (i4 == res_size - 2) {
                                    res = res + ".";
                                }
                                res = res + arr[i4];
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append(res);
                            sb.append("E");
                            sb.append(res_size - 1);
                            res = sb.toString();
                        } else {
                            for (int i5 = res_size - 1; i5 >= 0; i5--) {
                                res = res + arr[i5];
                            }
                        }
                        this.e2.setText(res);
                        return;
                    } catch (Exception e2) {
                        if (e2.toString().contains("ArrayIndexOutOfBoundsException")) {
                            this.e2.setText("Result too big!");
                        } else {
                            this.e2.setText("Invalid!!");
                        }
                        e2.printStackTrace();
                        return;
                    }
                }
                return;
            case R.id.history:
                Intent i6 = new Intent(this, HistoryActivity.class);
                i6.putExtra("calcName", "SCIENTIFIC");
                startActivity(i6);
                return;
            case R.id.log:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    if (this.toggleMode == 2) {
                        this.e2.setText("ln(" + this.text + ")");
                        return;
                    }
                    this.e2.setText("log(" + this.text + ")");
                    return;
                }
                return;
            case R.id.minus:
                operationClicked("-");
                return;
            case R.id.mode:
                if (this.angleMode == 1) {
                    this.mode.setTag(2);
                    this.mode.setText(R.string.mode2);
                    return;
                }
                this.mode.setTag(1);
                this.mode.setText(R.string.mode1);
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
            case R.id.pi:
                this.e2.setText(((Object) this.e2.getText()) + "pi");
                return;
            case R.id.plus:
                operationClicked("+");
                return;
            case R.id.posneg:
                if (this.e2.length() != 0) {
                    String s = this.e2.getText().toString();
                    if (s.toCharArray()[0] == '-') {
                        this.e2.setText(s.substring(1, s.length()));
                        return;
                    }
                    this.e2.setText("-" + s);
                    return;
                }
                return;
            case R.id.sin:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    if (this.angleMode == 1) {
                        double angle2 = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(this.text).doubleValue());
                        int i7 = this.toggleMode;
                        if (i7 == 1) {
                            this.e2.setText("sin(" + angle2 + ")");
                            return;
                        } else if (i7 == 2) {
                            this.e2.setText("asind(" + this.text + ")");
                            return;
                        } else {
                            this.e2.setText("sinh(" + this.text + ")");
                            return;
                        }
                    }
                    int i8 = this.toggleMode;
                    if (i8 == 1) {
                        this.e2.setText("sin(" + this.text + ")");
                        return;
                    } else if (i8 == 2) {
                        this.e2.setText("asin(" + this.text + ")");
                        return;
                    } else {
                        this.e2.setText("sinh(" + this.text + ")");
                        return;
                    }
                }
                return;
            case R.id.sqrt:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    int intValue = ((Integer) this.toggle.getTag()).intValue();
                    this.toggleMode = intValue;
                    if (intValue == 1) {
                        this.e2.setText("sqrt(" + this.text + ")");
                        return;
                    } else if (intValue == 2) {
                        this.e2.setText("cbrt(" + this.text + ")");
                        return;
                    } else {
                        this.e2.setText("1/(" + this.text + ")");
                        return;
                    }
                }
                return;
            case R.id.square:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    if (this.toggleMode == 2) {
                        this.e2.setText("(" + this.text + ")^3");
                        return;
                    }
                    this.e2.setText("(" + this.text + ")^2");
                    return;
                }
                return;
            case R.id.tan:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    if (this.angleMode == 1) {
                        double angle3 = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(this.text).doubleValue());
                        int i9 = this.toggleMode;
                        if (i9 == 1) {
                            this.e2.setText("tan(" + angle3 + ")");
                            return;
                        } else if (i9 == 2) {
                            this.e2.setText("atand(" + this.text + ")");
                            return;
                        } else {
                            this.e2.setText("tanh(" + this.text + ")");
                            return;
                        }
                    }
                    int i10 = this.toggleMode;
                    if (i10 == 1) {
                        this.e2.setText("tan(" + this.text + ")");
                        return;
                    } else if (i10 == 2) {
                        this.e2.setText("atan(" + this.text + ")");
                        return;
                    } else {
                        this.e2.setText("tanh(" + this.text + ")");
                        return;
                    }
                }
                return;
            case R.id.toggle:
                int i11 = this.toggleMode;
                if (i11 == 1) {
                    this.toggle.setTag(2);
                    this.square.setText(R.string.cube);
                    this.xpowy.setText(R.string.tenpow);
                    this.log.setText(R.string.naturalLog);
                    this.sin.setText(R.string.sininv);
                    this.cos.setText(R.string.cosinv);
                    this.tan.setText(R.string.taninv);
                    this.sqrt.setText(R.string.cuberoot);
                    this.fact.setText(R.string.Mod);
                    return;
                } else if (i11 == 2) {
                    this.toggle.setTag(3);
                    this.square.setText(R.string.square);
                    this.xpowy.setText(R.string.epown);
                    this.log.setText(R.string.log);
                    this.sin.setText(R.string.hyperbolicSine);
                    this.cos.setText(R.string.hyperbolicCosine);
                    this.tan.setText(R.string.hyperbolicTan);
                    this.sqrt.setText(R.string.inverse);
                    this.fact.setText(R.string.factorial);
                    return;
                } else if (i11 == 3) {
                    this.toggle.setTag(1);
                    this.sin.setText(R.string.sin);
                    this.cos.setText(R.string.cos);
                    this.tan.setText(R.string.tan);
                    this.sqrt.setText(R.string.sqrt);
                    this.xpowy.setText(R.string.xpown);
                    return;
                } else {
                    return;
                }
            case R.id.xpowy:
                if (this.e2.length() != 0) {
                    this.text = this.e2.getText().toString();
                    int i12 = this.toggleMode;
                    if (i12 == 1) {
                        this.e2.setText("(" + this.text + ")^");
                        return;
                    } else if (i12 == 2) {
                        this.e2.setText("10^(" + this.text + ")");
                        return;
                    } else {
                        this.e2.setText("e^(" + this.text + ")");
                        return;
                    }
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
