package com.example.databaseaplication.studentdetail;

import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.model.StudentModel;

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
        void editMark(MarksModel marksModel);
        void deleteMark(int id);
    }
}
