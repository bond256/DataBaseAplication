package com.example.databaseaplication.classRoom;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseaplication.MainInterfaceCallBack;
import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.R;
import com.example.databaseaplication.adapters.ClassAdapter;
import com.example.databaseaplication.classroomdetail.ClassDetailFragment;
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
    private MainInterfaceCallBack mainInterfaceCallBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_class, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        init(view);
        classListPresenter.loadClass();
        floatingActionButton.setOnClickListener(v -> {
            createDialogFragment = CreateDialogFragment.newInstance(this);
            getParentFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.main_fragment, createDialogFragment, null)
                    .commit();
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainInterfaceCallBack) {
            mainInterfaceCallBack = (MainInterfaceCallBack) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void init(View view) {
        classRecycler = view.findViewById(R.id.classRecycler);
        data = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        classRecycler.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(data);
        classAdapter.setOnItemMenuClickListener(this);
        classRecycler.setAdapter(classAdapter);
        classListPresenter = new ClassListPresenter(this, getContext());
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
        ClassDetailFragment classDetailFragment = new ClassDetailFragment();
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