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


    /**
     * Methods that calls an item of a task ui (fragment_task.xml)
     *
     * @param parent   Viewgroup
     * @param viewType int
     * @return itemview
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Function that manages the position of each task and knows if a task boxcheck has been checked
     * and updates it,
     * sets invisible the buttons for default tasks
     *
     * @param holder   view
     * @param position integer of tasks
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int pos = position;
        holder.check.setText(list.get(position).getName());
        holder.check.setChecked(list.get(position).isDone());
        if (pos == 0 || pos == 1 || pos == 2 || pos == 3 || pos == 4) {
            holder.delete.setVisibility(View.GONE);
        }
        holder.delete.setOnClickListener(v -> deleteItem(pos));
        holder.check.setOnCheckedChangeListener((buttonView, isChecked) -> list.get(pos).setDone(isChecked));
    }

    public void deleteItem(int position) {
        if (list.get(position) != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Returns the list size
     *
     * @return size of items in list
     */
    @Override
    public int getItemCount() {
        return list.size();
    }
}
