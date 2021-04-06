package com.example.databaseaplication.studentdetail;

import android.os.Handler;
import android.os.Looper;

import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.repositoty.StudentDetailRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentDetailPresenter implements StudentDetailContract.StudentDetailPresenter {
    private final StudentDetailContract.View view;
    private final StudentDetailRepository studentDetailRepository;
    private final Handler handler;


    public StudentDetailPresenter(StudentDetailContract.View view) throws Exception {
        this.view = view;
        this.studentDetailRepository = StudentDetailRepository.getInstance();
        this.handler = new Handler(Looper.getMainLooper());
    }


    @Override
    public void loadDetail(int id) {
        studentDetailRepository.getDetailStudent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> view.showDetail(result));
    }

    @Override
    public void addMark(MarksModel marksModel) {
        new Thread(() -> handler.post(() -> {
            studentDetailRepository.addMarks(marksModel);
        })).start();
    }

    @Override
    public void loadMarks(int id) {
        studentDetailRepository.getMarks(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> view.showMarks(result));
    }

    @Override
    public void editMark(MarksModel marksModel) {
        studentDetailRepository.editMark(marksModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                        },
                        error -> view.showError(error.getLocalizedMessage()));
    }

    @Override
    public void deleteMark(int id) {
        studentDetailRepository.deleteMark(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                        },
                        error -> view.showError(error.getLocalizedMessage()));
    }

    @Override
    public void loadSubject(String name) {
        studentDetailRepository.getSubjectByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> view.showMarks(result));
    }

    @Override
    public void loadSubjectByMark(int mark) {
        studentDetailRepository.getSubjectsByMark(mark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> view.showMarks(result));

    }


}
