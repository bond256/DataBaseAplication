package com.example.databaseaplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseaplication.Model.ClassRoomModel;
import com.example.databaseaplication.R;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<ClassRoomModel> classRoomsData;
    private ItemClickListener itemClickListener;


    public ClassAdapter(List<ClassRoomModel> classRoomsData,ItemClickListener itemClickListener) {
        this.classRoomsData = classRoomsData;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_list, parent, false);
        return new ViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameClass.setText(classRoomsData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return classRoomsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private final TextView nameClass;
        private final ItemClickListener itemClickListener;

        public ViewHolder(View view,ItemClickListener itemClickListener) {
            super(view);
            nameClass = view.findViewById(R.id.nameClass);
            this.itemClickListener=itemClickListener;
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
            return true;
        }
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }


}
