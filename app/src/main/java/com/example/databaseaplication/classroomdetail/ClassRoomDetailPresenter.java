package com.example.databaseaplication.classroomdetail;

import android.os.Handler;
import android.os.Looper;

import com.example.databaseaplication.model.StudentModel;
import com.example.databaseaplication.repositoty.DetailRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ClassRoomDetailPresenter implements ClassRoomDetailContract.ClassDetailPresenter {
    private ClassRoomDetailContract.View view;
    private DetailRepository detailRepository;


    public ClassRoomDetailPresenter(ClassRoomDetailContract.View view) {
        this.view = view;
        this.detailRepository = DetailRepository.getInstance();
    }

    @Override
    public void loadDetails(int id) {
        detailRepository.getDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> view.showDetails(result));
    }

    @Override
    public void loadStudents(int id) {
        detailRepository.getStudents(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->view.showStudent(result));
    }

    @Override
    public void addStudent(StudentModel student) {
        detailRepository.addStudent(student)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{},
                        error->view.showError(error.getLocalizedMessage()));
    }

    @Override
    public void deleteStudent(int id) {
        detailRepository.deleteStudent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{},
                        error->view.showError(error.getLocalizedMessage()));

    }

    @Override
    public void editStudent(StudentModel studentModel) {
        detailRepository.editStudent(studentModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{},
                        error->view.showError(error.getLocalizedMessage()));

    }

    @Override
    public void loadStudentsByName(String name, int id) {
        detailRepository.getStudentsByName(name,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->view.showStudent(result));
    }
}
