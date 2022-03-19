package com.android.e4todolist.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.e4todolist.AdapterTask;
import com.android.e4todolist.Model.Task;
import com.android.e4todolist.Model.TaskManager;
import com.android.e4todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements MyListener {
    private FloatingActionButton buttonAdd;
    private RecyclerView tasksRecyclerView;
    //ArrayList<Task> taskList = new ArrayList<>();
    private AdapterTask adapterTask;

    /**
     * Sets contentView and calls the different methods used bu the app
     *
     * @param savedInstanceState bundle
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_list);
        setTasks();

        buttonAdd = findViewById(R.id.fab);
        buttonAdd.setOnClickListener(v -> openCreateTaskFragment());

    }

    /**
     * Opens the Fragment to add new tasks.
     */
    private void openCreateTaskFragment() {
        CreateTaskFragment fragment = CreateTaskFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "ActionBottomDialog");
    }

    public void openEditTaskFragment(ArrayList<Task> list, int pos) {
        EditTaskFragment fragment = EditTaskFragment.newInstance(list, pos);
        fragment.show(getSupportFragmentManager(), "ActionBottomDialog");
    }


    /**
     * Function that adds the task to the recyclerview list
     */
    public void setTasks() {
        TaskManager.getInstance(this).saveTasks();
        tasksRecyclerView = findViewById(R.id.list);
        adapterTask = new AdapterTask(this, TaskManager.getInstance(this).getTaskList());
        tasksRecyclerView.setAdapter(adapterTask);
    }

    @Override
    public void sendTaskName(String taskName, int pos) {
        if (pos == 0) {
            TaskManager.getInstance(this).addTask(new Task(taskName));
        }
        setTasks();
    }
}
