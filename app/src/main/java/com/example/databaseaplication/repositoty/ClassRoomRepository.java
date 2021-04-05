package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.database.GetClassRoomData;
import com.example.databaseaplication.database.GetDataFromDB;
import com.example.databaseaplication.model.ClassRoomModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class ClassRoomRepository {
    private final GetDataFromDB classRoomData;
    private final GetClassRoomData getClassRoomData;
    private static ClassRoomRepository classRoomRepository;


    public ClassRoomRepository(Context context) {
        this.classRoomData = new GetDataFromDB(context);
        this.getClassRoomData=new GetClassRoomData(context);
    }


    public static void initInstance(Context context) {
        if (classRoomRepository == null) {
            classRoomRepository = new ClassRoomRepository(context);
        }
    }

    public static ClassRoomRepository getInstance() {
        return classRoomRepository;
    }


//    public List<ClassRoomModel> getClassRooms() {
//        return classData.getAllClassRooms();
//    }

    public Single<List<ClassRoomModel>> getClassRooms(){
        return getClassRoomData.getAllClassRooms();
    }

//    public long addClassRoom(ClassRoomModel classRoomModel) {
//        return classRoomData.addClassRoom(classRoomModel);
//    }

    public Completable addClassRoom(ClassRoomModel classRoomModel) {
        return getClassRoomData.addClassRoom(classRoomModel);
    }

    public int deleteClassRoom(int id) {
        return classRoomData.deleteClassRoom(id);
    }

    public int editClassRoom(ClassRoomModel classRoomModel) {
        return classRoomData.updateClassRoom(classRoomModel);
    }

//    public List<ClassRoomModel> getClassRoomByName(String name) {
//        return classRoomData.getClassRoomByName(name);
//    }

    public Single<List<ClassRoomModel>> getClassRoomByName(String name) {
        return getClassRoomData.getClassRoomByName(name);
    }
}
