package com.example.databaseaplication.classRoom;

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

import com.example.databaseaplication.Adapters.ClassAdapter;
import com.example.databaseaplication.Model.ClassRoomModel;
import com.example.databaseaplication.R;
import com.example.databaseaplication.classRoom.ClassListContract;
import com.example.databaseaplication.classRoom.ClassListPresenter;
import com.example.databaseaplication.classRoom.CreateDialogFragment;
import com.example.databaseaplication.classRoom.EditDialogFragment;
import com.example.databaseaplication.classdetail.ClassDetailFragmnet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListClassFragment extends Fragment implements ClassListContract.View,
        CreateDialogFragment.FragmentDialogListener,
        ClassAdapter.ItemMenuListener,
        EditDialogFragment.FragmentEditListener {

    private RecyclerView classRecycler;
    private ClassListPresenter classListPresenter;
    private ClassAdapter classAdapter;
    private ArrayList<ClassRoomModel> data;
    private CreateDialogFragment createDialogFragment;
    private EditDialogFragment editDialogFragment;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_class, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        classRecycler = view.findViewById(R.id.classRecycler);
        data = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        classRecycler.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(data);
        classAdapter.setOnItemMenuClickListener(this);
        classRecycler.setAdapter(classAdapter);
        classListPresenter = new ClassListPresenter(this, getContext());
        classListPresenter.loadClass();
        floatingActionButton.setOnClickListener(v -> {
            createDialogFragment = CreateDialogFragment.newInstance(this);
            getParentFragmentManager().beginTransaction()
                    .addToBackStack("fsdfsdf")
                    .add(R.id.main_fragment, createDialogFragment, null)
                    .commit();
        });
        return view;
    }


    @Override
    public void showList(List<ClassRoomModel> classRooms) {
        if (classRooms.isEmpty()) {
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
        ClassDetailFragmnet classDetailFragmnet=new ClassDetailFragmnet();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment,classDetailFragmnet,null)
                .commit();
        //classListPresenter.deleteClassRoom(data.get(position).getId());
        Log.d("tag", "onItemClick: ");
    }


    @Override
    public void onApply(ClassRoomModel classRoomModel) {
        classListPresenter.addClass(classRoomModel);
        getParentFragmentManager().beginTransaction().remove(createDialogFragment).commit();
        createDialogFragment = null;
    }

    @Override
    public void onEditClick(int position) {
        editDialogFragment = EditDialogFragment.newInstance(this, data.get(position));
        getParentFragmentManager().beginTransaction()
                .add(R.id.main_fragment, editDialogFragment, null)
                .commit();
    }

    @Override
    public void onDeleteClick(int position) {
        classListPresenter.deleteClassRoom(data.get(position).getId());
        data.remove(position);
        classAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onEdit(ClassRoomModel classRoomModel) {
        classListPresenter.editClassRoom(classRoomModel);
        getParentFragmentManager().beginTransaction().remove(editDialogFragment).commit();
        editDialogFragment = null;
    }

}