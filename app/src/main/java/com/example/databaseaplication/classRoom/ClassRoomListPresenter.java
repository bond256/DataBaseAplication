package com.example.databaseaplication.classRoom;

import android.os.Handler;
import android.os.Looper;

import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.repositoty.ClassRoomRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ClassRoomListPresenter implements ClassRoomListContract.ClassListPresenter {
    private final ClassRoomListContract.View view;
    private final ClassRoomRepository classRoomRepository;

    public ClassRoomListPresenter(ClassRoomListContract.View callback) {
        this.view = callback;
        this.classRoomRepository = ClassRoomRepository.getInstance();
    }

    @Override
    public void loadClass() {
       Disposable disposable= classRoomRepository.getClassRooms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        it -> view.showList(it),
                        ic -> view.showError(ic.getLocalizedMessage()));
    }

    @Override
    public void addClass(ClassRoomModel classRoom) {
        classRoomRepository.addClassRoom(classRoom)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                        },
                        error -> view.showError(error.getLocalizedMessage()));

    }

    @Override
    public void deleteClassRoom(int id) {
        classRoomRepository.deleteClassRoom(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                        },
                        error -> view.showError(error.getLocalizedMessage()));
    }

    @Override
    public void editClassRoom(ClassRoomModel classRoomModel) {
        classRoomRepository.editClassRoom(classRoomModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                        },
                        error -> view.showError(error.getLocalizedMessage()));
    }

    @Override
    public void loadClassRoomsByName(String name) {
        classRoomRepository.getClassRoomByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> view.showList(result),
                        error -> view.showError(error.getLocalizedMessage()));
    }




}
