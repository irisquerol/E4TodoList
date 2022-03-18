package com.android.e4todolist.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.e4todolist.AdapterTask;
import com.android.e4todolist.Model.Task;
import com.android.e4todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements MyListener {
    private FloatingActionButton buttonAdd;
    private RecyclerView tasksRecyclerView;
    ArrayList<Task> taskList = new ArrayList<>();
    AdapterTask adapterTask;

    /**
     * Sets contentView and calls the different methods used bu the app
     *
     * @param savedInstanceState bundle
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTasks();
        setContentView(R.layout.activity_list);
        setTasks();

        buttonAdd = findViewById(R.id.fab);
        buttonAdd.setOnClickListener(v -> openCreateTaskFragment());
    }

    /**
     * Adds the default task to the app every time the app is started
     */
    private void setDefaultTasks() {
        taskList.add(new Task(getResources().getString(R.string.task1)));
        taskList.add(new Task(getResources().getString(R.string.task2)));
        taskList.add(new Task(getResources().getString(R.string.task3)));
        taskList.add(new Task(getResources().getString(R.string.task4)));
        taskList.add(new Task(getResources().getString(R.string.task5)));

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
        tasksRecyclerView = findViewById(R.id.list);
        adapterTask = new AdapterTask(this, taskList);
        tasksRecyclerView.setAdapter(adapterTask);
    }


    @Override
    public void sendTaskName(String taskName, int pos) {
        if (pos == 0) {
            taskList.add(new Task(taskName));
        }

        setTasks();
    }
}
