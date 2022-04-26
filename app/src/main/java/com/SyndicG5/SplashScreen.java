package com.SyndicG5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.lifecycle.ViewModelProvider;

import com.SyndicG5.ui.ContainerHome.HomeContainer;
import com.SyndicG5.ui.login.login;
import com.SyndicG5.ui.login.loginViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;


@SuppressLint("CheckResult")
public class SplashScreen extends SyndicActivity {

    private loginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        mViewModel.getLoginInfo();
        mViewModel.getLoginLiveData().observe(this, it -> {
            if (it != null) {
                if (it.isStatus())
                    goHome();
                else {
                    goLogin();
                }
            } else {
                goLogin();
            }
        });
    }


    private void goHome() {
        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    startActivity(new Intent(getApplication(), HomeContainer.class));
                });
    }

    private void goLogin() {
        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    startActivity(new Intent(getApplication(), login.class));
                });
    }
}