package com.example.databaseaplication.classdetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.databaseaplication.R;


public class ClassDetailFragment extends Fragment implements ClassDetailContract.View {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_detail, container, false);
    }

    @Override
    public void showDetails() {

    }

    @Override
    public void showError() {

    }
}