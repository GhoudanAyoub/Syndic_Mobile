package com.SyndicG5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dagger.hilt.android.AndroidEntryPoint;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
    }
}