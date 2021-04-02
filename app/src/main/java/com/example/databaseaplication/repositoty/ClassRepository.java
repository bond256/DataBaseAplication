package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.database.GetDataFromDB;
import com.example.databaseaplication.model.ClassRoomModel;

import java.util.List;

public class ClassRepository {
    private final GetDataFromDB classData;
    private static ClassRepository classRepository;


    public ClassRepository(Context context) {
        this.classData = new GetDataFromDB(context);
    }


    public static void initInstance(Context context) {
        if (classRepository == null) {
            classRepository = new ClassRepository(context);
        }
    }

    public static ClassRepository getInstance() {
        return classRepository;
    }


    public List<ClassRoomModel> getClassRooms() {
        return classData.getAllClassRooms();
    }

    public long addClassRoom(ClassRoomModel classRoomModel) {
        return classData.addClassRoom(classRoomModel);
    }

    public int deleteClassRoom(int id) {
        return classData.deleteClassRoom(id);
    }

    public int editClassRoom(ClassRoomModel classRoomModel) {
        return classData.updateClassRoom(classRoomModel);
    }

    public List<ClassRoomModel> getClassRoomByName(String name) {
        return classData.getClassRoomByName(name);
    }
}
