package com.example.databaseaplication.studentdetail;

import com.example.databaseaplication.Model.MarksModel;
import com.example.databaseaplication.Model.StudentModel;

public interface StudentDetailContract {
    interface View{
        void showDetail(StudentModel studentModel);
        void showError();
        void showMarks(MarksModel marksModel);
    }

    interface StudentDetailPresenter{
        void loadDetail(int id);
        void addMark(MarksModel marksModel);
    }
}
