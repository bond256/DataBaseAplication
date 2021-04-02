package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.database.GetDataFromDB;
import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.model.StudentModel;

import java.util.List;

public class DetailRepository {
    private GetDataFromDB getDataFromDB;
    private static DetailRepository detailRepository;

    public DetailRepository(Context context) {
        this.getDataFromDB = new GetDataFromDB(context);
    }


    public static void initInstance(Context context) {
        if (detailRepository == null) {
            detailRepository = new DetailRepository(context);
        }
    }

    public static DetailRepository getInstance() {
        return detailRepository;
    }

    public void addStudent(StudentModel student) {
        getDataFromDB.addNewStudent(student);
    }

    public List<StudentModel> getStudents(int id) {
        return getDataFromDB.getStudents(id);
    }

    public ClassRoomModel getDetails(int id) {
        return getDataFromDB.getClassRoomDetail(id);
    }

    public int deleteStudent(int id) {
        return getDataFromDB.deleteStudent(id);
    }

    public int editStudent(StudentModel studentModel) {
        return getDataFromDB.editStudent(studentModel);
    }

    public List<StudentModel> getStudentsByName(String name, int id) {
        return getDataFromDB.getStudentByName(name, id);
    }


}
