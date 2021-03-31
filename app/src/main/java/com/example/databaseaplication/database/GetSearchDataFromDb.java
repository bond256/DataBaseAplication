package com.example.databaseaplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GetSearchDataFromDb {
    private SQLiteDatabase database;

    public GetSearchDataFromDb(Context context) {
        this.database = new DbHelper(context).getReadableDatabase();
    }

    public void getStudentByParam(String param) {
        //database.rawQuery("Select * student")
    }

}
