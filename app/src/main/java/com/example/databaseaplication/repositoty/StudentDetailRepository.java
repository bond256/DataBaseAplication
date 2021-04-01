package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.database.GetDataFromDB;
import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.model.StudentModel;

import java.util.List;

public class StudentDetailRepository {
    private GetDataFromDB getDataFromDB;
    private static StudentDetailRepository studentDetailRepository;
    private Context context;

    public StudentDetailRepository(Context context) {
        this.getDataFromDB = new GetDataFromDB(context);
        this.context=context;
    }

    public static StudentDetailRepository getInstance(){
        if(studentDetailRepository==null){
            studentDetailRepository=new StudentDetailRepository(this.context);
        }
        return studentDetailRepository;
    }

    public List<MarksModel> getMarks(int id) {
        return getDataFromDB.getMarks(id);
    }

    public int addMarks(MarksModel marksModel) {
        getDataFromDB.addMarks(marksModel);
        return 0;
    }

    public StudentModel getDetailStudent(int id) {
        return getDataFromDB.getStudentDetail(id);
    }

    public void deleteMark(int id) {
        getDataFromDB.deleteMark(id);
    }

    public void editMark(MarksModel marksModel) {
        getDataFromDB.updateMark(marksModel);
    }


    public List<MarksModel> getSubjectByName(String name) {
        return getDataFromDB.getSubjectByName(name);
    }

    public List<MarksModel> getSubjectsByMark(int mark) {
        return getDataFromDB.getSubjectByMark(mark);
    }
}
