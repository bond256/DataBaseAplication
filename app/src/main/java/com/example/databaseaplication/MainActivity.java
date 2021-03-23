package com.example.databaseaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.databaseaplication.classRoom.ListClassFragment;

public class MainActivity extends AppCompatActivity implements MainInterfaceCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment,new ListClassFragment(),null)
                .commit();
    }

    @Override
    public void openClassRoomFragment() {

    }

    @Override
    public void openClassDetailsFragment() {

    }

    @Override
    public void openStudentDetailsFragment() {

    }
}