package com.example.databaseaplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databaseaplication.Model.ClassRoomModel;

public class CreateDialogFragment extends Fragment {
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

    public CreateDialogFragment() {
        // Required empty public constructor
    }

    public static CreateDialogFragment newInstance(FragmentDialogListener fragmentListener) {
        CreateDialogFragment fragment = new CreateDialogFragment();
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
        floorClass = view.findViewById(R.id.floorClassRoom);
        typeClass = view.findViewById(R.id.typeOfClass);
        createButton = view.findViewById(R.id.create_class_room);
        createButton.setOnClickListener(v -> {
            String name = nameClass.getText().toString();
            String number = numberClass.getText().toString();
            String floor = floorClass.getText().toString();
            String type = typeClass.getText().toString();
//            if(name.equals(" ")){
//                Toast.makeText(getActivity(), "Please input data", Toast.LENGTH_SHORT).show();
//            }else if()
            fragmentDialogListener.onApply(new ClassRoomModel(0, name, type, Integer.valueOf(number), Integer.valueOf(floor)));
        });
        return view;
    }


    static

    public interface FragmentDialogListener {
        void onApply(ClassRoomModel classRoomModel);
    }
}