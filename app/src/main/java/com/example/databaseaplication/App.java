package com.example.databaseaplication;

import android.app.Application;
import android.content.Context;

import com.example.databaseaplication.repositoty.ClassRepository;
import com.example.databaseaplication.repositoty.StudentDetailRepository;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StudentDetailRepository studentDetailRepository=StudentDetailRepository.getInstance(getApplicationContext());


    }
}
