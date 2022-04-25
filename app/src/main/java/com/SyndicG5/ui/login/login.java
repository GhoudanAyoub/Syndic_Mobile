package com.SyndicG5.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.SyndicG5.R;
import com.SyndicG5.databinding.ActivityHomrContainerBinding;
import com.SyndicG5.databinding.ActivityLoginBinding;
import com.SyndicG5.ui.home.HomeContainer;
import com.jakewharton.rxbinding3.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import timber.log.Timber;

public class login extends AppCompatActivity {

    ActivityLoginBinding binding;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login ..........");

        RxView.clicks(binding.login)
                .throttleFirst(4, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NotNull Unit unit) {
                        progressDialog.show();
                        LoginUser(Objects.requireNonNull(binding.gmailEditText.getEditText()).getText().toString(), Objects.requireNonNull(binding.passEditText.getEditText()).getText().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(Objects.requireNonNull(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void LoginUser(String email, String pass) {
        startActivity(new Intent(getApplication(), HomeContainer.class));
    }
}