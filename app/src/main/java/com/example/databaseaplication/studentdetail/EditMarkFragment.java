package com.example.databaseaplication.studentdetail;

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

import com.example.databaseaplication.R;
import com.example.databaseaplication.model.MarksModel;


public class EditMarkFragment extends Fragment {
    private EditMarkFragmentListener editMarkFragmentListener;
    private MarksModel marksModel;
    private EditText subjectName;
    private EditText subjectMark;
    private Button editMark;
    private EditText date;


    public static EditMarkFragment newInstance(EditMarkFragmentListener editMarkFragment, MarksModel marksModel) {
        EditMarkFragment fragment = new EditMarkFragment();
        fragment.editMarkFragmentListener = editMarkFragment;
        fragment.marksModel = marksModel;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_mark, container, false);
        subjectName = view.findViewById(R.id.subjectNameEdit);
        subjectMark = view.findViewById(R.id.subjectMarkEdit);
        date = view.findViewById(R.id.dateMarkEdit);
        editMark = view.findViewById(R.id.edit_mark);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subjectName.setText(marksModel.getSubjectName());
        subjectMark.setText(marksModel.getMark().toString());
        date.setText(marksModel.getDataMark());
        editMark.setOnClickListener(v -> {
            Integer id = marksModel.getId();
            Integer studentId = marksModel.getStudentId();
            String name = subjectName.getText().toString();
            String mark = subjectMark.getText().toString();
            String markDate = date.getText().toString();
            if (!name.equals("") && !mark.equals("") && !markDate.equals("")) {
                editMarkFragmentListener.onEditMark(new MarksModel(id, name, studentId, Integer.parseInt(mark), markDate));
            }
        });
    }

    public interface EditMarkFragmentListener {
        void onEditMark(MarksModel marksModel);
    }
}