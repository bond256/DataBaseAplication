package com.example.databaseaplication.classRoom;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseaplication.MainInterfaceCallBack;
import com.example.databaseaplication.filters.MarksFilterDialogFragment;
import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.R;
import com.example.databaseaplication.adapters.ClassAdapter;
import com.example.databaseaplication.classroomdetail.ClassRoomDetailFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListClassRoomFragment extends Fragment implements ClassRoomListContract.View,
        CreateClassRoomFragment.FragmentDialogListener,
        ClassAdapter.ItemMenuListener,
        EditClassRoomFragment.FragmentEditListener {

    private RecyclerView classRecycler;
    private ClassRoomListPresenter classListPresenter;
    private ClassAdapter classAdapter;
    private ArrayList<ClassRoomModel> data;
    private CreateClassRoomFragment createDialogFragment;
    private EditClassRoomFragment editDialogFragment;
    private MainInterfaceCallBack mainInterfaceCallBack;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_class, container, false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        classRecycler = view.findViewById(R.id.classRecycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        classListPresenter.loadClass();
        floatingActionButton.setOnClickListener(v -> {
            createDialogFragment = CreateClassRoomFragment.newInstance(this);
            getParentFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.main_fragment, createDialogFragment, null)
                    .commit();
        });
    }

    private void init() {
        data = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        classRecycler.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(data);
        classAdapter.setOnItemMenuClickListener(this);
        classRecycler.setAdapter(classAdapter);
        classListPresenter = new ClassRoomListPresenter(this, getContext());
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
        ClassRoomDetailFragment classDetailFragment = new ClassRoomDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", data.get(position).getId().toString());
        classDetailFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .addToBackStack("detail")
                .add(R.id.main_fragment, classDetailFragment, null)
                .commit();
    }


    @Override
    public void onApply(ClassRoomModel classRoomModel) {
        classListPresenter.addClass(classRoomModel);
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void onEditClick(int position) {
        editDialogFragment = EditClassRoomFragment.newInstance(this, data.get(position));
        getParentFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, editDialogFragment, null)
                .commit();
    }

    @Override
    public void onDeleteClick(int position) {
//        classListPresenter.deleteClassRoom(data.get(position).getId());
//        data.remove(position);
//        classAdapter.notifyItemRemoved(position);
        MarksFilterDialogFragment filtersFragment=new MarksFilterDialogFragment();
        getParentFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragment,filtersFragment,null)
                .commit();
    }

    @Override
    public void onEdit(ClassRoomModel classRoomModel) {
        classListPresenter.editClassRoom(classRoomModel);
        getParentFragmentManager().popBackStack();
        editDialogFragment = null;
    }

}