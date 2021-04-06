package com.example.databaseaplication.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class GetStudentDetailData {
    private DbHelper dbHelper;

    public GetStudentDetailData(Context context) {
        dbHelper = new DbHelper(context);
    }


    public Single<StudentModel> getStudentDetail(int id) {
        return Single.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query(DbHelper.TABLE_NAME_OF_STUDENTS, null, DbHelper.ID + "=" + id, null, null, null, null);
            cursor.moveToFirst();
            int idIndex = cursor.getColumnIndex(DbHelper.ID);
            int name = cursor.getColumnIndex(DbHelper.FIRST_NAME);
            int second = cursor.getColumnIndex(DbHelper.SECOND_NAME);
            int gender = cursor.getColumnIndex(DbHelper.GENDER);
            int age = cursor.getColumnIndex(DbHelper.AGE);
            int idClass = cursor.getColumnIndex(DbHelper.CLASS_ID);
            Integer idStudent = cursor.getInt(idIndex);
            String firstName = cursor.getString(name);
            String secondName = cursor.getString(second);
            String genderStudent = cursor.getString(gender);
            Integer ageStudent = cursor.getInt(age);
            Integer classId = cursor.getInt(idClass);
            cursor.close();
            emitter.onSuccess(new StudentModel(idStudent, firstName, secondName, classId, genderStudent, ageStudent));
        });
    }


    public Single<List<MarksModel>> getMarks(int id) {
        return Single.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query(DbHelper.TABLE_NAME_OF_MARKS, null, DbHelper.STUDENT_ID + "=" + id, null, null, null, null);
            ArrayList<MarksModel> dataMarks = new ArrayList<>();
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DbHelper.ID);
                int name = cursor.getColumnIndex(DbHelper.SUBJECT_NAME);
                int mark = cursor.getColumnIndex(DbHelper.MARK);
                int date = cursor.getColumnIndex(DbHelper.DATA_MARK);
                int studentId = cursor.getColumnIndex(DbHelper.STUDENT_ID);
                do {
                    Integer idMark = cursor.getInt(idIndex);
                    String subjectName = cursor.getString(name);
                    Integer subjectMark = cursor.getInt(mark);
                    String dateMark = cursor.getString(date);
                    Integer idStudent = cursor.getInt(studentId);
                    dataMarks.add(new MarksModel(idMark, subjectName, idStudent, subjectMark, dateMark));
                } while (cursor.moveToNext());
            }
            cursor.close();
            emitter.onSuccess(dataMarks);
        });
    }


    @SuppressLint("Recycle")
    public Single<List<MarksModel>> getSubjectByName(String name) {
        return Single.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME_OF_MARKS + " WHERE " + DbHelper.SUBJECT_NAME + " = ?", new String[]{name});
            ArrayList<MarksModel> dataMarks = new ArrayList<>();
            if (cursor.moveToFirst()) {

                int idIndex = cursor.getColumnIndex(DbHelper.ID);
                int nameSubject = cursor.getColumnIndex(DbHelper.SUBJECT_NAME);
                int mark = cursor.getColumnIndex(DbHelper.MARK);
                int date = cursor.getColumnIndex(DbHelper.DATA_MARK);
                int studentId = cursor.getColumnIndex(DbHelper.STUDENT_ID);
                do {
                    Integer idMark = cursor.getInt(idIndex);
                    String subjectName = cursor.getString(nameSubject);
                    Integer subjectMark = cursor.getInt(mark);
                    String dateMark = cursor.getString(date);
                    Integer idStudent = cursor.getInt(studentId);
                    dataMarks.add(new MarksModel(idMark, subjectName, idStudent, subjectMark, dateMark));
                } while (cursor.moveToNext());
            }
            cursor.close();
            emitter.onSuccess(dataMarks);
        });
    }

    @SuppressLint("Recycle")
    public Single<List<MarksModel>> getSubjectByMark(int mark) {
        return Single.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME_OF_MARKS + " WHERE " + DbHelper.MARK + "=" + mark, null);
            ArrayList<MarksModel> dataMarks = new ArrayList<>();
            if (cursor.moveToFirst()) {

                int idIndex = cursor.getColumnIndex(DbHelper.ID);
                int nameSubject = cursor.getColumnIndex(DbHelper.SUBJECT_NAME);
                int markSubject = cursor.getColumnIndex(DbHelper.MARK);
                int date = cursor.getColumnIndex(DbHelper.DATA_MARK);
                int studentId = cursor.getColumnIndex(DbHelper.STUDENT_ID);
                do {
                    Integer idMark = cursor.getInt(idIndex);
                    String subjectName = cursor.getString(nameSubject);
                    Integer subjectMark = cursor.getInt(markSubject);
                    String dateMark = cursor.getString(date);
                    Integer idStudent = cursor.getInt(studentId);
                    dataMarks.add(new MarksModel(idMark, subjectName, idStudent, subjectMark, dateMark));
                } while (cursor.moveToNext());
            }
            cursor.close();
            emitter.onSuccess(dataMarks);
        });
    }

    public Completable addMarks(MarksModel marksModel) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbHelper.SUBJECT_NAME, marksModel.getSubjectName());
            contentValues.put(DbHelper.STUDENT_ID, marksModel.getStudentId());
            contentValues.put(DbHelper.MARK, marksModel.getMark());
            contentValues.put(DbHelper.DATA_MARK, marksModel.getDataMark());
            if (sqLiteDatabase.insert(DbHelper.TABLE_NAME_OF_MARKS, null, contentValues) > 0) {
                emitter.onComplete();
            } else {
                emitter.onError(new Exception("ErrorAddMark"));
            }
        });
    }

    public Completable deleteMark(int id) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            if (sqLiteDatabase.delete(DbHelper.TABLE_NAME_OF_MARKS, DbHelper.ID + "=" + id, null) > 0) {
                emitter.onComplete();
            } else {
                emitter.onError(new Exception("Delete Mark"));
            }
        });
    }

    public Completable updateMark(MarksModel marksModel) {
        return Completable.create(emitter -> {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Integer id = marksModel.getId();
            contentValues.put(DbHelper.SUBJECT_NAME, marksModel.getSubjectName());
            contentValues.put(DbHelper.STUDENT_ID, marksModel.getStudentId());
            contentValues.put(DbHelper.MARK, marksModel.getMark());
            contentValues.put(DbHelper.DATA_MARK, marksModel.getDataMark());
            if (sqLiteDatabase.update(DbHelper.TABLE_NAME_OF_MARKS, contentValues, DbHelper.ID + "=" + id, null) > 0) {
                emitter.onComplete();
            } else {
                emitter.onError(new Exception("Error Update"));
            }
        });
    }

}
