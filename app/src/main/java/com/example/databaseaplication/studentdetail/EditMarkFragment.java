package com.example.databaseaplication.studentdetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.databaseaplication.R;


public class EditMarkFragment extends Fragment {


    public static EditMarkFragment newInstance(String param1, String param2) {
        EditMarkFragment fragment = new EditMarkFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit_mark, container, false);
    }
}