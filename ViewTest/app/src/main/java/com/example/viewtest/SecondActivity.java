package com.example.viewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.viewtest.fragment.ProgressFragment;
import com.example.viewtest.fragment.RoundImageFragment;
import com.example.viewtest.fragment.ScrollViewFragment;
import com.example.viewtest.fragment.ViewLifeFragment;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (savedInstanceState == null)//防止重叠-Activity意外销毁(旋转)-导致重新创建
            initFragment();
    }

    private void initFragment() {
        int type = getIntent().getIntExtra("type", 0);
        Fragment fragment;
        switch (type) {
            default:
            case MainActivity.PROGRESSBAR:
                fragment = new ProgressFragment();
                break;
            case MainActivity.SCROLLVIEW:
                fragment = new ScrollViewFragment();
                break;
            case MainActivity.LIFECYCLE:
                fragment = new ViewLifeFragment();
                break;
            case MainActivity.ROUNDIMAGE:
                fragment = new RoundImageFragment();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(ProgressFragment.class.getName());
        ft.add(R.id.container, fragment, ProgressFragment.class.getName());
        ft.commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
