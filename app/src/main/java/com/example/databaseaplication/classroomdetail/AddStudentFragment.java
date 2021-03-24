package com.example.databaseaplication.classroomdetail;

import android.os.Bundle;

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


public class AddStudentFragment extends Fragment {
    private AddDialogListener addDialogListener;
    private String classId;
    private Spinner spinner;
    private EditText firstName;
    private EditText secondName;
    private EditText ageStudent;
    private String gender;
    private Button createButton;

    public static AddStudentFragment newInstance(AddDialogListener addDialogListener, String id) {
        AddStudentFragment fragment = new AddStudentFragment();
        fragment.addDialogListener=addDialogListener;
        fragment.classId=id;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_dialog, container, false);
        spinner=view.findViewById(R.id.spinnerGender);
        ArrayAdapter<?> arrayAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.gender, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        firstName=view.findViewById(R.id.firstName);
        secondName=view.findViewById(R.id.secondName);
        ageStudent=view.findViewById(R.id.age);
        createButton=view.findViewById(R.id.create_student);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               gender= getResources().getStringArray(R.array.gender)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        createButton.setOnClickListener(v->{
            String name=firstName.getText().toString();
            String surname=secondName.getText().toString();
            String age=ageStudent.getText().toString();
            if(!name.equals("")&&!surname.equals("")&&!age.equals("")) {
                addDialogListener.addCLick(new StudentModel(0, name, surname, Integer.valueOf(classId), gender, Integer.valueOf(age)));
            }else Toast.makeText(getActivity(),"Please input data",Toast.LENGTH_LONG).show();
        });
        return view;
    }

    interface AddDialogListener{
        void addCLick(StudentModel studentModel);
    }
}