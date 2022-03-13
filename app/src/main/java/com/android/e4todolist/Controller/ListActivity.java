package com.android.e4todolist.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.e4todolist.AdapterTask;
import com.android.e4todolist.Model.Task;
import com.android.e4todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {
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
        checkIntent();

        buttonAdd = findViewById(R.id.fab);
        buttonAdd.setOnClickListener(v -> openFragmentActivity());
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
    private void openFragmentActivity() {
        CreateTaskFragment.newInstance(taskList).show(getSupportFragmentManager(), "ActionBottomDialog");
    }


    /**
     * Function that adds the task to the recyclerview list
     */
    public void setTasks() {
        tasksRecyclerView = findViewById(R.id.list);
        //tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterTask = new AdapterTask(taskList);
        tasksRecyclerView.setAdapter(adapterTask);
    }

    /**
     * Function that receives a string and checks if a new task has been introduced, if it has it calls the function to add it
     */
    public void checkIntent() {
        if (getIntent().getExtras() != null) {
            Intent intentGetter = getIntent();
            taskList = intentGetter.getParcelableArrayListExtra("list");
            Log.d("myTag", "list" + taskList.get(0));
        }
        setTasks();
    }
}