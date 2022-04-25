package com.SyndicG5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.SyndicG5.ui.login.login;

import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class SplashScreen extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    startActivity(new Intent(getApplication(), login.class));
                });
    }
}