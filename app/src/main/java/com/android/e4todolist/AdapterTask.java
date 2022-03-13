package com.android.e4todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.e4todolist.Model.Task;

import java.util.ArrayList;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox check;
        Button delete;

        ViewHolder(View view) {
            super(view);
            check = view.findViewById(R.id.itemCheck);
            delete = view.findViewById(R.id.btn_delete);

        }
    }

    private ArrayList<Task> list;

    public AdapterTask(ArrayList<Task> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int pos = position;
        holder.check.setText(list.get(position).getName());
        holder.check.setChecked(list.get(position).getDone());
        holder.check.setOnCheckedChangeListener((buttonView, isChecked) -> list.get(pos).setDone(isChecked));
        holder.delete.setOnClickListener(v -> deleteItem(pos));

    }

    public void deleteItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
