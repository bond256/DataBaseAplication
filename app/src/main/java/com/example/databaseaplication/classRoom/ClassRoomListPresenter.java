package com.example.databaseaplication.classRoom;

import android.os.Handler;
import android.os.Looper;

import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.repositoty.ClassRoomRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ClassRoomListPresenter implements ClassRoomListContract.ClassListPresenter {
    private final ClassRoomListContract.View view;
    private final ClassRoomRepository classRoomRepository;
    private final Handler handler;

    public ClassRoomListPresenter(ClassRoomListContract.View callback) {
        this.view = callback;
        this.classRoomRepository = ClassRoomRepository.getInstance();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadClass() {
        //new Thread(() -> handler.post(() -> view.showList(classRoomRepository.getClassRooms()))).start();
        classRoomRepository.getClassRooms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        it -> view.showList(it),
                        ic -> view.showError(ic.getLocalizedMessage()));
    }

    @Override
    public void addClass(ClassRoomModel classRoom) {
//        new Thread(() -> handler.post(() -> {
//            if (classRoomRepository.addClassRoom(classRoom) > 0) {
//                view.showList(classRoomRepository.getClassRooms());
//            } else view.showError();
//        })).start();
        classRoomRepository.addClassRoom(classRoom)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{},
                        error->view.showError(error.getLocalizedMessage()));

    }

    @Override
    public void deleteClassRoom(int id) {
        new Thread(() -> handler.post(() -> {
            if (classRoomRepository.deleteClassRoom(id) > 0) {
            } else view.showError("gfsdd");
        })).start();
    }

    @Override
    public void editClassRoom(ClassRoomModel classRoomModel) {
//        new Thread(() -> handler.post(() -> {
//            classRoomRepository.editClassRoom(classRoomModel);
//            view.showList(classRoomRepository.getClassRooms());
//        })).start();
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
