package com.example.databaseaplication;

import android.annotation.SuppressLint;
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
    private Button editButton;
    private FragmentEditListener fragmentEditListener;
    private ClassRoomModel editData;


    public EditDialogFragment() {
        // Required empty public constructor
    }

    public static EditDialogFragment newInstance(FragmentEditListener fragmentDialogListener, ClassRoomModel classRoomModel) {
        EditDialogFragment fragment = new EditDialogFragment();
        fragment.fragmentEditListener = fragmentDialogListener;
        fragment.editData = classRoomModel;
        return fragment;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_dialog, container, false);
        nameClass = view.findViewById(R.id.nameClassRoomEdit);
        numberClass = view.findViewById(R.id.numberClassRoomEdit);
        floorClass = view.findViewById(R.id.floorClassRoomEdit);
        typeClass = view.findViewById(R.id.typeOfClassEdit);
        nameClass.setText(editData.getName());
        numberClass.setText(editData.getNumberRoom().toString());
        floorClass.setText(editData.getLevel().toString());
        typeClass.setText(editData.getTypeOfClass());
        editButton = view.findViewById(R.id.edit_class_room);
        editButton.setOnClickListener(v -> {
            String name = nameClass.getText().toString();
            String number = numberClass.getText().toString();
            String floor = floorClass.getText().toString();
            String type = typeClass.getText().toString();
            fragmentEditListener.onEdit(new ClassRoomModel(editData.getId(), name, type, Integer.valueOf(number), Integer.valueOf(floor)));
        });
        return view;
    }


    public interface FragmentEditListener {
        void onEdit(ClassRoomModel classRoomModel);
    }
}