package com.example.databaseaplication.studentdetail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.databaseaplication.adapters.MarksAdapter;
import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.model.StudentModel;
import com.example.databaseaplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailFragment extends Fragment implements StudentDetailContract.View,
        MarksAdapter.ItemMenuListener,
        AddMarkFragment.OnAddFragmentListener {
    private Integer studentID;
    private StudentDetailPresenter studentDetailPresenter;
    private TextView firstName;
    private TextView secondName;
    private TextView gender;
    private TextView age;
    private RecyclerView marksRecycler;
    private MarksAdapter marksAdapter;
    private List<MarksModel> marksData;
    private AddMarkFragment addMarkFragment;
    private FloatingActionButton addButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentID = getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_detail, container, false);
        firstName = view.findViewById(R.id.firstNameDetail);
        secondName = view.findViewById(R.id.secondNameDetail);
        marksRecycler = view.findViewById(R.id.marksRecycler);
        gender = view.findViewById(R.id.genderDetail);
        age = view.findViewById(R.id.ageDetail);
        addButton = view.findViewById(R.id.fab_add_mark);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        addButton.setOnClickListener(v -> {
            addMarkFragment = AddMarkFragment.newInstance(this, studentID);
            getParentFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.main_fragment, addMarkFragment, null)
                    .commit();
        });
        studentDetailPresenter.loadDetail(studentID);
        studentDetailPresenter.loadMarks(studentID);
    }

    private void init() {
        marksData = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        marksRecycler.setLayoutManager(layoutManager);
        marksAdapter = new MarksAdapter(marksData);
        marksAdapter.setOnItemMenuClickListener(this);
        marksRecycler.setAdapter(marksAdapter);
        studentDetailPresenter = new StudentDetailPresenter(this, getContext());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showDetail(StudentModel studentModel) {
        Log.d("tag", "showDetail: " + studentModel.getFirstName());
        firstName.setText(studentModel.getFirstName());
        secondName.setText(studentModel.getSecondName());
        gender.setText(studentModel.getGender());
        age.setText(studentModel.getId().toString());
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMarks(List<MarksModel> marksModel) {
        if (marksModel.isEmpty()) {
            return;
        }
        marksData.clear();
        marksData.addAll(marksModel);
        marksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }

    @Override
    public void onAddMark(MarksModel marksModel) {
        studentDetailPresenter.addMark(marksModel);
        getParentFragmentManager().popBackStack();
        addMarkFragment=null;
        studentDetailPresenter.loadMarks(studentID);
    }
}