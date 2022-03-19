package com.android.e4todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.e4todolist.Controller.ListActivity;
import com.android.e4todolist.Model.Task;
import com.android.e4todolist.Model.TaskManager;

import java.util.ArrayList;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task_checkbox;
        ImageButton delete_btn;
        ImageButton edit_btn;


        ViewHolder(View view) {
            super(view);
            task_checkbox = view.findViewById(R.id.itemCheck);
            delete_btn = view.findViewById(R.id.btn_delete);
            edit_btn = view.findViewById(R.id.btn_save_edit);
        }


        public void bind(int pos) {
            task_checkbox.setText(list.get(pos).getName());
            task_checkbox.setChecked(list.get(pos).isDone());
            delete_btn.setOnClickListener(v -> deleteItem(list, pos));
            edit_btn.setOnClickListener(v -> editItem(list, pos));
            task_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        list.get(pos).setDone(isChecked);
                        TaskManager.getInstance().saveTasks();
                    }
                }
            );
            //The default tasks cannot be edited or deleted:
            if (pos <= 4) {
                delete_btn.setVisibility(View.GONE);
                edit_btn.setVisibility(View.GONE);
            }
        }

        public void deleteItem(ArrayList<Task> list, int pos) {
            TaskManager.getInstance().removeTask(pos);
            TaskManager.getInstance().saveTasks();

            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, list.size());
        }

        public void editItem(ArrayList<Task> list, int pos) {
            activity.openEditTaskFragment(list, pos);
        }

    }

    private ArrayList<Task> list;
    private ListActivity activity;

    public AdapterTask(ListActivity activity, ArrayList<Task> list) {
        this.activity = activity;
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
        holder.bind(position);
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
