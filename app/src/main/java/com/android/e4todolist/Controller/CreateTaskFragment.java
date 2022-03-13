package com.android.e4todolist.Controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.e4todolist.Model.Task;
import com.android.e4todolist.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class CreateTaskFragment extends BottomSheetDialogFragment {

    private Button btn_add;
    private EditText new_title;
    ArrayList<Task> taskList;

    public static CreateTaskFragment newInstance(ArrayList<Task> taskList) {
        return new CreateTaskFragment(taskList);
    }

    public CreateTaskFragment(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_task, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new_title = getView().findViewById(R.id.new_task_title);
        btn_add = getView().findViewById(R.id.btn_save);

        btn_add.setOnClickListener(v -> {
            String taskTitle = new_title.getText().toString();
            if (!taskTitle.equals("")) {
                taskList.add(new Task(taskTitle));
            }
            Intent intent = new Intent(getActivity(), ListActivity.class);
            intent.putExtra("list", taskList);
            startActivity(intent);
            dismiss();
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        Activity activity = getActivity();

    }

}