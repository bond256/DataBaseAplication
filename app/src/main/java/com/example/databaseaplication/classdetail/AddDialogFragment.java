package com.example.databaseaplication.classdetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.databaseaplication.Model.StudentModel;
import com.example.databaseaplication.R;


public class AddDialogFragment extends Fragment {
    private AddDialogListener addDialogListener;

    public static AddDialogFragment newInstance(AddDialogListener addDialogListener) {
        AddDialogFragment fragment = new AddDialogFragment();
        fragment.addDialogListener=addDialogListener;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_dialog, container, false);
        return view;
    }

    interface AddDialogListener{
        void addCLick(StudentModel studentModel);
    }
}