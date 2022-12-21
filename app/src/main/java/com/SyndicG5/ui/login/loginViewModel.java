package com.SyndicG5.ui.login;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Login;
import com.syndicg5.networking.models.Resident;
import com.syndicg5.networking.models.Syndic;
import com.syndicg5.networking.models.User;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.repository.roomRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class loginViewModel extends ViewModel {

    private roomRepository roomRepo;
    private apiRepository apiRepository;

    private MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
    private LiveData<Login> loginLiveData = new MutableLiveData<>();
    private LiveData<Immeuble> immeubleLoginLiveData = new MutableLiveData<>();
    private LiveData<User> userLoginLiveData = new MutableLiveData<>();
    private MutableLiveData<Syndic> syndicMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Resident> residentMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Resident> getResidentMutableLiveData() {
        return residentMutableLiveData;
    }

    public LiveData<Immeuble> getImmeubleInfoLiveData() {
        return immeubleLoginLiveData;
    }

    public LiveData<Login> getLoginLiveData() {
        return loginLiveData;
    }

    public MutableLiveData<Boolean> getBooleanMutableLiveData() {
        return booleanMutableLiveData;
    }

    public LiveData<User> getUserLoginLiveData() {
        return userLoginLiveData;
    }

    public MutableLiveData<Syndic> getUserTypeMutableLiveData() {
        return syndicMutableLiveData;
    }

    @ViewModelInject
    public loginViewModel(roomRepository roomRepository, apiRepository apiRepository) {
        this.roomRepo = roomRepository;
        this.apiRepository = apiRepository;
    }

    @SuppressLint("CheckResult")
    public void Login(String email, String pass) {
        apiRepository.Login(new User(email,pass))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.isSuccessful()) {
                                if (response.code() == 200)
                                    booleanMutableLiveData.setValue(true);
                                else
                                    booleanMutableLiveData.setValue(false);
                            } else
                                booleanMutableLiveData.setValue(false);
                        }, Throwable::printStackTrace);
    }

    @SuppressLint("CheckResult")
    public void CreateAccount(String email, String pass,String name,int type) {
        apiRepository.Login(new User(email,pass))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.isSuccessful()) {
                                if (response.code() == 200)
                                    booleanMutableLiveData.setValue(true);
                                else
                                    booleanMutableLiveData.setValue(false);
                            } else
                                booleanMutableLiveData.setValue(false);
                        }, Throwable::printStackTrace);
    }

    @SuppressLint("CheckResult")
    public void getUserServerInfo(String email){
        apiRepository.getOneSyndic(email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    syndicMutableLiveData.setValue(response);
                }, Throwable::printStackTrace);
    }
    @SuppressLint("CheckResult")
    public void getOneResidents(String email){
        apiRepository.getOneResidents(email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    residentMutableLiveData.setValue(response);
                }, Throwable::printStackTrace);
    }

    public void saveLogin(Login f) {
        roomRepo.saveLogin(f);
    }

    public void UpdateLogin(Login f) {
        roomRepo.UpdateLogin(f);
    }

    public void getLoginInfo() {
        loginLiveData = roomRepo.getLoginInfo();
    }

    public void saveImmeuble(Immeuble f) {
        roomRepo.saveImmeuble(f);
    }

    public void saveUser(User f) {
        roomRepo.saveUser(f);
    }

    public void UpdateImmeuble(Immeuble f) {
        roomRepo.UpdateImmeuble(f);
    }

    public void getImmeubleInfo() {
        immeubleLoginLiveData = roomRepo.getImmeubleInfo();
    }

    public void getUserInfo() {
        userLoginLiveData = roomRepo.getUserInfo();
    }
}
