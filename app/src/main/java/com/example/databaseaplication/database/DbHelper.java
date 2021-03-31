package com.example.databaseaplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME_OF_CLASS = "classroom";
    public static final String TABLE_NAME_OF_STUDENTS = "students";
    public static final String TABLE_NAME_OF_MARKS = "marks";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String ROOM_NUMBER = "roomNumber";
    public static final String LEVEL = "level";
    public static final String TYPE_OF_CLASS = "typeOfClass";
    public static final String FIRST_NAME = "first_name";
    public static final String SECOND_NAME = "second_name";
    public static final String CLASS_ID = "class_id";
    public static final String GENDER = "gender";
    public static final String AGE = "age";
    public static final String SUBJECT_NAME = "subject_name";
    public static final String STUDENT_ID = "student_id";
    public static final String MARK = "mark";
    public static final String DATA_MARK = "data_mark";


    public DbHelper(@Nullable Context context) {
        super(context, "classroom", null, 3);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_OF_CLASS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,roomNumber INTEGER,level INTEGER,typeOfClass TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_OF_STUDENTS + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FIRST_NAME + " TEXT," + SECOND_NAME + " TEXT," + CLASS_ID + " INTEGER," + GENDER + " TEXT," + AGE + " INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_OF_MARKS + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SUBJECT_NAME + " TEXT," + STUDENT_ID + " INTEGER," + MARK + " INTEGER," + DATA_MARK + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
