package com.example.databaseaplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.databaseaplication.classRoom.ListClassRoomFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, new ListClassRoomFragment(), null)
                .commit();
    }

}