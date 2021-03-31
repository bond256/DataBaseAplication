package com.example.databaseaplication.classroomdetail;

import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.model.StudentModel;

import java.util.List;

public interface ClassRoomDetailContract {
    interface View {
        void showDetails(ClassRoomModel classRoomModel);

        void showError();

        void showStudent(List<StudentModel> studentModel);
    }

    interface ClassDetailPresenter {
        void loadDetails(int id);

        void loadStudents(int id);

        void addStudent(StudentModel student);

        void deleteStudent(int id);

        void editStudent(StudentModel studentModel);
    }
}
