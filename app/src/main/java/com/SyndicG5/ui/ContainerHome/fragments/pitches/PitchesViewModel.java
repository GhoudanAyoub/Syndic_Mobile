package com.SyndicG5.ui.ContainerHome.fragments.pitches;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.syndicg5.networking.models.Pitches;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.models.User;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.repository.roomRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PitchesViewModel extends ViewModel {

    private apiRepository repository;
    private roomRepository roomRepo;

    private MutableLiveData<List<Pitches>> listPitchesBySyndicMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<List<Revenu>> listRevenuBySyndicMutableLiveData = new MutableLiveData<>();


    @ViewModelInject
    public PitchesViewModel(apiRepository repository, roomRepository roomRepo) {
        this.repository = repository;
        this.roomRepo = roomRepo;
    }

    @SuppressLint("CheckResult")
    public void getAllPitches() {
        repository.getAllPitches()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listPitchesBySyndicMutableLiveData.setValue(response), Throwable::printStackTrace);
    }

    public LiveData<List<Pitches>> getListPitchesMutableLiveData() {
        return  listPitchesBySyndicMutableLiveData;
    }

    public LiveData<List<Revenu>> getListPaymentBySyndicMutableLiveData() {
        return listRevenuBySyndicMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void getPaymentByPitches(int id) {
        repository.getPaymentByResident(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listRevenuBySyndicMutableLiveData.setValue(response), Throwable::printStackTrace);

    }

}
