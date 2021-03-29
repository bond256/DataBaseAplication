package com.example.databaseaplication.filters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.databaseaplication.R;


public class FiltersFragment extends Fragment {
    private Spinner filterSpinner;
    private RecyclerView searchRecycler;

    public static FiltersFragment newInstance(String param1, String param2) {
        FiltersFragment fragment = new FiltersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_filters, container, false);
        filterSpinner=view.findViewById(R.id.filter_spinner);
        searchRecycler=view.findViewById(R.id.resultRecycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] itemSpinner={"Name","Surname","Age","ClassName","Number Room","Level","Gender","Subject","Mark","Data Mark"};
        ArrayAdapter<String> filterParamAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,itemSpinner);
        filterParamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterParamAdapter);
    }
}