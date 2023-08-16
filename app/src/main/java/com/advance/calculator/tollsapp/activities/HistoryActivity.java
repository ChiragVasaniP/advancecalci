package com.advance.calculator.tollsapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.advance.calculator.R;
import com.advance.calculator.tollsapp.utils.DBHelper;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private DBHelper dbHelper;
    private ArrayList<String> list;
    private ListView lv;
    private String calcName = "";
    private String[] emptyList = {"There is no history yet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_history);

        lv = findViewById(R.id.listView);
        dbHelper = new DBHelper(this);

        String stringExtra = getIntent().getStringExtra("calcName");
        calcName = stringExtra;

        ArrayList<String> showHistory = dbHelper.showHistory(stringExtra);
        list = showHistory;

        if (!showHistory.isEmpty()) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        } else {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emptyList);
        }

        lv.setAdapter((ListAdapter) adapter);
    }

    public void onClick(View v) {
        dbHelper.deleteRecords(calcName);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emptyList);
        adapter = arrayAdapter;
        lv.setAdapter((ListAdapter) arrayAdapter);
    }
}
