package com.SyndicG5.ui.ContainerHome.fragments.profile;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;
import androidx.room.Update;

import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.User;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.repository.roomRepository;

public class ProfileViewModel extends ViewModel {
    private apiRepository repository;
    private roomRepository roomRepo;

    @ViewModelInject
    public ProfileViewModel(apiRepository repository, roomRepository roomRepo) {
        this.repository = repository;
        this.roomRepo = roomRepo;
    }

    public void UpdateUser(User f) {
        roomRepo.UpdateUser(f);
    }
}
