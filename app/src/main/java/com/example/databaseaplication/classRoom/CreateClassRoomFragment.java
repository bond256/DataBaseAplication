package com.example.databaseaplication.classRoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.databaseaplication.R;
import com.example.databaseaplication.model.ClassRoomModel;

public class CreateClassRoomFragment extends Fragment {
    private EditText nameClass;
    private EditText numberClass;
    private EditText floorClass;
    private EditText typeClass;
    private Button createButton;
    private FragmentDialogListener fragmentDialogListener;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CreateClassRoomFragment() {
        // Required empty public constructor
    }

    public static CreateClassRoomFragment newInstance(FragmentDialogListener fragmentListener) {
        CreateClassRoomFragment fragment = new CreateClassRoomFragment();
        fragment.fragmentDialogListener = fragmentListener;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_dialog, container, false);
        nameClass = view.findViewById(R.id.nameClassRoom);
        numberClass = view.findViewById(R.id.numberClassRoom);
        floorClass = view.findViewById(R.id.spinnerGender);
        typeClass = view.findViewById(R.id.age);
        createButton = view.findViewById(R.id.create_student);
        createButton.setOnClickListener(v -> {
            String name = nameClass.getText().toString();
            String number = numberClass.getText().toString();
            String floor = floorClass.getText().toString();
            String type = typeClass.getText().toString();
            if (!name.equals("") && !number.equals("") && !floor.equals("") && !type.equals("")) {
                fragmentDialogListener.onApply(new ClassRoomModel(0, name, type, Integer.valueOf(number), Integer.valueOf(floor)));
            } else Toast.makeText(getActivity(), "Please input data", Toast.LENGTH_LONG).show();
        });
        return view;
    }

    public interface FragmentDialogListener {
        void onApply(ClassRoomModel classRoomModel);
    }
}