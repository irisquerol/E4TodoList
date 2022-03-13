package com.android.e4todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateActivityTask extends AppCompatActivity {
    private Button btn;
    private EditText new_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        btn = findViewById(R.id.btn_save);
        btn.setOnClickListener(v -> {
            new_title = findViewById(R.id.new_task_title);
            openListActivity(new_title.getText().toString());

        });

    }

    public void openListActivity(String title) {
        Intent intent = new Intent(CreateActivityTask.this, MainActivity.class);
        intent.putExtra("taskTitle", title);
        startActivity(intent);
    }

}