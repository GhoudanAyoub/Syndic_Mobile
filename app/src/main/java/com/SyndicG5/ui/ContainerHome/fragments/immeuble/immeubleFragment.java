package com.SyndicG5.ui.ContainerHome.fragments.immeuble;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.Adapters.ImmeubleListAdapter;
import com.SyndicG5.R;
import com.SyndicG5.databinding.FragmentImmebleBinding;
import com.SyndicG5.ui.ContainerHome.fragments.home.HomefragementViewModel;
import com.SyndicG5.ui.login.loginViewModel;
import com.syndicg5.networking.models.Immeuble;

import javax.inject.Singleton;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
@RequiresApi(api = Build.VERSION_CODES.N)
public class immeubleFragment extends Fragment {
    private FragmentImmebleBinding binding;
    HomefragementViewModel homeViewModel;
    loginViewModel loginViewModel;
    private Immeuble chosenImmeuble;
    private RecyclerView recyclerView;
    private ImmeubleListAdapter immeubleListAdapter;


    @Singleton
    public static immeubleFragment newInstance() {
        return new immeubleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        binding = FragmentImmebleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActivityName("Immeubles ");
        recyclerView = view.findViewById(R.id.immeuble_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        immeubleListAdapter = new ImmeubleListAdapter();
        recyclerView.setAdapter(immeubleListAdapter);
        homeViewModel.getAllImmeuble();
        homeViewModel.getListImmeubleMutableLiveData().observe(getViewLifecycleOwner(), immeubles -> {
            if (!immeubles.isEmpty())
                immeubleListAdapter.setImmeublesList(immeubles);
        });
        immeubleListAdapter.onImmeubleClicked(immeuble -> {
            chosenImmeuble = immeuble;
            return true;
        });
        binding.selectStoreBtn.setOnClickListener(view1 -> loginViewModel.saveImmeuble(chosenImmeuble));
    }

}