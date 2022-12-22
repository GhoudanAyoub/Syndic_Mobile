package com.SyndicG5.ui.ContainerHome.fragments.pitches;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.Adapters.PitchesAdapter;
import com.SyndicG5.R;
import com.SyndicG5.databinding.PichesFragmentBinding;
import com.SyndicG5.ui.ContainerHome.fragments.home.HomefragementViewModel;
import com.SyndicG5.ui.login.loginViewModel;
import com.syndicg5.networking.models.Pitches;
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
public class PitchesFragment extends Fragment {

    private PichesFragmentBinding binding;
    private PitchesViewModel mViewModel;
    private loginViewModel loginViewModel;
    private HomefragementViewModel homeViewModel;
    private RecyclerView recyclerView;
    private PitchesAdapter pitchesAdapter;
    private List<Pitches> pitchesList;
    @Inject
    apiRepository repository;

    public static PitchesFragment newInstance() {
        return new PitchesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        loginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        mViewModel = new ViewModelProvider(this).get(PitchesViewModel.class);
        homeViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        binding = PichesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActivityName("Pitches ");
        recyclerView = view.findViewById(R.id.pitche_recycler_view);
        pitchesAdapter = new PitchesAdapter(getContext(),repository);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerView.addItemDecoration(new GridAutoFitItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.alternative_horizontal_margin_page)));
        recyclerView.setAdapter(pitchesAdapter);
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
        mViewModel.getAllPitches();
        mViewModel.getListPitchesMutableLiveData().observe(getViewLifecycleOwner(), pitches -> {
            pitchesList=pitches;
            pitchesAdapter.setList((ArrayList<Pitches>) pitches);
            binding.progressBar2.setVisibility(View.GONE);
        });
    }

    private void filter(String query) {
        String lowerCaseQuery = query.toUpperCase(Locale.ROOT);
        List<Pitches> filteredModelList = query.isEmpty() ?
                pitchesList :
                pitchesList
                        .stream()
                        .filter(pitch -> pitch.getLocation().toUpperCase(Locale.ROOT).contains(lowerCaseQuery) || pitch.getName().toUpperCase(Locale.ROOT).contains(lowerCaseQuery) )
                        .collect(Collectors.toList());
        pitchesAdapter.setList((ArrayList<Pitches>) filteredModelList);
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
