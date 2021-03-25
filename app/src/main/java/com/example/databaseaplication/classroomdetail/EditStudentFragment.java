package com.example.databaseaplication.classroomdetail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.databaseaplication.model.StudentModel;
import com.example.databaseaplication.R;


public class EditStudentFragment extends Fragment {

    private StudentModel studentModel;
    private Spinner spinner;
    private EditText firstName;
    private EditText secondName;
    private EditText ageStudent;
    private String gender;
    private OnEditStudentListener editStudentListener;
    private Button editButton;


    public static EditStudentFragment newInstance(OnEditStudentListener onEditStudentListener, StudentModel studentModel) {
        EditStudentFragment fragment = new EditStudentFragment();
        fragment.editStudentListener = onEditStudentListener;
        fragment.studentModel = studentModel;
        return fragment;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);
        spinner = view.findViewById(R.id.spinnerGenderEdit);
        firstName = view.findViewById(R.id.firstNameEdit);
        secondName = view.findViewById(R.id.secondNameEdit);
        ageStudent = view.findViewById(R.id.ageEdit);
        editButton = view.findViewById(R.id.edit_student);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        String[] data = {"Male", "Female"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(arrayAdapter.getPosition(studentModel.getGender()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = data[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editButton.setOnClickListener(v -> {
            String name = firstName.getText().toString();
            String surname = secondName.getText().toString();
            String age = ageStudent.getText().toString();
            if (!name.equals("") && !surname.equals("") && !age.equals("")) {
                editStudentListener.onEdit(new StudentModel(studentModel.getId(), name, surname, studentModel.getClassId(), gender, Integer.valueOf(age)));
            } else Toast.makeText(getActivity(), "Please input data", Toast.LENGTH_LONG).show();
        });
    }

    private void init(){
        firstName.setText(studentModel.getFirstName());
        secondName.setText(studentModel.getSecondName());
        ageStudent.setText(studentModel.getAge().toString());
    }

    public interface OnEditStudentListener {
        void onEdit(StudentModel studentModel);
    }
}