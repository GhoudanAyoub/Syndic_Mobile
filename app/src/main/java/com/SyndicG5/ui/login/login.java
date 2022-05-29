package com.SyndicG5.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.SyndicG5.R;
import com.SyndicG5.databinding.ActivityLoginBinding;
import com.SyndicG5.ui.ContainerHome.HomeContainer;
import com.jakewharton.rxbinding3.view.RxView;
import com.syndicg5.networking.models.Login;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import timber.log.Timber;

@RequiresApi(api = Build.VERSION_CODES.N)
@AndroidEntryPoint
public class login extends AppCompatActivity {

    ActivityLoginBinding binding;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressDialog progressDialog;
    private loginViewModel mViewModel;
    private RadioButton radioButton, radioButton2;
    private String radioButtonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mViewModel = new ViewModelProvider(this).get(loginViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login ..........");
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        if (radioButton.isChecked())
            radioButtonText = "syndic";
        if (radioButton2.isChecked())
            radioButtonText = "resident";

        RxView.clicks(binding.login)
                .throttleFirst(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NotNull Unit unit) {
                        progressDialog.show();
                        binding.login.setEnabled(false);
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
        subscribe();
    }

    private void subscribe() {
        mViewModel.getBooleanMutableLiveData().observe(this, aBoolean -> {
            if (aBoolean) {
                mViewModel.getImmeubleInfo();
                mViewModel.saveLogin(new Login(1, true));
                mViewModel.getOneSyndic(Objects.requireNonNull(binding.gmailEditText.getEditText()).getText().toString());
                mViewModel.getSyndicMutableLiveData().observe(this, syndic -> {
                            if (syndic != null) {
                                syndic.setType(1);
                                mViewModel.saveUser(syndic.toUser());
                                binding.login.setEnabled(true);

                                startActivity(new Intent(getApplication(), HomeContainer.class));
                            }
                        }
                );
            } else {
                Toasty.error(getApplicationContext(), "Email or Password Not Correct", Toast.LENGTH_SHORT, true).show();
                progressDialog.dismiss();
                binding.login.setEnabled(true);
            }
        });
    }

    private void LoginUser(String email, String pass) {
        mViewModel.Login(email, pass);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        progressDialog.dismiss();
    }
}