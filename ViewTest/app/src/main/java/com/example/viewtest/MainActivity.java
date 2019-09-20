package com.example.viewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.viewtest.view.NumberProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = MainActivity.class.getName();

    public static final int PROGRESSBAR = 0;
    public static final int LIFECYCLE = 2;

    private Button progressBtn;
    private Button lifeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBtn = findViewById(R.id.progress_button);
        progressBtn.setOnClickListener(this);

        lifeBtn = findViewById(R.id.life_button);
        lifeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int type = 0;
        switch (id){
            case R.id.progress_button:
                type = PROGRESSBAR;
                break;
            case R.id.life_button:
                type = LIFECYCLE;
                break;
        }
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
