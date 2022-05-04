package com.SyndicG5.ui.ContainerHome.fragments.profile;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;
import static com.syndicg5.networking.utils.Commun.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SyndicG5.databinding.ProfileFragmentBinding;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private ProfileFragmentBinding binding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = ProfileFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setActivityName("");
        binding.textView7.setText(profile.getNom()+" "+profile.getPrenom());
        binding.emailedittext.getEditText().setText(profile.getEmail());
        binding.phoneedittext.getEditText().setText(profile.getTelephone());
        binding.passedittext.getEditText().setText(profile.getMdp());
    }

}