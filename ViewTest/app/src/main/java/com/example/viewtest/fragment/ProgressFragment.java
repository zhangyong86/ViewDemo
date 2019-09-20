package com.example.viewtest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewtest.R;
import com.example.viewtest.view.NumberProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressFragment extends Fragment {

    private NumberProgressBar progressBar;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progressbar, container, false);
        progressBar = view.findViewById(R.id.progressbar);
        initTimer();
        return view;
    }

    private void initTimer(){
        progressBar.setProgress(0);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {//子线程-不能更新View(invalidate)
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        float progress = progressBar.getProgress();
                        progressBar.setProgress(progress + 1);
                    }
                });
            }
        }, 1000, 100);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }
}
