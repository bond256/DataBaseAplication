package com.example.databaseaplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.databaseaplication.repositoty.ClassRoomData;

public class ListClassFragment extends Fragment implements ClassListContract.View {

    private RecyclerView classRecycler;
    private ClassListPresenter classListPresenter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListClassFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ListClassFragment newInstance(String param1, String param2) {
        ListClassFragment fragment = new ListClassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_list_class, container, false);
        view.findViewById(R.id.classRecycler);
        classListPresenter=new ClassListPresenter(this);
        classListPresenter.loadClass();
        ClassRoomData classRoomData=new ClassRoomData(getActivity());
        classRoomData.insertData();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void showList() {
        Toast.makeText(getContext(),"ok",Toast.LENGTH_LONG).show();

    }

    @Override
    public void showError() {

    }
}