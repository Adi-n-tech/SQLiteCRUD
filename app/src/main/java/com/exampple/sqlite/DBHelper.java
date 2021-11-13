package com.exampple.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
        String query = "CREATE TABLE EMP (NAME TEXT,AGE INTEGER)";
        sqLiteDatabase.execSQL(query);
    }

    public void addEmp(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("AGE", age);
        db.insert("EMP", null, values);
        db.close();
    }

    public ArrayList<Emp> readEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Emp> empArrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM EMP WHERE AGE>10", null);

        if (cursor.moveToFirst()) {
            do {
                empArrayList.add(new Emp(cursor.getString(0), cursor.getInt(1)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return empArrayList;
    }

    public void deleterEntry(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "EMP"+ " WHERE "+"NAME"+"='"+name+"'");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "EMP");
        onCreate(sqLiteDatabase);
    }
}
