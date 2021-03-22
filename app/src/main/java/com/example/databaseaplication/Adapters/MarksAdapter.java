package com.example.databaseaplication.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseaplication.Model.StudentModel;
import com.example.databaseaplication.R;

import java.util.List;

public class MarksAdapter extends RecyclerView.Adapter<> {
    private List<StudentModel> studentsData;
    private StudentsAdapter.ItemMenuListener itemMenuListener;


    public MarksAdapter(List<StudentModel> studentModelsList) {
        this.studentsData=studentModelsList;
        //this.itemClickListener=itemClickListener;
    }

    public void setOnItemMenuClickListener(StudentsAdapter.ItemMenuListener itemMenuClickListener){
        this.itemMenuListener=itemMenuClickListener;
    }

    @NonNull
    @Override
    public StudentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students_list, parent, false);
        return new StudentsAdapter.ViewHolder(view,itemMenuListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameStudent.setText(studentsData.get(position).getFirstName()+" "+studentsData.get(position).getSecondName());
    }

    @Override
    public int getItemCount() {
        return studentsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private final TextView nameStudent;
        private final ImageButton editButton;
        private final ImageButton deleteButton;
        private final StudentsAdapter.ItemMenuListener itemMenuListener;

        public ViewHolder(View view, StudentsAdapter.ItemMenuListener itemMenuListener) {
            super(view);
            nameStudent = view.findViewById(R.id.nameStudent);
            editButton=view.findViewById(R.id.editStudent);
            deleteButton=view.findViewById(R.id.deleteStudent);
            this.itemMenuListener=itemMenuListener;
            view.setOnClickListener(this);
            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
        }


        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.editStudent: itemMenuListener.onEditClick(getAdapterPosition());
                    break;
                case R.id.deleteStudent: itemMenuListener.onDeleteClick(getAdapterPosition());
                    break;
            }
        }
    }



    public interface ItemMenuListener{
        void onEditClick(int position);
        void onDeleteClick(int position);
    }
}
