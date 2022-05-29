package com.SyndicG5.ui.ContainerHome.fragments.residents;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.Adapters.ResidentAdapter;
import com.SyndicG5.R;
import com.SyndicG5.databinding.ResidentFragmentBinding;
import com.SyndicG5.ui.ContainerHome.fragments.home.HomefragementViewModel;
import com.SyndicG5.ui.login.loginViewModel;
import com.syndicg5.networking.models.Resident;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@RequiresApi(api = Build.VERSION_CODES.N)
@AndroidEntryPoint
public class residentFragment extends Fragment {

    private ResidentFragmentBinding binding;
    private ResidentViewModel mViewModel;
    private loginViewModel loginViewModel;
    private HomefragementViewModel homeViewModel;
    private RecyclerView recyclerView;
    private ResidentAdapter residentAdapter;
    private List<Resident> residentList;
    @Inject
    apiRepository repository;

    public static residentFragment newInstance() {
        return new residentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        loginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        mViewModel = new ViewModelProvider(this).get(ResidentViewModel.class);
        homeViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        binding = ResidentFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActivityName("Residents ");
        recyclerView = view.findViewById(R.id.resident_recycler_view);
        residentAdapter = new ResidentAdapter(getContext(),repository);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(residentAdapter);
        binding.progressBar2.setVisibility(View.VISIBLE);

        binding.searchBtn.setOnClickListener(view1 -> {

            binding.clientsDetails.setVisibility(View.VISIBLE);
            binding.searchBtn.setVisibility(View.GONE);
            binding.clientsSearchView.setVisibility(View.VISIBLE);
            binding.endSearchBtn.setVisibility(View.VISIBLE);
            binding.clientsHeaderBar.setBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
            );
            binding.clientsSearchView.setIconified(true);
            binding.clientsSearchView.requestFocus();
            AppUtils.showKeyboard(requireActivity());
        });

        binding.clientsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                clearAndHideSearchView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        subscribe();
    }

    private void subscribe() {
        loginViewModel.getUserInfo();
        loginViewModel.getUserLoginLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                mViewModel.getResidentBySyndic(user.getId());
                mViewModel.getListResidentBySyndicMutableLiveData().observe(getViewLifecycleOwner(), residents -> {
                    residentList=residents;
                    residentAdapter.setList((ArrayList<Resident>) residents);
                    binding.progressBar2.setVisibility(View.GONE);
                });
            }
        });
    }

    private void filter(String query) {
        String lowerCaseQuery = query.toUpperCase(Locale.ROOT);
        List<Resident> filteredModelList = query.isEmpty() ?
                residentList :
                residentList
                        .stream()
                        .filter(resident -> resident.getPrenom().toUpperCase(Locale.ROOT).contains(lowerCaseQuery) || resident.getNom().toUpperCase(Locale.ROOT).contains(lowerCaseQuery) || resident.getEmail().toUpperCase(Locale.ROOT).contains(lowerCaseQuery) || resident.getTelephone().contains(lowerCaseQuery) || resident.getVille().toUpperCase(Locale.ROOT).contains(lowerCaseQuery))
                        .collect(Collectors.toList());
        residentAdapter.setList((ArrayList<Resident>) filteredModelList);
    }

    private void clearAndHideSearchView() {
        binding.clientsDetails.setVisibility(View.VISIBLE);
        binding.searchBtn.setVisibility(View.VISIBLE);
        binding.clientsSearchView.setVisibility(View.GONE);
        binding.endSearchBtn.setVisibility(View.GONE);
        binding.clientsSearchView.setIconified(false);
        binding.clientsHeaderBar.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.white)
        );
        binding.clientsSearchView.clearFocus();
        AppUtils.hideKeyboard(requireActivity());
    }
}