package com.SyndicG5.ui.ContainerHome.fragments.home;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.Adapters.AppartementAdapter;
import com.SyndicG5.R;
import com.SyndicG5.databinding.HomefragementFragmentBinding;
import com.SyndicG5.ui.ContainerHome.transaction.calculatorActivity;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.repository.apiRepository;
import com.syndicg5.networking.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
@RequiresApi(api = Build.VERSION_CODES.N)
public class homefragment extends Fragment {

    private HomefragementFragmentBinding binding;
    HomefragementViewModel homeViewModel;
    private AppartementAdapter appartementAdapter;
    private RecyclerView recyclerView;
    private boolean amountHidden = false;
    private Double outcome = 0.0;
    private Double income = 0.0;
    private Double solde = 0.0;
    private Immeuble currentImmeuble;
    private List<Appartement> appartementLists;
    @Inject
    apiRepository repository;

    @Singleton
    public static homefragment newInstance() {
        return new homefragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        binding = HomefragementFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActivityName("Home");
        recyclerView = view.findViewById(R.id.balance_recycler_view);
        appartementAdapter = new AppartementAdapter(getContext(),repository);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(appartementAdapter);
        binding.progressBar2.setVisibility(View.VISIBLE);

        binding.balanceView.outcomingBalanceTxt.setText("0.0 DH");
        binding.balanceView.incomingBalanceTxt.setText("0.0 DH");
        binding.balanceView.balanceTxt.setText("0.0 Dh");
        binding.addContactFabTitle.setText(R.string.label_fabTransaction);
        binding.addContactBtn.setOnClickListener(view1 -> OpenCalcutor());
        binding.addContactContainer.setOnClickListener(view1 -> OpenCalcutor());

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

        binding.endSearchBtn.setOnClickListener(view1 -> clearAndHideSearchView());
        binding.balanceView.showHideBalanceBtn.setOnClickListener(view1 -> {
            if (amountHidden) {
                getBalanceView(currentImmeuble.getId());
                binding.balanceView.showHideBalanceBtn.setImageResource(R.drawable.ic_eye_closed);
            } else {
                String stars = getResources().getString(R.string.label_asterisk_amount);
                binding.balanceView.incomingBalanceTxt.setText(stars);
                binding.balanceView.outcomingBalanceTxt.setText(stars);
                binding.balanceView.balanceTxt.setText(stars);
                binding.balanceView.showHideBalanceBtn.setImageResource(R.drawable.ic_eye_open);
            }
            amountHidden = !amountHidden;
        });
        binding.fragmentNestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > oldScrollY)
                binding.addContactFabTitle.setVisibility(View.GONE);
            else if (scrollX == scrollY)
                binding.addContactFabTitle.setVisibility(View.VISIBLE);
            else
                binding.addContactFabTitle.setVisibility(View.VISIBLE);
        });
    }
    private void filter(String query) {
        String lowerCaseQuery = query.toLowerCase();
        List<Appartement> filteredModelList = query.isEmpty() ?
                appartementLists :
                appartementLists
                        .stream()
                        .filter(appartement -> appartement.getNumero().toString().contains(lowerCaseQuery) || appartement.getEtage().toString().contains(lowerCaseQuery))
                .collect(Collectors.toList());
        appartementAdapter.setList((ArrayList<Appartement>) filteredModelList);
    }

    @Override
    public void onResume() {
        super.onResume();

        subscribe();
    }

    private void OpenCalcutor() {
        startActivity(new Intent(binding.getRoot().getContext(), calculatorActivity.class));
    }

    private void updateEditMode() {
        binding.editModeContainer.setVisibility(View.GONE);
        binding.clientActions.setVisibility(View.VISIBLE);
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


    private void subscribe() {
        homeViewModel.getImmeubleInfo().observe(getViewLifecycleOwner(), immeuble -> {
            currentImmeuble = immeuble;
            getBalanceView(immeuble.getId());
            homeViewModel.getAppartementByImmeuble(immeuble.getId());
            homeViewModel.getListAppartementByImmeubleMutableLiveData().observe(getViewLifecycleOwner(), appartementList -> {
                appartementLists = appartementList;
                binding.clientsNumberTxt.setText(appartementList.size()+"");

                binding.progressBar2.setVisibility(View.GONE);
                appartementAdapter.setList((ArrayList<Appartement>) appartementList);
            });
        });
        /*homeViewModel.getUserInfo();
        homeViewModel.getBalance();
        getBalanceView();
        homeViewModel.getBalanceList().observe(getViewLifecycleOwner(), balances -> {
            balanceAdapter.setList(balances);
        });*/
    }

    private void getBalanceView(int immeuble) {
        homeViewModel.getDepenseByImmeuble(immeuble);
        homeViewModel.getListDepenseByImmeubleMutableLiveData().observe(getViewLifecycleOwner(), depenses -> {
            if (depenses != null) {
                outcome =0.0;
                for (Depense depense  : depenses){
                    outcome += depense.getMontant();
                }
                binding.balanceView.outcomingBalanceTxt.setText(outcome + " DH");
            }
        });
        homeViewModel.getRevenusByImmeuble(immeuble);
        homeViewModel.getListRevenuByImmeubleMutableLiveData().observe(getViewLifecycleOwner(), revenus -> {
            if (revenus != null) {
                income = 0.0; solde = 0.0;
                for (Revenu revenu  : revenus){
                    income += revenu.getMontant();
                }
                binding.balanceView.incomingBalanceTxt.setText(income + " DH");
                solde = income - outcome;
                binding.balanceView.balanceTxt.setText(solde + " Dh");
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}