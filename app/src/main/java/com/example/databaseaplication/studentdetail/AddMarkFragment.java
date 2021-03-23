package com.example.databaseaplication.studentdetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.R;


public class AddMarkFragment extends Fragment {
    private OnAddFragmentListener onAddFragmentListener;
    private EditText subjectName;
    private EditText subjectMark;
    private Button addMark;
    private Integer id;
    private final String ID_ST="id_st";
    private EditText date;




    public static AddMarkFragment newInstance(OnAddFragmentListener onAddFragmentListener,Integer id) {
        AddMarkFragment fragment = new AddMarkFragment();
        fragment.onAddFragmentListener=onAddFragmentListener;
        Bundle bundle=new Bundle();
        bundle.putInt(fragment.ID_ST,id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id=getArguments().getInt(ID_ST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_mark, container, false);
        subjectName=view.findViewById(R.id.subjectName);
        subjectMark=view.findViewById(R.id.subjectMark);
        addMark=view.findViewById(R.id.create_mark);
        date=view.findViewById(R.id.dateMark);
        addMark.setOnClickListener(v -> {
            String name=subjectName.getText().toString();
            String mark=subjectMark.getText().toString();
            String markDate=date.getText().toString();
            if(!name.equals("")&&!mark.equals("")){
               onAddFragmentListener.onAddMark(new MarksModel(0,name,id,Integer.parseInt(mark),markDate));
            }
        });
        return view;
    }


    public interface OnAddFragmentListener{
        void onAddMark(MarksModel marksModel);
    }
}