package com.example.databaseaplication.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseaplication.Model.ClassRoomModel;
import com.example.databaseaplication.R;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<ClassRoomModel> classRoomsData;
    private ItemClickListener itemClickListener;
    private ItemMenuListener itemMenuListener;


    public ClassAdapter(List<ClassRoomModel> classRoomsData,ItemClickListener itemClickListener) {
        this.classRoomsData = classRoomsData;
        this.itemClickListener=itemClickListener;
    }

    public void setOnItemMenuClickListener(ItemMenuListener itemMenuClickListener){
        this.itemMenuListener=itemMenuClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_list, parent, false);
        return new ViewHolder(view,itemClickListener,itemMenuListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameClass.setText(classRoomsData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return classRoomsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        private final TextView nameClass;
        private final ImageButton editButton;
        private final ImageButton deleteButton;
        private final ItemClickListener itemClickListener;
        private final ItemMenuListener itemMenuListener;

        public ViewHolder(View view,ItemClickListener itemClickListener,ItemMenuListener itemMenuListener) {
            super(view);
            nameClass = view.findViewById(R.id.nameClass);
            editButton=view.findViewById(R.id.editClassRoom);
            deleteButton=view.findViewById(R.id.deleteClassRoom);
            this.itemClickListener=itemClickListener;
            this.itemMenuListener=itemMenuListener;
            view.setOnLongClickListener(this);
            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
            return true;
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.editClassRoom: itemMenuListener.onEditClick(getAdapterPosition());
                break;
                case R.id.deleteClassRoom: itemMenuListener.onDeleteClick(getAdapterPosition());
                break;
            }
        }
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }


    public interface ItemMenuListener{
        void onEditClick(int position);
        void onDeleteClick(int position);
    }


}
