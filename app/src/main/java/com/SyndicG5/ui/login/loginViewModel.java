package com.SyndicG5.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class loginViewModel extends ViewModel {

    private MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getBooleanMutableLiveData() {
        return booleanMutableLiveData;
    }

    public void Login(String email, String pass) {
        booleanMutableLiveData.setValue(true);
    }
}
