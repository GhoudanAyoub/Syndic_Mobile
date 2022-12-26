package com.SyndicG5.ui.ContainerHome.fragments.pitches;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.syndicg5.networking.models.Monument;
import com.syndicg5.networking.models.Pitches;
import com.syndicg5.networking.models.Reservation;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.models.User;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.repository.roomRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PitchesViewModel extends ViewModel {

    private apiRepository repository;
    private roomRepository roomRepo;

    private MutableLiveData<List<Pitches>> listPitchesBySyndicMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Monument>> listMonumentMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<List<Revenu>> listRevenuBySyndicMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Reservation>> listReservationMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> reservationMutableLiveData = new MutableLiveData<>();


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
    @SuppressLint("CheckResult")
    public void getMonuments() {
        repository.getMonuments()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listMonumentMutableLiveData.setValue(response), Throwable::printStackTrace);
    }

    public MutableLiveData<List<Monument>> getListMonumentMutableLiveData() {
        return listMonumentMutableLiveData;
    }

    public LiveData<List<Pitches>> getListPitchesMutableLiveData() {
        return  listPitchesBySyndicMutableLiveData;
    }

    public LiveData<List<Revenu>> getListPaymentBySyndicMutableLiveData() {
        return listRevenuBySyndicMutableLiveData;
    }

    public MutableLiveData<Boolean> getReservationMutableLiveData() {
        return reservationMutableLiveData;
    }

    public MutableLiveData<List<Reservation>> getListReservationMutableLiveData() {
        return listReservationMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void getPaymentByPitches(int id) {
        repository.getPaymentByResident(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listRevenuBySyndicMutableLiveData.setValue(response), Throwable::printStackTrace);

    }
    @SuppressLint("CheckResult")
    public void getReservationByUserId(int id) {
        repository.getReservationByUserId(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listReservationMutableLiveData.setValue(response), Throwable::printStackTrace);

    }

    @SuppressLint("CheckResult")
    public void RunReservation(Pitches pitche, Date reservationDate,
                                            Date matchDate) {
        User user = roomRepo.getUserInfo().getValue();
        repository.RunReservation(new Reservation(user,pitche,reservationDate,matchDate,pitche.getPrice()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                        if(response.code()==200)
                            reservationMutableLiveData.setValue(true);
                        else
                            reservationMutableLiveData.setValue(false);
                }, Throwable::printStackTrace);
    }
}
