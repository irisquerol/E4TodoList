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

    /**
     * Creates the default tasks and the class singelton, if there are values stored it loads.
     * @param context  context
     */
    private TaskManager(Context context) {
        this.taskList = new ArrayList<>();
        this.context = context;

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String objectJSON = sharedPref.getString("taskList", null);
        if (objectJSON != null) {
            Type type = new TypeToken<ArrayList<Task>>() {
            }.getType();
            taskList = new Gson().fromJson(objectJSON, type);
        } else {
            /*api*/

        }
    }

    /**
     * Gets instance of singelton
     */
    public static TaskManager getInstance(Context context) {
        if (single_instance == null) single_instance = new TaskManager(context);
        return single_instance;
    }
    /**
     * Gets the instance of singelton
     */
    public static TaskManager getInstance() {
        return single_instance;
    }

    /**
     * Saves the tasks in the phone memory
     */
    public void saveTasks() {
        Gson gson = new Gson();
        String json = gson.toJson(taskList);

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("taskList", json);
        editor.apply();
    }

    /**returns tasks list
     * @return list task
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**Adds a task
     * @param task task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**Removes a task
     * @param idTask int
     */
    public void removeTask(int idTask) {
        taskList.remove(idTask);
    }
}