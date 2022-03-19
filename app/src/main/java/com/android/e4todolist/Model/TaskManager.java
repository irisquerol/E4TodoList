package com.android.e4todolist.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.e4todolist.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TaskManager {
    private static TaskManager single_instance = null;
    ArrayList<Task> taskList;
    Context context;

    private TaskManager(Context context) {
        this.taskList = new ArrayList<>();
        this.context = context;

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String objectJSON = sharedPref.getString("taskList", null);
        if (objectJSON != null) {
            Type type = new TypeToken<ArrayList<Task>>(){}.getType();
            taskList = new Gson().fromJson(objectJSON, type);
        } else {
            taskList.add(new Task(context.getResources().getString(R.string.task1)));
            taskList.add(new Task(context.getResources().getString(R.string.task2)));
            taskList.add(new Task(context.getResources().getString(R.string.task3)));
            taskList.add(new Task(context.getResources().getString(R.string.task4)));
            taskList.add(new Task(context.getResources().getString(R.string.task5)));
        }
    }

    public static TaskManager getInstance(Context context) {
        if (single_instance == null) single_instance = new TaskManager(context);
        return single_instance;
    }

    public static TaskManager getInstance() {
        return single_instance;
    }

    public void saveTasks() {
        Gson gson = new Gson();
        String json = gson.toJson(taskList);

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("taskList", json);
        editor.apply();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int idTask) {
        taskList.remove(idTask);
    }
}