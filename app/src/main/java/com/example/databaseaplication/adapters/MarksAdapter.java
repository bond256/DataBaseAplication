package com.example.databaseaplication.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.R;

import java.util.List;

public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.ViewHolder> {
    private List<MarksModel> marksData;
    private ItemMenuListener itemMenuListener;


    public MarksAdapter(List<MarksModel> marksModelsList) {
        this.marksData=marksModelsList;
    }

    public void setOnItemMenuClickListener(ItemMenuListener itemMenuClickListener){
        this.itemMenuListener=itemMenuClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marks_list, parent, false);
        return new ViewHolder(view,itemMenuListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameSubject.setText(marksData.get(position).getSubjectName());
        holder.markSubject.setText(marksData.get(position).getMark().toString());
        holder.dateSubject.setText(marksData.get(position).getDataMark());
    }

    @Override
    public int getItemCount() {
        return marksData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private final TextView nameSubject;
        private final TextView markSubject;
        private final TextView dateSubject;
        private final ImageButton editButton;
        private final ImageButton deleteButton;
        private final ItemMenuListener itemMenuListener;

        public ViewHolder(View view, ItemMenuListener itemMenuListener) {
            super(view);
            nameSubject = view.findViewById(R.id.nameSubject);
            markSubject=view.findViewById(R.id.markSubject);
            dateSubject=view.findViewById(R.id.dateSubject);
            editButton=view.findViewById(R.id.editMark);
            deleteButton=view.findViewById(R.id.deleteMark);
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
