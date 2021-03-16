package com.example.databaseaplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.databaseaplication.Adapters.ClassAdapter;
import com.example.databaseaplication.Model.ClassRoomModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListClassFragment extends Fragment implements ClassListContract.View,
        ClassAdapter.ItemClickListener,
        CreateDialogFragment.FragmentDialogListener,
        ClassAdapter.ItemMenuListener,
        EditDialogFragment.FragmentEditListener {

    private RecyclerView classRecycler;
    private ClassListPresenter classListPresenter;
    private ClassAdapter classAdapter;
    private ArrayList<ClassRoomModel> data;
    private CreateDialogFragment createDialogFragment;
    private EditDialogFragment editDialogFragment;

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
        FloatingActionButton floatingActionButton=view.findViewById(R.id.floatingActionButton);
        classRecycler= view.findViewById(R.id.classRecycler);
        data=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        classRecycler.setLayoutManager(layoutManager);
        classAdapter=new ClassAdapter(data,this);
        classAdapter.setOnItemMenuClickListener(this);
        classRecycler.setAdapter(classAdapter);
        classListPresenter=new ClassListPresenter(this, getContext());
        classListPresenter.loadClass();
        ClassRoomModel classRoomModel=new ClassRoomModel(0,"chemestry","univers",111,5);
        floatingActionButton.setOnClickListener(v -> {
            //classListPresenter.addClass(classRoomModel);
            createDialogFragment=CreateDialogFragment.newInstance(this);
            getParentFragmentManager().beginTransaction()
                    .add(R.id.main_fragment,createDialogFragment,null)
                    .commit();

        });

        return view;
    }


    @Override
    public void showList(List<ClassRoomModel> classRooms) {
        if(classRooms.isEmpty()){
            data.clear();
            classAdapter.notifyDataSetChanged();
            return;
        }
        data.clear();
        data.addAll(classRooms);
        classAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Log.d("tag", "showError: ");

    }

    @Override
    public void onItemClick(int position) {
        classListPresenter.deleteClassRoom(data.get(position).getId());

    }


    @Override
    public void onApply(ClassRoomModel classRoomModel) {
        classListPresenter.addClass(classRoomModel);
        getParentFragmentManager().beginTransaction().remove(createDialogFragment).commit();

    }

    @Override
    public void onEditClick(int position) {
        editDialogFragment=EditDialogFragment.newInstance(this,)
        getParentFragmentManager().beginTransaction()
                .add(R.id.main_fragment,createDialogFragment,null)
                .commit();
    }

    @Override
    public void onDeleteClick(int position) {
        classListPresenter.deleteClassRoom(data.get(position).getId());
    }

    @Override
    public void onEdit(ClassRoomModel classRoomModel) {
         classListPresenter.editClassRoom(classRoomModel);
    }
}