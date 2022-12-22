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
        listPitchesBySyndicMutableLiveData.setValue(Arrays.asList(
                new Pitches(
                        1,
                        "Pitch 1",
                        10,
                        100,
                        "Location 1",
                        "https://www.google.com/url?sa=i&url=http%3A%2F%2Fstadiumdb.com%2F&psig=AOvVaw1QsSRozeSsoAVHF1-Q1nTl&ust=1671797598593000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCLjHq8aZjfwCFQAAAAAdAAAAABAI"
                ),
                new Pitches(
                        2,
                        "Pitch 2",
                        20,
                        300,
                        "Location 4",
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fpopulous.com%2Four-projects%2Fstadium-experience&psig=AOvVaw1QsSRozeSsoAVHF1-Q1nTl&ust=1671797598593000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCLjHq8aZjfwCFQAAAAAdAAAAABAW"
                ),
                new Pitches(
                        3,
                        "Pitch 3",
                        20,
                        150,
                        "Location 5",
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fsportsbrief.com%2Ffacts%2Ftop-listicles%2F27297-which-top-20-ranking-world-cup-stadiums-time%2F&psig=AOvVaw1QsSRozeSsoAVHF1-Q1nTl&ust=1671797598593000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCLjHq8aZjfwCFQAAAAAdAAAAABAd"
                )
                ));
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
