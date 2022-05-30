package com.SyndicG5.ui.ContainerHome.fragments.payment;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SyndicG5.Adapters.RevenuAdapter;
import com.SyndicG5.R;
import com.SyndicG5.databinding.FragmentPaymentBinding;
import com.SyndicG5.ui.ContainerHome.fragments.home.HomefragementViewModel;
import com.SyndicG5.ui.ContainerHome.fragments.residents.ResidentViewModel;
import com.SyndicG5.ui.login.loginViewModel;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@RequiresApi(api = Build.VERSION_CODES.N)
@AndroidEntryPoint
public class PaymentFragment extends Fragment {


    private FragmentPaymentBinding binding;
    private ResidentViewModel mViewModel;
    private loginViewModel loginViewModel;
    private HomefragementViewModel homeViewModel;
    private RecyclerView recyclerView;
    private RevenuAdapter revenuAdapter;
    private List<Revenu> revuenssList;
    @Inject
    apiRepository repository;

    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        loginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        mViewModel = new ViewModelProvider(this).get(ResidentViewModel.class);
        homeViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActivityName("Payment");
        recyclerView = view.findViewById(R.id.resident_recycler_view);
        revenuAdapter = new RevenuAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(revenuAdapter);
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
                mViewModel.getPaymentByResident(user.getId());
            }
        });
        mViewModel.getListPaymentBySyndicMutableLiveData().observe(getViewLifecycleOwner(), revuenss -> {
            revuenssList=revuenss;
            revenuAdapter.setList((ArrayList<Revenu>) revuenss);
            binding.progressBar2.setVisibility(View.GONE);
        });
    }

    private void filter(String query) {
        String lowerCaseQuery = query.toUpperCase(Locale.ROOT);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        List<Revenu> filteredModelList = query.isEmpty() ?
                revuenssList :
                revuenssList
                        .stream()
                        .filter(revenu -> dateFormat.format(revenu.getDate().getTime()).equals(lowerCaseQuery) || revenu.getAppartement().getNumero().equals(lowerCaseQuery) ||revenu.getMontant().equals(lowerCaseQuery) || revenu.getDescription().toUpperCase(Locale.ROOT).contains(lowerCaseQuery) || revenu.getImmeuble().getNom().toUpperCase(Locale.ROOT).contains(lowerCaseQuery) || revenu.getImmeuble().getVille().contains(lowerCaseQuery))
                        .collect(Collectors.toList());
        revenuAdapter.setList((ArrayList<Revenu>) filteredModelList);
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