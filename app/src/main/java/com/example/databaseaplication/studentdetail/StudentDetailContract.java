package com.example.databaseaplication.studentdetail;

import com.example.databaseaplication.Model.MarksModel;
import com.example.databaseaplication.Model.StudentModel;

import java.util.List;

public interface StudentDetailContract {
    interface View{
        void showDetail(StudentModel studentModel);
        void showError();
        void showMarks(List<MarksModel> marksModel);
    }

    interface StudentDetailPresenter{
        void loadDetail(int id);
        void addMark(MarksModel marksModel);
        void loadMarks(int id);
    }
}
