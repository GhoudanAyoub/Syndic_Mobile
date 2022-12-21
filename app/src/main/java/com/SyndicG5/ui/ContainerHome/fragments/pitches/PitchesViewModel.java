package com.SyndicG5.ui.ContainerHome.fragments.pitches;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.syndicg5.networking.models.Resident;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.repository.roomRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PitchesViewModel extends ViewModel {

    private apiRepository repository;
    private roomRepository roomRepo;

    private MutableLiveData<List<Resident>> listResidentBySyndicMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<List<Revenu>> listRevenuBySyndicMutableLiveData = new MutableLiveData<>();

    @ViewModelInject
    public PitchesViewModel(apiRepository repository, roomRepository roomRepo) {
        this.repository = repository;
        this.roomRepo = roomRepo;
    }

    @SuppressLint("CheckResult")
    public void getResidentBySyndic(int id) {
        repository.getResidentBySyndic(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listResidentBySyndicMutableLiveData.setValue(response), Throwable::printStackTrace);
    }

    public LiveData<List<Resident>> getListResidentBySyndicMutableLiveData() {
        return  listResidentBySyndicMutableLiveData;
    }

    public LiveData<List<Revenu>> getListPaymentBySyndicMutableLiveData() {
        return listRevenuBySyndicMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void getPaymentByResident(int id) {
        repository.getPaymentByResident(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listRevenuBySyndicMutableLiveData.setValue(response), Throwable::printStackTrace);

    }

}
