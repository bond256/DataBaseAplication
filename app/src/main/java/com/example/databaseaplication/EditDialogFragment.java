package com.example.databaseaplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaseaplication.Model.ClassRoomModel;


public class EditDialogFragment extends Fragment {
    private EditText nameClass;
    private EditText numberClass;
    private EditText floorClass;
    private EditText typeClass;
    private Button createButton;
    private FragmentEditListener fragmentEditListener;
    private ClassRoomModel editData;



    public EditDialogFragment() {
        // Required empty public constructor
    }

    public static EditDialogFragment newInstance(FragmentEditListener fragmentDialogListener,ClassRoomModel classRoomModel) {
        EditDialogFragment fragment = new EditDialogFragment();
        fragment.fragmentEditListener=fragmentDialogListener;
        fragment.editData=classRoomModel;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_dialog, container, false);
    }

    public interface FragmentEditListener{
        void onEdit(ClassRoomModel classRoomModel);
    }
}