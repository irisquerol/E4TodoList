package com.android.e4todolist.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.e4todolist.AdapterTask;
import com.android.e4todolist.Model.Task;
import com.android.e4todolist.Model.TaskManager;
import com.android.e4todolist.TaskListener;
import com.android.e4todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ListActivity extends AppCompatActivity implements TaskListener {
    private RecyclerView tasksRecyclerView;
    private AdapterTask adapterTask;

    /**
     * Sets contentView and calls the different methods used by the app
     *
     * @param savedInstanceState bundle
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTasks();
        FloatingActionButton buttonAdd = findViewById(R.id.fab);
        buttonAdd.setOnClickListener(v -> openCreateTaskFragment());

    }

    /**
     * Opens the {@link CreateTaskFragment} to add new tasks.
     */
    private void openCreateTaskFragment() {
        CreateTaskFragment fragment = CreateTaskFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "ActionBottomDialog");
    }

    /**
     * Opens the {@link EditTaskFragment} to edit a task
     *
     * @param pos task position user want to edit
     */
    public void openEditTaskFragment(int pos) {
        EditTaskFragment fragment = EditTaskFragment.newInstance(TaskManager.getInstance(this).getTaskList(), pos);
        fragment.show(getSupportFragmentManager(), "ActionBottomDialog");
    }


    /**
     * Function that adds the task to the recyclerview list
     */
    public void setTasks() {
        TaskManager.getInstance(this).saveTasks();
        tasksRecyclerView = findViewById(R.id.list);
        adapterTask = new AdapterTask(this);
        tasksRecyclerView.setAdapter(adapterTask);
    }

    /**
     * method from {@link TaskListener} interface
     * it adds to task manager new task or saves edited tasks
     *
     * @param taskName user input of the class title
     * @param pos      position in case of edit title task
     */
    @Override
    public void sendTaskName(String taskName, int pos) {
        if (pos == 0) {
            TaskManager.getInstance(this).addTask(new Task(taskName));
        }
        TaskManager.getInstance(this).saveTasks();
        tasksRecyclerView.setAdapter(adapterTask);
    }
}
