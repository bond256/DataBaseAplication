package com.example.databaseaplication.classdetail;

import com.example.databaseaplication.Model.ClassRoomModel;
import com.example.databaseaplication.Model.StudentModel;

import java.util.List;

public interface ClassDetailContract {
    interface View{
        void showDetails(ClassRoomModel classRoomModel);
        void showError();
        void showStudent(List<StudentModel> studentModel);
    }

    interface ClassDetailPresenter{
        void loadDetails(int id);
        void loadStudents(int id);
        void addStudent(StudentModel student);
        void deleteStudent(int id);
        void editStudent(StudentModel studentModel);
    }
}
