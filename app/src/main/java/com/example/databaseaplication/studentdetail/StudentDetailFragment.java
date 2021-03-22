package com.example.databaseaplication.studentdetail;

import android.annotation.SuppressLint;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.databaseaplication.Model.MarksModel;
import com.example.databaseaplication.Model.StudentModel;
import com.example.databaseaplication.R;

public class StudentDetailFragment extends Fragment implements StudentDetailContract.View {
    private Integer studentID;
    private StudentDetailPresenter studentDetailPresenter;
    private TextView firstName;
    private TextView secondName;
    private TextView gender;
    private TextView age;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           studentID=getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_student_detail, container, false);
        firstName=view.findViewById(R.id.firstNameDetail);
        secondName=view.findViewById(R.id.secondNameDetail);
        gender=view.findViewById(R.id.genderDetail);
        age=view.findViewById(R.id.ageDetail);
        studentDetailPresenter=new StudentDetailPresenter(this,getContext());
        studentDetailPresenter.loadDetail(studentID);
        studentDetailPresenter.addMark(new MarksModel(0,"chemestry",studentID,5,"34:53:65"));
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showDetail(StudentModel studentModel) {
        Log.d("tag", "showDetail: "+studentModel.getFirstName());
        firstName.setText(studentModel.getFirstName());
        secondName.setText(studentModel.getSecondName());
        gender.setText(studentModel.getGender());
        age.setText(studentModel.getId().toString());
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMarks(MarksModel marksModel) {

    }
}