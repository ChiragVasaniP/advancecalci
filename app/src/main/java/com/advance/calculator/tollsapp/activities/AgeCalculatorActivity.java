package com.advance.calculator.tollsapp.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.advance.calculator.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class AgeCalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editTextBirthDay)
    EditText birthDayEditText;
    @BindView(R.id.editTextBirthMonth)
    EditText birthMonthEditText;
    @BindView(R.id.editTextBirthYear)
    EditText birthYearEditText;
    @BindView(R.id.editTextCurrentDay)
    EditText currentDayEditText;
    @BindView(R.id.editTextCurrentMonth)
    EditText currentMonthEditText;
    @BindView(R.id.editTextCurrentYear)
    EditText currentYearEditText;
    @BindView(R.id.imageViewCalenderFirst)
    ImageView calenderFirstImageView;
    @BindView(R.id.imageViewCalenderSecond)
    ImageView calenderSecondImageView;
    @BindView(R.id.textViewCalculate)
    RelativeLayout calculateTextView;
    @BindView(R.id.textViewClear)
    RelativeLayout clearTextView;
    @BindView(R.id.textViewCurrentDay)
    TextView currentDayTextView;
    @BindView(R.id.textViewFinalDays)
    TextView finalDaysTextView;
    @BindView(R.id.textViewFinalMonths)
    TextView finalMonthsTextView;
    @BindView(R.id.textViewFinalYears)
    TextView finalYearsTextView;
    @BindView(R.id.textViewNextBirthdayDays)
    TextView nextBirthdayDaysTextView;
    @BindView(R.id.textViewNextBirthdayMonths)
    TextView nextBirthdayMonthsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_age_calculator);
        ButterKnife.bind(this);
        setupCurrentDate();
        calculateTextView.setOnClickListener(this);
        clearTextView.setOnClickListener(this);
        calenderSecondImageView.setOnClickListener(this);
        calenderFirstImageView.setOnClickListener(this);
    }

    private void setupCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        currentYearEditText.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        currentMonthEditText.setText(addZero(calendar.get(Calendar.MONTH) + 1));
        currentDayEditText.setText(addZero(calendar.get(Calendar.DAY_OF_MONTH)));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Date currentDate = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - 1);
        String dayOfWeek = simpleDateFormat.format(currentDate);
        currentDayTextView.setText(dayOfWeek);
        currentDayTextView.setVisibility(View.VISIBLE);
    }

    private String addZero(int number) {
        return (number < 10) ? "0" + number : String.valueOf(number);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            super.onBackPressed();
        }
        if (view.getId() == R.id.imageViewCalenderSecond) {
            showDatePickerDialog();
        } else if (view == calculateTextView) {
            calculateAgeAndNextBirthday();
        } else if (view == clearTextView) {
            resetFields();
        }
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            birthDayEditText.setText(addZero(dayOfMonth));
            birthMonthEditText.setText(addZero(monthOfYear + 1));
            birthYearEditText.setText(String.valueOf(year));
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void calculateAgeAndNextBirthday() {
        if (!TextUtils.isEmpty(birthDayEditText.getText()) && !TextUtils.isEmpty(birthMonthEditText.getText()) && !TextUtils.isEmpty(birthYearEditText.getText())) {
            calculateAge();
            calculateNextBirthday();
        } else {
            Toasty.warning(this, "All fields are required", 0, true).show();
        }
    }

    private void calculateNextBirthday() {
        int currentDay = Integer.parseInt(currentDayEditText.getText().toString());
        int currentMonth = Integer.parseInt(currentMonthEditText.getText().toString());
        int currentYear = Integer.parseInt(currentYearEditText.getText().toString());
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(currentYear, currentMonth, currentDay);

        int birthDay = Integer.parseInt(birthDayEditText.getText().toString());
        int birthMonth = Integer.parseInt(birthMonthEditText.getText().toString());
        int birthYear = Integer.parseInt(birthYearEditText.getText().toString());
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.set(birthYear, birthMonth, birthDay);

        long difference = birthCalendar.getTimeInMillis() - currentCalendar.getTimeInMillis();
        Calendar diffCalendar = Calendar.getInstance();
        diffCalendar.setTimeInMillis(difference);

        nextBirthdayMonthsTextView.setText(String.valueOf(diffCalendar.get(Calendar.MONTH)));
        nextBirthdayDaysTextView.setText(String.valueOf(diffCalendar.get(Calendar.DAY_OF_MONTH)));
    }

    private void calculateAge() {
        int currentDay = Integer.parseInt(currentDayEditText.getText().toString());
        int currentMonth = Integer.parseInt(currentMonthEditText.getText().toString());
        int currentYear = Integer.parseInt(currentYearEditText.getText().toString());
        Date now = new Date(currentYear, currentMonth, currentDay);

        int birthDay = Integer.parseInt(birthDayEditText.getText().toString());
        int birthMonth = Integer.parseInt(birthMonthEditText.getText().toString());
        int birthYear = Integer.parseInt(birthYearEditText.getText().toString());
        Date dob = new Date(birthYear, birthMonth, birthDay);

        if (dob.after(now)) {
            Toasty.error(this, "Birthday can't be in the future", 0, true).show();
            return;
        }

        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (birthDay > currentDay) {
            currentDay += monthDays[birthMonth - 1];
            currentMonth--;
        }
        if (birthMonth > currentMonth) {
            currentYear--;
            currentMonth += 12;
        }

        int calculatedDays = currentDay - birthDay;
        int calculatedMonths = currentMonth - birthMonth;
        int calculatedYears = currentYear - birthYear;

        finalDaysTextView.setText(String.valueOf(calculatedDays));
        finalMonthsTextView.setText(String.valueOf(calculatedMonths));
        finalYearsTextView.setText(String.valueOf(calculatedYears));
    }

    private void resetFields() {
        birthDayEditText.setText("");
        birthMonthEditText.setText("");
        birthYearEditText.setText("");
        Toasty.success(this, "Successfully reset", 0, true).show();
    }
}
