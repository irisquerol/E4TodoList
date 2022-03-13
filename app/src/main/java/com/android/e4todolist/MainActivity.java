package com.android.e4todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn= findViewById(R.id.btn_go);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
}