package com.example.databaseaplication.studentdetail;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseaplication.R;
import com.example.databaseaplication.adapters.MarksAdapter;
import com.example.databaseaplication.filters.MarksFilterDialogFragment;
import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.model.StudentModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailFragment extends Fragment implements StudentDetailContract.View,
        MarksAdapter.ItemMenuListener,
        AddMarkFragment.OnAddFragmentListener,
        EditMarkFragment.EditMarkFragmentListener,
        MarksFilterDialogFragment.OnMarkDialogFragmentListener {
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
    private EditMarkFragment editMarkFragment;
    private FloatingActionButton addButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentID = getArguments().getInt("id");
        }
        setHasOptionsMenu(true);

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
        Toolbar myToolbar = view.findViewById(R.id.studentDetailToolBar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_class_room, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //searchView.clearFocus();
                studentDetailPresenter.loadSubject("math");
                Log.d("tag", "onQueryTextChange: " + query);
                //searchView.onActionViewCollapsed();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.d("tag", "onQueryTextChange: "+newText);
                return true;
            }
        });

        Button button = (Button) menu.findItem(R.id.action_filter).getActionView();
        button.setOnClickListener(v -> {
            MarksFilterDialogFragment marksFilterDialogFragment = new MarksFilterDialogFragment();
            marksFilterDialogFragment.setMarkDialogListener(this);
            marksFilterDialogFragment.show(getParentFragmentManager(), null);
        });
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void showDetail(StudentModel studentModel) {
        firstName.setText("First name: " + studentModel.getFirstName());
        secondName.setText("Second name: " + studentModel.getSecondName());
        gender.setText("Gender: " + studentModel.getGender());
        age.setText("Age: " + studentModel.getId().toString());
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMarks(List<MarksModel> marksModel) {
        if (marksModel.isEmpty()) {
            marksData.clear();
            marksAdapter.notifyDataSetChanged();
            return;
        }
        marksData.clear();
        marksData.addAll(marksModel);
        marksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditClick(int position) {
        editMarkFragment = EditMarkFragment.newInstance(this, marksData.get(position));
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.main_fragment, editMarkFragment, null)
                .commit();
    }

    @Override
    public void onDeleteClick(int position) {
        studentDetailPresenter.deleteMark(marksData.get(position).getId());
        studentDetailPresenter.loadMarks(studentID);
    }

    @Override
    public void onAddMark(MarksModel marksModel) {
        studentDetailPresenter.addMark(marksModel);
        getParentFragmentManager().popBackStack();
        addMarkFragment = null;
        studentDetailPresenter.loadMarks(studentID);
    }

    @Override
    public void onEditMark(MarksModel marksModel) {
        studentDetailPresenter.editMark(marksModel);
        getParentFragmentManager().popBackStack();
        editMarkFragment = null;
        studentDetailPresenter.loadMarks(studentID);
    }

    @Override
    public void onMark(String mark) {
        studentDetailPresenter.loadMarks(Integer.parseInt(mark));
    }
}