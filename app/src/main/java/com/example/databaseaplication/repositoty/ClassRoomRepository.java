package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.database.GetClassRoomData;
import com.example.databaseaplication.model.ClassRoomModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class ClassRoomRepository {
    private final GetClassRoomData getClassRoomData;
    private static ClassRoomRepository classRoomRepository;


    public ClassRoomRepository(Context context) {
        this.getClassRoomData = new GetClassRoomData(context);
    }


    public static void initInstance(Context context) {
        if (classRoomRepository == null) {
            classRoomRepository = new ClassRoomRepository(context);
        }
    }

    public static ClassRoomRepository getInstance() {
        return classRoomRepository;
    }


    public Single<List<ClassRoomModel>> getClassRooms() {
        return getClassRoomData.getAllClassRooms();
    }


    public Completable addClassRoom(ClassRoomModel classRoomModel) {
        return getClassRoomData.addClassRoom(classRoomModel);
    }

    public Completable deleteClassRoom(int id) {
        return getClassRoomData.deleteClassRoom(id);
    }

    public Completable editClassRoom(ClassRoomModel classRoomModel) {
        return getClassRoomData.updateClassRoom(classRoomModel);
    }

    public Single<List<ClassRoomModel>> getClassRoomByName(String name) {
        return getClassRoomData.getClassRoomByName(name);
    }
}
