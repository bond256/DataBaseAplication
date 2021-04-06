package com.example.databaseaplication.classRoom;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.databaseaplication.R;
import com.example.databaseaplication.adapters.ClassAdapter;
import com.example.databaseaplication.classroomdetail.ClassRoomDetailFragment;
import com.example.databaseaplication.model.ClassRoomModel;
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
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_class, container, false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        classRecycler = view.findViewById(R.id.classRecycler);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshClassRooms);
        setHasOptionsMenu(true);
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
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            classListPresenter.loadClass();
        });
    }

    private void init() {
        data = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        classRecycler.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(data);
        classAdapter.setOnItemMenuClickListener(this);
        classRecycler.setAdapter(classAdapter);
        classListPresenter = new ClassRoomListPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                classListPresenter.loadClassRoomsByName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    classListPresenter.loadClass();
                }
                return true;
            }
        });
        menu.findItem(R.id.action_filter).setVisible(false);
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
    public void showError(String message) {
        Log.d("tag", "showError: "+message);
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
        classListPresenter.loadClass();
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
        classListPresenter.deleteClassRoom(26);
        //data.remove(position);
        //classAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onEdit(ClassRoomModel classRoomModel) {
        classListPresenter.editClassRoom(classRoomModel);
        getParentFragmentManager().popBackStack();
        editDialogFragment = null;
    }

}