package com.example.databaseaplication.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteProgram;

import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

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
        database.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_OF_CLASS + " WHERE " + DbHelper.TABLE_NAME_OF_CLASS + "." + DbHelper.ID + "=" + id);
        database.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_OF_MARKS + " WHERE " + DbHelper.STUDENT_ID + "=" + "( SELECT " + DbHelper.ID + " FROM " + DbHelper.TABLE_NAME_OF_STUDENTS + " WHERE " + DbHelper.CLASS_ID + "=" + id + ")");
        database.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_OF_STUDENTS + " WHERE " + DbHelper.CLASS_ID + "=" + id);
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

    public void addNewStudent(StudentModel studentModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.FIRST_NAME, studentModel.getFirstName());
        contentValues.put(DbHelper.SECOND_NAME, studentModel.getSecondName());
        contentValues.put(DbHelper.CLASS_ID, studentModel.getClassId());
        contentValues.put(DbHelper.GENDER, studentModel.getGender());
        contentValues.put(DbHelper.AGE, studentModel.getAge());
        database.insert(DbHelper.TABLE_NAME_OF_STUDENTS, null, contentValues);
    }

    public ClassRoomModel getClassRoomDetail(int id) {
        Cursor cursor = database.query(DbHelper.TABLE_NAME_OF_CLASS, null, DbHelper.ID + "=" + id, null, null, null, null);
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
        return new ClassRoomModel(idClass, name, typeOfClass, numberRoom, level);
    }

    public List<StudentModel> getStudents(int id) {
        Cursor cursor = database.query(DbHelper.TABLE_NAME_OF_STUDENTS, null, DbHelper.CLASS_ID + "=" + id, null, null, null, null);
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
        return dataStudents;
    }

    public int deleteStudent(int id) {
        database.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_OF_STUDENTS + " WHERE " + DbHelper.ID + "=" + id);
        database.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_OF_MARKS + " WHERE " + DbHelper.STUDENT_ID + "=" + id);
        //return database.delete(DbHelper.TABLE_NAME_OF_STUDENTS,DbHelper.ID+"="+id,null);
        return 1;
    }

    public int editStudent(StudentModel studentModel) {
        int id = studentModel.getId();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.FIRST_NAME, studentModel.getFirstName());
        contentValues.put(DbHelper.SECOND_NAME, studentModel.getSecondName());
        contentValues.put(DbHelper.CLASS_ID, studentModel.getClassId());
        contentValues.put(DbHelper.GENDER, studentModel.getGender());
        contentValues.put(DbHelper.AGE, studentModel.getAge());
        return database.update(DbHelper.TABLE_NAME_OF_STUDENTS, contentValues, DbHelper.ID + "=" + id, null);
    }

    public void addMarks(MarksModel marksModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.SUBJECT_NAME, marksModel.getSubjectName());
        contentValues.put(DbHelper.STUDENT_ID, marksModel.getStudentId());
        contentValues.put(DbHelper.MARK, marksModel.getMark());
        contentValues.put(DbHelper.DATA_MARK, marksModel.getDataMark());
        database.insert(DbHelper.TABLE_NAME_OF_MARKS, null, contentValues);
    }

    public StudentModel getStudentDetail(int id) {
        Cursor cursor = database.query(DbHelper.TABLE_NAME_OF_STUDENTS, null, DbHelper.ID + "=" + id, null, null, null, null);
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
        return new StudentModel(idStudent, firstName, secondName, classId, genderStudent, ageStudent);
    }

    public List<MarksModel> getMarks(int id) {
        Cursor cursor = database.query(DbHelper.TABLE_NAME_OF_MARKS, null, DbHelper.STUDENT_ID + "=" + id, null, null, null, null);
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
        return dataMarks;
    }

    public void deleteMark(int id) {
        database.delete(DbHelper.TABLE_NAME_OF_MARKS, DbHelper.ID + "=" + id, null);
    }

    public void updateMark(MarksModel marksModel) {
        ContentValues contentValues = new ContentValues();
        Integer id = marksModel.getId();
        contentValues.put(DbHelper.SUBJECT_NAME, marksModel.getSubjectName());
        contentValues.put(DbHelper.STUDENT_ID, marksModel.getStudentId());
        contentValues.put(DbHelper.MARK, marksModel.getMark());
        contentValues.put(DbHelper.DATA_MARK, marksModel.getDataMark());
        database.update(DbHelper.TABLE_NAME_OF_MARKS, contentValues, DbHelper.ID + "=" + id, null);
    }


    @SuppressLint("Recycle")
    public List<MarksModel> getSubjectByName(String name){
        String sds="dfds";
        //Cursor cursor= database.rawQuery("SELECT * FROM "+DbHelper.TABLE_NAME_OF_MARKS+" WHERE "+DbHelper.SUBJECT_NAME+" = "+name,null);
        Cursor cursor=database.query(DbHelper.TABLE_NAME_OF_MARKS,null,DbHelper.SUBJECT_NAME+"="+"math",null,null,null,null);
        ArrayList<MarksModel> dataMarks=new ArrayList<>();
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
        return dataMarks;
    }

    @SuppressLint("Recycle")
    public List<MarksModel> getSubjectByMark(int mark){
        Cursor cursor= database.rawQuery("SELECT * FROM "+DbHelper.TABLE_NAME_OF_MARKS+ " WHERE "+DbHelper.MARK+"="+mark,null);
        ArrayList<MarksModel> dataMarks=new ArrayList<>();
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
        return dataMarks;
    }




}
