package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.Model.MarksModel;
import com.example.databaseaplication.Model.StudentModel;
import com.example.databaseaplication.database.GetDataFromDB;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailRepository {
    private GetDataFromDB getDataFromDB;

    public StudentDetailRepository(Context context) {
        this.getDataFromDB = new GetDataFromDB(context);
    }

    public List<MarksModel> getMarks(){
        return new ArrayList<>();
    }

    public int addMarks(MarksModel marksModel){
        getDataFromDB.addMarks(marksModel);
        return 0;
    }

    public StudentModel getDetailStudent(int id){
        return getDataFromDB.getStudentDetail(id);
    }
}
