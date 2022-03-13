package com.android.e4todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    //FragmentManager fm = getSupportFragmentManager();
    //Fragment fragment = fm.findFragmentById(R.id.fragment_container);
    private ArrayList<String> tasks = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        checkIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().findFragmentById(R.id.list);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateTaskActivity();

            }
        });
    }

    public void openCreateTaskActivity() {
        Intent intent = new Intent(MainActivity.this, CreateActivityTask.class);
        // intent.putExtra("subjectID", );
        startActivity(intent);
    }

    public void setTasks() {
        CheckBox item = findViewById(R.id.itemCheck);
        for (String task: tasks) {
            
        }
    }

    public void checkIntent() {
        if (getIntent().getExtras() != null) {
            Intent intentGetter = getIntent();
            String strValue = intentGetter.getStringExtra("taskTitle");
            if (!strValue.equals("")) {
                tasks.add(strValue);
                Log.d("myTag", "This is the  variable value: " + strValue);
                setTasks();
            }

        }
    }
}