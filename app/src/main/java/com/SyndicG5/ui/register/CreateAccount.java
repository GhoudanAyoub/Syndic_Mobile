package com.SyndicG5.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.ViewModelProvider;

import com.SyndicG5.R;
import com.SyndicG5.databinding.ActivityCreateAccountBinding;
import com.SyndicG5.ui.ContainerHome.HomeContainer;
import com.SyndicG5.ui.login.loginViewModel;
import com.jakewharton.rxbinding3.view.RxView;
import com.syndicg5.networking.models.Login;
import com.syndicg5.networking.models.User;

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
public class CreateAccount extends AppCompatActivity {


    ActivityCreateAccountBinding binding;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressDialog progressDialog;
    private loginViewModel mViewModel;
    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating your Account ..........");
        binding.loginTxt.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Login.class)));


        Objects.requireNonNull(binding.FullNameEditText.getEditText())
                .addTextChangedListener(new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                        if (binding.gmailEditText.getEditText().getText().toString()==null ||
                                s.length()==0)
                            binding.createAccount.setEnabled(false);
                        else
                            binding.createAccount.setEnabled(true);

                    }
                });
        Objects.requireNonNull(binding.FullNameEditText.getEditText())
                .addTextChangedListener(new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                        if (!binding.confirmPassEditText.getEditText().getText().toString()
                                .contains(binding.passEditText.getEditText().getText().toString())) {
                            binding.confirmPassEditText.setBackground(AppCompatResources
                                    .getDrawable(getApplicationContext(), R.drawable.buttoncolorerror));
                            binding.createAccount.setEnabled(false);
                        }
                        else {
                            binding.confirmPassEditText.setBackground(AppCompatResources
                                    .getDrawable(getApplicationContext(), R.drawable.bg_textinputlayout));
                            binding.createAccount.setEnabled(true);
                        }

                    }
                });
        RxView.clicks(binding.createAccount)
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
                        binding.createAccount.setEnabled(false);
                        createAccount();
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
                mViewModel.getUserServerInfo(Objects.requireNonNull(binding.gmailEditText.getEditText()).getText().toString());
                mViewModel.getUserTypeMutableLiveData().observe(this, user -> {
                    if (user != null) {
                        user.setType(1);
                        mViewModel.saveLogin(new Login(1, true, type));
                        mViewModel.saveUser(user);
                        binding.createAccount.setEnabled(true);
                        startActivity(new Intent(getApplication(), HomeContainer.class));
                    }
                });

            } else {
                Toasty.error(getApplicationContext(), "Email or Password Not Correct", Toast.LENGTH_SHORT, true).show();
                progressDialog.dismiss();
                binding.createAccount.setEnabled(true);
            }
        });
    }

    private void createAccount() {
        mViewModel.CreateAccount(
                new User(binding.gmailEditText.getEditText().getText().toString(),
                        binding.passEditText.getEditText().getText().toString(),
                        binding.FullNameEditText.getEditText().getText().toString(),
                        type));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        progressDialog.dismiss();
    }
}
