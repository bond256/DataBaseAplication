package com.example.databaseaplication.classRoom;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.R;


public class EditClassRoomFragment extends Fragment {
    private EditText nameClass;
    private EditText numberClass;
    private EditText floorClass;
    private EditText typeClass;
    private Button editButton;
    private FragmentEditListener fragmentEditListener;
    private ClassRoomModel editData;


    public EditClassRoomFragment() {
        // Required empty public constructor
    }

    public static EditClassRoomFragment newInstance(FragmentEditListener fragmentDialogListener, ClassRoomModel classRoomModel) {
        EditClassRoomFragment fragment = new EditClassRoomFragment();
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editButton.setOnClickListener(v -> {
            String name = nameClass.getText().toString();
            String number = numberClass.getText().toString();
            String floor = floorClass.getText().toString();
            String type = typeClass.getText().toString();
            if (!name.equals("") && !number.equals("") && !floor.equals("") && !type.equals("")) {
                fragmentEditListener.onEdit(new ClassRoomModel(editData.getId(), name, type, Integer.valueOf(number), Integer.valueOf(floor)));
            } else Toast.makeText(getActivity(), "Please input data", Toast.LENGTH_LONG).show();
        });
    }

    public interface FragmentEditListener {
        void onEdit(ClassRoomModel classRoomModel);
    }
}