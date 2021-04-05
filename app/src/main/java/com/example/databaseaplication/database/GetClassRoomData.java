package com.example.databaseaplication.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.databaseaplication.model.ClassRoomModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class GetClassRoomData {
    private SqLiteRxConverter<ClassRoomModel> sqLiteRxConverter;
    private DbHelper dbHelper;

    public GetClassRoomData(Context context) {
        sqLiteRxConverter = new SqLiteRxConverter<>();
        dbHelper = new DbHelper(context);
    }

    @SuppressLint("Recycle")
    public Single<List<ClassRoomModel>> getAllClassRooms() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        return Single.create(emitter -> {
            Cursor cursor = sqLiteDatabase.query(DbHelper.TABLE_NAME_OF_CLASS, null, null, null, null, null, null);
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

            emitter.onSuccess(dataClassRoom);
            cursor.close();
            sqLiteDatabase.close();
        });
    }

    @SuppressLint("Recycle")
    public Single<List<ClassRoomModel>> getClassRoomByName(String name) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        return Single.create(emitter -> {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME_OF_CLASS + " WHERE " + DbHelper.NAME + " = ?", new String[]{name});
            ArrayList<ClassRoomModel> dataClassRoom = new ArrayList<>();
            if (cursor.moveToFirst()) {

                int idIndex = cursor.getColumnIndex(DbHelper.ID);
                int nameIndex = cursor.getColumnIndex(DbHelper.NAME);
                int roomNumberIndex = cursor.getColumnIndex(DbHelper.ROOM_NUMBER);
                int levelIndex = cursor.getColumnIndex(DbHelper.LEVEL);
                int typeOfClassIndex = cursor.getColumnIndex(DbHelper.TYPE_OF_CLASS);
                do {
                    Integer id = cursor.getInt(idIndex);
                    String nameClassRoom = cursor.getString(nameIndex);
                    Integer numberRoom = cursor.getInt(roomNumberIndex);
                    Integer level = cursor.getInt(levelIndex);
                    String typeOfClass = cursor.getString(typeOfClassIndex);
                    dataClassRoom.add(new ClassRoomModel(id, nameClassRoom, typeOfClass, numberRoom, level));
                } while (cursor.moveToNext());
            }
            emitter.onSuccess(dataClassRoom);
            Thread.sleep(20000);
            cursor.close();
            sqLiteDatabase.close();
        });
    }

    public Completable addClassRoom(ClassRoomModel classRoom) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbHelper.NAME, classRoom.getName());
            contentValues.put(DbHelper.ROOM_NUMBER, classRoom.getNumberRoom());
            contentValues.put(DbHelper.LEVEL, classRoom.getLevel());
            contentValues.put(DbHelper.TYPE_OF_CLASS, classRoom.getTypeOfClass());
            long code = sqLiteDatabase.insert(DbHelper.TABLE_NAME_OF_CLASS, null, contentValues);
            Thread.sleep(20000);
            if(code==-1) {
                emitter.onError(new Exception("Error add class room"));
            }else emitter.onComplete();
        });
    }
}
