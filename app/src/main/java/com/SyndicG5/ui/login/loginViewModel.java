package com.SyndicG5.ui.login;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.syndicg5.networking.models.Login;
import com.syndicg5.networking.models.User;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.repository.roomRepository;

public class loginViewModel extends ViewModel {

    private roomRepository roomRepo;
    private apiRepository apiRepository;

    private MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
    private LiveData<Login> loginLiveData = null;

    public LiveData<Login> getLoginLiveData() {
        return loginLiveData;
    }

    public MutableLiveData<Boolean> getBooleanMutableLiveData() {
        return booleanMutableLiveData;
    }

    @ViewModelInject
    public loginViewModel(roomRepository roomRepository, apiRepository apiRepository) {
        this.roomRepo = roomRepository;
        this.apiRepository = apiRepository;
    }

    public void Login(String email, String pass) {
        booleanMutableLiveData.setValue(true);
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
}
