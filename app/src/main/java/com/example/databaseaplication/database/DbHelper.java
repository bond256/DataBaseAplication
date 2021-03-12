package com.example.databaseaplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DbHelper extends SQLiteOpenHelper {
    private String name;
    private Integer version;
    private final String TABLE_NAME_OF_CLASS="classroom";
    final String ID="_id";
    final String NAME="name";
    final String ROOM="roomNumber";
    final String LEVEL="level";

    public DbHelper(@Nullable Context context) {
        super(context, "classroom", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_OF_CLASS+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,roomNumber INTEGER,level INTEGER,typeOfClass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
