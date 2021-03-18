package com.example.databaseaplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.IntentReceiverLeakedViolation;

import com.example.databaseaplication.Model.ClassRoomModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

public class GetDataFromDB {
    private SQLiteDatabase database;

    public GetDataFromDB(Context context) {
        this.database = new DbHelper(context).getWritableDatabase();
    }

    public List<ClassRoomModel> getAllClassRooms() {
        Cursor cursor = database.query(DbHelper.TABLE_NAME_OF_CLASS, null, null, null, null, null, null);
        ArrayList<ClassRoomModel> dataClassRoom = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int idIndex = cursor.getColumnIndex(DbHelper.ID);
            int nameIndex = cursor.getColumnIndex(DbHelper.NAME);
            int roomNumberIndex = cursor.getColumnIndex(DbHelper.ROOM_NUMBER);
            int levelIndex = cursor.getColumnIndex(DbHelper.LEVEL);
            int typeOfClassIndex = cursor.getColumnIndex(DbHelper.TYPE_OF_CLASS);
            do {
                Integer id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                Integer numberRoom = cursor.getInt(roomNumberIndex);
                Integer level = cursor.getInt(levelIndex);
                String typeOfClass = cursor.getString(typeOfClassIndex);
                dataClassRoom.add(new ClassRoomModel(id, name, typeOfClass, numberRoom, level));
            } while (cursor.moveToNext());
        }
        return dataClassRoom;
    }


    public long addClassRoom(ClassRoomModel classRoom) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME, classRoom.getName());
        contentValues.put(DbHelper.ROOM_NUMBER, classRoom.getNumberRoom());
        contentValues.put(DbHelper.LEVEL, classRoom.getLevel());
        contentValues.put(DbHelper.TYPE_OF_CLASS, classRoom.getTypeOfClass());
        return database.insert(DbHelper.TABLE_NAME_OF_CLASS, null, contentValues);
    }


    public int deleteClassRoom(int id) {
        int result = database.delete(DbHelper.TABLE_NAME_OF_CLASS, "_id= " + id, null);
        database.execSQL("DELETE FROM "+DbHelper.TABLE_NAME_OF_CLASS + " WHERE "+ DbHelper.ID+ " = "+ id+";");
        //database.execSQL("UPDATE " + DbHelper.TABLE_NAME_OF_CLASS + " SET " + DbHelper.ID + " = " + DbHelper.ID + " -1 " + " WHERE " + DbHelper.ID + " > " + id + ";");
        database.execSQL(" DELETE FROM "+ "sqlite_sequence"  + " WHERE "+ "name" +" = " + DbHelper.TABLE_NAME_OF_CLASS+";");
        return 1;
    }

    public int updateClassRoom(ClassRoomModel classRoomModel) {
        Integer id = classRoomModel.getId();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME, classRoomModel.getName());
        contentValues.put(DbHelper.ROOM_NUMBER, classRoomModel.getNumberRoom());
        contentValues.put(DbHelper.LEVEL, classRoomModel.getLevel());
        contentValues.put(DbHelper.TYPE_OF_CLASS, classRoomModel.getTypeOfClass());
        return database.update(DbHelper.TABLE_NAME_OF_CLASS, contentValues, "_id= " + id.toString(), null);
    }
}
