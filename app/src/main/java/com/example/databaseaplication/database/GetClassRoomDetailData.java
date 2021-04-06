package com.example.databaseaplication.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class GetClassRoomDetailData {
    private DbHelper dbHelper;

    public GetClassRoomDetailData(Context context) {
        dbHelper = new DbHelper(context);
    }


    public Single<ClassRoomModel> getClassRoomDetail(int id) {
        return Single.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query(DbHelper.TABLE_NAME_OF_CLASS, null, DbHelper.ID + "=" + id, null, null, null, null);
            int idIndex = cursor.getColumnIndex(DbHelper.ID);
            int nameIndex = cursor.getColumnIndex(DbHelper.NAME);
            int roomNumberIndex = cursor.getColumnIndex(DbHelper.ROOM_NUMBER);
            int levelIndex = cursor.getColumnIndex(DbHelper.LEVEL);
            int typeOfClassIndex = cursor.getColumnIndex(DbHelper.TYPE_OF_CLASS);
            cursor.moveToFirst();
            Integer idClass = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            Integer numberRoom = cursor.getInt(roomNumberIndex);
            Integer level = cursor.getInt(levelIndex);
            String typeOfClass = cursor.getString(typeOfClassIndex);
            cursor.close();
            emitter.onSuccess(new ClassRoomModel(idClass, name, typeOfClass, numberRoom, level));
        });
    }

    public Single<List<StudentModel>> getStudents(int id) {
        return Single.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query(DbHelper.TABLE_NAME_OF_STUDENTS, null, DbHelper.CLASS_ID + "=" + id, null, null, null, null);
            ArrayList<StudentModel> dataStudents = new ArrayList<>();

            if (cursor.moveToFirst()) {

                int idIndex = cursor.getColumnIndex(DbHelper.ID);
                int name = cursor.getColumnIndex(DbHelper.FIRST_NAME);
                int second = cursor.getColumnIndex(DbHelper.SECOND_NAME);
                int gender = cursor.getColumnIndex(DbHelper.GENDER);
                int age = cursor.getColumnIndex(DbHelper.AGE);
                int idClass = cursor.getColumnIndex(DbHelper.CLASS_ID);
                do {
                    Integer idStudent = cursor.getInt(idIndex);
                    String firstName = cursor.getString(name);
                    String secondName = cursor.getString(second);
                    String genderStudent = cursor.getString(gender);
                    Integer ageStudent = cursor.getInt(age);
                    Integer classId = cursor.getInt(idClass);
                    dataStudents.add(new StudentModel(idStudent, firstName, secondName, classId, genderStudent, ageStudent));
                } while (cursor.moveToNext());
            }
            emitter.onSuccess(dataStudents);
        });
    }

    @SuppressLint("Recycle")
    public Single<List<StudentModel>> getStudentByName(String name, int id) {
        return Single.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME_OF_STUDENTS + " WHERE " + "(" + DbHelper.FIRST_NAME + " = ? " + " OR " + DbHelper.SECOND_NAME + " = ? " + ")" + " AND " + DbHelper.CLASS_ID + " = ? ", new String[]{name, name, String.valueOf(id)});
            ArrayList<StudentModel> dataStudents = new ArrayList<>();

            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DbHelper.ID);
                int nameIndex = cursor.getColumnIndex(DbHelper.FIRST_NAME);
                int second = cursor.getColumnIndex(DbHelper.SECOND_NAME);
                int gender = cursor.getColumnIndex(DbHelper.GENDER);
                int age = cursor.getColumnIndex(DbHelper.AGE);
                int idClass = cursor.getColumnIndex(DbHelper.CLASS_ID);
                do {
                    Integer idStudent = cursor.getInt(idIndex);
                    String firstName = cursor.getString(nameIndex);
                    String secondName = cursor.getString(second);
                    String genderStudent = cursor.getString(gender);
                    Integer ageStudent = cursor.getInt(age);
                    Integer classId = cursor.getInt(idClass);
                    dataStudents.add(new StudentModel(idStudent, firstName, secondName, classId, genderStudent, ageStudent));
                } while (cursor.moveToNext());
            }
            emitter.onSuccess(dataStudents);
        });
    }

    public Completable deleteStudent(int id) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            int res1 = sqLiteDatabase.delete(DbHelper.TABLE_NAME_OF_STUDENTS, DbHelper.ID + "=?", new String[]{String.valueOf(id)});
            int res2 = sqLiteDatabase.delete(DbHelper.TABLE_NAME_OF_MARKS, DbHelper.STUDENT_ID + "=?", new String[]{String.valueOf(id)});
            if (res1 > 0 && res2 > 0) {
                emitter.onComplete();
            } else {
                emitter.onError(new Exception("ErrorDelete"));
            }
        });
    }

    public Completable editStudent(StudentModel studentModel) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            int id = studentModel.getId();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbHelper.FIRST_NAME, studentModel.getFirstName());
            contentValues.put(DbHelper.SECOND_NAME, studentModel.getSecondName());
            contentValues.put(DbHelper.CLASS_ID, studentModel.getClassId());
            contentValues.put(DbHelper.GENDER, studentModel.getGender());
            contentValues.put(DbHelper.AGE, studentModel.getAge());
            if (sqLiteDatabase.update(DbHelper.TABLE_NAME_OF_STUDENTS, contentValues, DbHelper.ID + "=?", new String[]{String.valueOf(id)}) > 0) {
                emitter.onComplete();
            } else {
                emitter.onError(new Exception("ErrorUpdate"));
            }
        });
    }

    public Completable addNewStudent(StudentModel studentModel) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbHelper.FIRST_NAME, studentModel.getFirstName());
            contentValues.put(DbHelper.SECOND_NAME, studentModel.getSecondName());
            contentValues.put(DbHelper.CLASS_ID, studentModel.getClassId());
            contentValues.put(DbHelper.GENDER, studentModel.getGender());
            contentValues.put(DbHelper.AGE, studentModel.getAge());
            if (sqLiteDatabase.insert(DbHelper.TABLE_NAME_OF_STUDENTS, null, contentValues) > 0) {
                emitter.onComplete();
            } else {
                emitter.onError(new Exception("ErrorAddStudent"));
            }
        });
    }

}
