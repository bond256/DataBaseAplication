package com.example.databaseaplication.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.R;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<ClassRoomModel> classRoomsData;
    private ItemMenuListener itemMenuListener;


    public ClassAdapter(List<ClassRoomModel> classRoomsData) {
        this.classRoomsData = classRoomsData;
    }

    public void setOnItemMenuClickListener(ItemMenuListener itemMenuClickListener){
        this.itemMenuListener=itemMenuClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_list, parent, false);
        return new ViewHolder(view,itemMenuListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameClass.setText(classRoomsData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return classRoomsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private final TextView nameClass;
        private final ImageButton editButton;
        private final ImageButton deleteButton;
        private final ItemMenuListener itemMenuListener;

        public ViewHolder(View view,ItemMenuListener itemMenuListener) {
            super(view);
            nameClass = view.findViewById(R.id.nameClassRoom);
            editButton=view.findViewById(R.id.edit_class_room);
            deleteButton=view.findViewById(R.id.delete_class_room);
            this.itemMenuListener=itemMenuListener;
            view.setOnClickListener(this);
            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
        }


        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.edit_class_room: itemMenuListener.onEditClick(getAdapterPosition());
                break;
                case R.id.delete_class_room: itemMenuListener.onDeleteClick(getAdapterPosition());
                break;
                case R.id.item_class: itemMenuListener.onItemClick(getAdapterPosition());
                break;
            }
        }
    }

    public interface ItemMenuListener{
        void onEditClick(int position);
        void onDeleteClick(int position);
        void onItemClick(int position);
    }


}
