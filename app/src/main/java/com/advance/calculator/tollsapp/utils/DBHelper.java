package com.advance.calculator.tollsapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DATABASE OPERATIONS";
    private static final String column1 = "calculator_name";
    private static final String column2 = "expression";
    private static final String create_Table = "CREATE TABLE history(calculator_name TEXT,expression TEXT);";
    private static final String database_Name = "HISTORY.DB";
    private static final int database_Version = 1;
    private static final String table_Name = "history";
    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_Name, null, database_Version);
        Log.i(TAG, "Database Created / Opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_Table);
        Log.i(TAG, "Table Created");
    }

    public void insert(String calcName, String expression) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column1, calcName);
        contentValues.put(column2, expression);
        db.insert(table_Name, null, contentValues);
        db.close();
    }

    public ArrayList<String> showHistory(String calcName) {
        db = getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        String[] selectionArgs = {calcName};
        Cursor cursor = db.rawQuery("SELECT * FROM history WHERE calculator_name = ?", selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                String expression = cursor.getString(1);
                list.add(expression);
            } while (cursor.moveToNext());
            db.close();
            return list;
        }
        db.close();
        return list;
    }

    public void deleteRecords(String calcName) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        db = writableDatabase;
        String[] value = {calcName};
        writableDatabase.delete(table_Name, "calculator_name=?", value);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement if needed
    }
}
