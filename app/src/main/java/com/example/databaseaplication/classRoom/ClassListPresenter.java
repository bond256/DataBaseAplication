package com.example.databaseaplication.classRoom;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.databaseaplication.Model.ClassRoomModel;
import com.example.databaseaplication.repositoty.ClassRepository;


public class ClassListPresenter implements ClassListContract.ClassListPresenter {
    private final ClassListContract.View view;
    private final ClassRepository classRepository;
    private final Handler handler;

    public ClassListPresenter(ClassListContract.View view, Context context) {
        this.view = view;
        this.classRepository = new ClassRepository(context);
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadClass() {
        new Thread(() -> {
            handler.post(() -> {
                view.showList(classRepository.getClassRooms());
            });
        }).start();
    }

    @Override
    public void addClass(ClassRoomModel classRoom) {
        new Thread(() -> {
            handler.post(() -> {
                if (classRepository.addClassRoom(classRoom) > 0) {
                    view.showList(classRepository.getClassRooms());
                } else view.showError();
            });
        }).start();

    }

    @Override
    public void deleteClassRoom(int id) {
        new Thread(() -> {
            handler.post(() -> {
                if (classRepository.deleteClassRoom(id) > 0) {
                } else view.showError();
            });
        }).start();
    }

    @Override
    public void editClassRoom(ClassRoomModel classRoomModel) {
        new Thread(() -> {
            handler.post(() -> {
                classRepository.editClassRoom(classRoomModel);
                view.showList(classRepository.getClassRooms());
            });
        }).start();

    }


}
