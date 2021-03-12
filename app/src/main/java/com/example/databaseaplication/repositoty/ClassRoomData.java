package com.example.databaseaplication.repositoty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.databaseaplication.Model.ClassRoomModel;
import com.example.databaseaplication.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ClassRoomData {
    private SQLiteDatabase database;



    @RequiresApi(api = Build.VERSION_CODES.P)
    public ClassRoomData(Context context) {
        this.database=new DbHelper(context).getWritableDatabase();
    }

    public List<ClassRoomModel> getAllClassRooms(){
        Cursor cursor=database.query("classroom",null,null,null,null,null,null);
        ArrayList<ClassRoomModel> dataClassRoom =new ArrayList<>();

        if(cursor.moveToFirst()){

           int idIndex=cursor.getColumnIndex("_id");
           int nameIndex=cursor.getColumnIndex("name");
           int roomNumberIndex=cursor.getColumnIndex("roomNumber");
           int levelIndex=cursor.getColumnIndex("level");
           int typeOfClassIndex=cursor.getColumnIndex("typeOfClass");
           do{
                Integer id=cursor.getInt(idIndex);
                String name=cursor.getString(nameIndex);
                Integer numberRoom=cursor.getInt(roomNumberIndex);
                Integer level=cursor.getInt(levelIndex);
                String typeOfClass=cursor.getString(typeOfClassIndex);
                dataClassRoom.add(new ClassRoomModel(id,name,typeOfClass,numberRoom,level));
           }while (cursor.moveToNext());
        }
        return dataClassRoom;
    }

    public void insertData(){
        new Thread(() -> {
            ContentValues contentValues=new ContentValues();
            contentValues.put("name","english");
            contentValues.put("roomNumber",245);
            contentValues.put("level",3);
            contentValues.put("typeOfClass","Univers");
            database.insert("classroom",null,contentValues);
        }).start();
    }





}
