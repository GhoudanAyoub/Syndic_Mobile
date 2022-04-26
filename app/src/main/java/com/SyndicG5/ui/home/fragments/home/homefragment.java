package com.SyndicG5.ui.home.fragments.home;

import static com.SyndicG5.ui.home.HomeContainer.setActivityName;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SyndicG5.R;
import com.SyndicG5.databinding.HomefragementFragmentBinding;
import com.SyndicG5.databinding.ProfileFragmentBinding;

public class homefragment extends Fragment {

    private HomefragementFragmentBinding binding;
    private HomefragementViewModel mViewModel;

    public static homefragment newInstance() {
        return new homefragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        binding = HomefragementFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setActivityName("Home");
    }

}