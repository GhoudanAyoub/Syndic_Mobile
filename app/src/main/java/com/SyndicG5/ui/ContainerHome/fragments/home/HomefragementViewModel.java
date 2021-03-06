package com.SyndicG5.ui.ContainerHome.fragments.home;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.request.RevenusReq;
import com.syndicg5.networking.models.Categorie;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.repository.roomRepository;
import com.syndicg5.networking.request.depenseReq;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class HomefragementViewModel extends ViewModel {

    private apiRepository repository;
    private roomRepository roomRepo;
    private MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<List<Categorie>> listCategorieMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Appartement>> listAppartementMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Appartement>> listAppartementByImmeubleMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Appartement>> listAppartementByResidentMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Immeuble>> listImmeubleMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Revenu>> listRevenuByAppartementMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Revenu>> listRevenuByImmeubleMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Depense>> listDepenseByImmeubleMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Appartement> AppartementMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Immeuble> ImmeubleMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Appartement>> getListAppartementByResidentMutableLiveData() {
        return listAppartementByResidentMutableLiveData;
    }

    public MutableLiveData<List<Categorie>> getListCategorieMutableLiveData() {
        return listCategorieMutableLiveData;
    }

    public MutableLiveData<Boolean> getBooleanMutableLiveData() {
        return booleanMutableLiveData;
    }

    public MutableLiveData<List<Revenu>> getListRevenuByAppartementMutableLiveData() {
        return listRevenuByAppartementMutableLiveData;
    }

    public MutableLiveData<List<Revenu>> getListRevenuByImmeubleMutableLiveData() {
        return listRevenuByImmeubleMutableLiveData;
    }

    public MutableLiveData<List<Depense>> getListDepenseByImmeubleMutableLiveData() {
        return listDepenseByImmeubleMutableLiveData;
    }

    public MutableLiveData<List<Appartement>> getListAppartementByImmeubleMutableLiveData() {
        return listAppartementByImmeubleMutableLiveData;
    }

    public MutableLiveData<List<Appartement>> getListAppartementMutableLiveData() {
        return listAppartementMutableLiveData;
    }

    public MutableLiveData<List<Immeuble>> getListImmeubleMutableLiveData() {
        return listImmeubleMutableLiveData;
    }

    public MutableLiveData<Appartement> getAppartementMutableLiveData() {
        return AppartementMutableLiveData;
    }

    public MutableLiveData<Immeuble> getImmeubleMutableLiveData() {
        return ImmeubleMutableLiveData;
    }

    @ViewModelInject
    public HomefragementViewModel(apiRepository repository, roomRepository roomRepo) {
        this.repository = repository;
        this.roomRepo = roomRepo;
    }

    public void getAllAppartements() {
        repository.getAllAppartements()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listAppartementMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }

    public void getOneAppartement(int id) {
        repository.getOneAppartement(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> AppartementMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }

    public void getAppartementByResident(int id) {
        repository.getAppartementByResident(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listAppartementByResidentMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }

    public void getAppartementByImmeuble(int id) {
        repository.getAppartementByImmeuble(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listAppartementByImmeubleMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }

    public void getAllImmeuble(int id, String email, int type) {
        repository.getAllImmeuble(id,email,type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listImmeubleMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }

    public void getOneImmeuble(int id) {
        repository.getOneImmeuble(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> ImmeubleMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }
    public void getRevenusByAppartement(int id){
        repository.getRevenusByAppartement(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listRevenuByAppartementMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }
    public void getRevenusByImmeuble(int id){
        repository.getRevenusByImmeuble(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listRevenuByImmeubleMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }
    public void getDepenseByImmeuble(int id){
        repository.getDepenseByImmeuble(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listDepenseByImmeubleMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }

    public LiveData<Immeuble> getImmeubleInfo() { return  roomRepo.getImmeubleInfo(); }


    public void saveBalance(RevenusReq f, depenseReq d, boolean type) {
        repository.saveBalance(f,d,type)
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

    public void getAllCategories() {
        repository.getAllCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listCategorieMutableLiveData.setValue(response)
                        , Throwable::printStackTrace);
    }
}