package com.example.databaseaplication.classroomdetail;

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

import com.example.databaseaplication.adapters.StudentsAdapter;
import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.model.StudentModel;
import com.example.databaseaplication.R;
import com.example.databaseaplication.studentdetail.StudentDetailFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ClassRoomDetailFragment extends Fragment implements ClassRoomDetailContract.View,
        StudentsAdapter.ItemMenuListener,
        AddStudentFragment.AddDialogListener,
        EditStudentFragment.OnEditStudentListener {

    private RecyclerView studentsRecycler;
    private TextView nameClass;
    private StudentsAdapter studentsAdapter;
    private List<StudentModel> studentDateList;
    private AddStudentFragment addDialogFragment;
    private EditStudentFragment editStudentFragment;
    private String classId;
    private ClassRoomDetailPresenter classDetailPresenter;
    private TextView nameDetailClass;
    private TextView typeDetailClass;
    private TextView levelDetailClass;
    private TextView numberDetailClass;
    private FloatingActionButton fabAddStudent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            classId = getArguments().getString("id");
        }
        classDetailPresenter=new ClassRoomDetailPresenter(this,getContext());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_class_detail, container, false);
        studentsRecycler=view.findViewById(R.id.studentsRecycler);
        nameDetailClass=view.findViewById(R.id.nameDetailClass);
        typeDetailClass=view.findViewById(R.id.typeDetailClass);
        levelDetailClass=view.findViewById(R.id.levelDetailClass);
        numberDetailClass=view.findViewById(R.id.numberDetailClass);
        fabAddStudent=view.findViewById(R.id.fab_add_student);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        studentsRecycler.setLayoutManager(layoutManager);
        studentDateList=new ArrayList<>();
        studentsAdapter=new StudentsAdapter(studentDateList);
        studentsAdapter.setOnItemMenuClickListener(this);
        studentsRecycler.setAdapter(studentsAdapter);
        classDetailPresenter.loadDetails(Integer.parseInt(classId));
        classDetailPresenter.loadStudents(Integer.parseInt(classId));
        fabAddStudent.setOnClickListener(v -> {
            addDialogFragment= AddStudentFragment.newInstance(this,classId);
            getParentFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.main_fragment,addDialogFragment,null)
                    .commit();
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showDetails(ClassRoomModel classRoomModel) {
        Log.d("tag", "showDetails: "+classRoomModel.getName()+"/"+classRoomModel.getId());
        nameDetailClass.setText(classRoomModel.getName());
        typeDetailClass.setText("Type: "+classRoomModel.getTypeOfClass());
        levelDetailClass.setText("Level: "+classRoomModel.getLevel());
        numberDetailClass.setText("Number: "+classRoomModel.getNumberRoom());
    }

    @Override
    public void showError() {

    }

    @Override
    public void showStudent(List<StudentModel> studentModel) {
        if(studentModel.isEmpty()){
            return;
        }
        studentDateList.clear();
        studentDateList.addAll(studentModel);
        studentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditClick(int position) {
        editStudentFragment=EditStudentFragment.newInstance(this,studentDateList.get(position));
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack("null")
                .add(R.id.main_fragment,editStudentFragment,null)
                .commit();
    }

    @Override
    public void onDeleteClick(int position) {
        classDetailPresenter.deleteStudent(studentDateList.get(position).getId());
        classDetailPresenter.loadStudents(Integer.parseInt(classId));
    }

    @Override
    public void onItemClick(int position) {
        StudentDetailFragment studentDetailFragment=new StudentDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("id",studentDateList.get(position).getId());
        studentDetailFragment.setArguments(bundle);
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.main_fragment,studentDetailFragment,null)
                .commit();
    }

    @Override
    public void addCLick(StudentModel studentModel) {
        classDetailPresenter.addStudent(studentModel);
        getParentFragmentManager().popBackStack();
        addDialogFragment=null;
        classDetailPresenter.loadStudents(Integer.parseInt(classId));
    }

    @Override
    public void onEdit(StudentModel studentModel) {
        classDetailPresenter.editStudent(studentModel);
        getParentFragmentManager().popBackStack();
        editStudentFragment=null;
    }
}