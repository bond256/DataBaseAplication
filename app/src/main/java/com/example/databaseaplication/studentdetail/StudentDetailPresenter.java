package com.example.databaseaplication.studentdetail;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.repositoty.StudentDetailRepository;

public class StudentDetailPresenter implements StudentDetailContract.StudentDetailPresenter {
    private final StudentDetailContract.View view;
    private final StudentDetailRepository studentDetailRepository;
    private final Handler handler;


    public StudentDetailPresenter(StudentDetailContract.View view, Context context) {
        this.view = view;
        this.studentDetailRepository = new StudentDetailRepository(context);
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadDetail(int id) {
        new Thread(() -> handler.post(() -> {
            view.showDetail(studentDetailRepository.getDetailStudent(id));
        })).start();
    }

    @Override
    public void addMark(MarksModel marksModel) {
        new Thread(() -> handler.post(() -> {
            studentDetailRepository.addMarks(marksModel);
        })).start();
    }

    @Override
    public void loadMarks(int id) {
        new Thread(() -> handler.post(() -> {
            view.showMarks(studentDetailRepository.getMarks(id));
        })).start();
    }

    @Override
    public void editMark(MarksModel marksModel) {
        new Thread(() -> handler.post(() -> studentDetailRepository.editMark(marksModel))).start();
    }

    @Override
    public void deleteMark(int id) {
        new Thread(() -> handler.post(() -> {
            studentDetailRepository.deleteMark(id);
        })).start();
    }

    @Override
    public void loadSubject(String name) {
        new Thread(() -> handler.post(() -> {
            view.showMarks(studentDetailRepository.getSubjectByName(name));
        })).start();
    }

    @Override
    public void loadSubjectByMark(int mark) {
        new Thread(() -> handler.post(() -> {
            view.showMarks(studentDetailRepository.getSubjectsByMark(mark));
        })).start();
        Log.d("tag", "loadSubjectByMark: "+mark);

    }


}
