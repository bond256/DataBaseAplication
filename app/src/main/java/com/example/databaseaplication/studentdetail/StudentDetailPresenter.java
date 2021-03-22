package com.example.databaseaplication.studentdetail;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.databaseaplication.Model.MarksModel;
import com.example.databaseaplication.repositoty.StudentDetailRepository;

public class StudentDetailPresenter implements StudentDetailContract.StudentDetailPresenter {
    private StudentDetailContract.View view;
    private StudentDetailRepository studentDetailRepository;
    private Handler handler;


    public StudentDetailPresenter(StudentDetailContract.View view, Context context) {
        this.view = view;
        this.studentDetailRepository = new StudentDetailRepository(context);
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadDetail(int id) {
        view.showDetail(studentDetailRepository.getDetailStudent(id));
    }

    @Override
    public void addMark(MarksModel marksModel) {
        studentDetailRepository.addMarks(marksModel);
    }
}
