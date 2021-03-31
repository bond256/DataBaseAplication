package com.example.databaseaplication.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.databaseaplication.model.MarksModel;

import java.util.ArrayList;
import java.util.List;

public class GetSearchDataFromDb {
    private SQLiteDatabase database;

    public GetSearchDataFromDb(Context context) {
        this.database = new DbHelper(context).getReadableDatabase();
    }

    public void getStudentByParam(String param){
        //database.rawQuery("Select * student")
    }

}
