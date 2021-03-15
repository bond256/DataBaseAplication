package com.example.databaseaplication.repositoty;

import android.content.Context;
import android.os.Handler;

import com.example.databaseaplication.Model.ClassRoomModel;
import com.example.databaseaplication.database.GetDataFromDB;

import java.util.List;

public class ClassRepository {
    private final GetDataFromDB classData;


    public ClassRepository(Context context) {
        this.classData=new GetDataFromDB(context);
    }

    public List<ClassRoomModel> getClassRooms(){
        return classData.getAllClassRooms();
    }

    public long addClassRoom(ClassRoomModel classRoomModel){
        return classData.addClassRoom(classRoomModel);
    }

    public int deleteClassRoom(int id){
        return classData.deleteClassRoom(id);
    }
}
