package com.example.databaseaplication;

import android.app.Application;

import com.example.databaseaplication.repositoty.ClassRoomRepository;
import com.example.databaseaplication.repositoty.DetailRepository;
import com.example.databaseaplication.repositoty.StudentDetailRepository;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StudentDetailRepository.initInstance(getApplicationContext());
        DetailRepository.initInstance(getApplicationContext());
        ClassRoomRepository.initInstance(getApplicationContext());
    }
}
