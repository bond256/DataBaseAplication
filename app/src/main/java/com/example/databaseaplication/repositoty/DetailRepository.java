package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.model.StudentModel;
import com.example.databaseaplication.database.GetDataFromDB;

import java.util.List;

public class DetailRepository {
    private GetDataFromDB getDataFromDB;

    public DetailRepository(Context context) {
        this.getDataFromDB=new GetDataFromDB(context);
    }

    public void addStudent(StudentModel student){
        getDataFromDB.addNewStudent(student);
    }

    public List<StudentModel> getStudents(int id){
        return getDataFromDB.getStudents(id);
    }

    public ClassRoomModel getDetails(int id){
       return getDataFromDB.getClassRoomDetail(id);
    }

    public int deleteStudent(int id){
        return getDataFromDB.deleteStudent(id);
    }

    public int editStudent(StudentModel studentModel){
        return getDataFromDB.editStudent(studentModel);
    }


}
