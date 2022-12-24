package com.SyndicG5.ui.ContainerHome.fragments.pitches;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.Adapters.PitchesAdapter;
import com.SyndicG5.R;
import com.SyndicG5.databinding.FragmentPitchDetailsBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.jakewharton.rxbinding3.view.RxView;
import com.syndicg5.networking.models.Pitches;
import com.syndicg5.networking.repository.apiRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import timber.log.Timber;

@RequiresApi(api = Build.VERSION_CODES.N)
@AndroidEntryPoint
public class PitchDetailsFragment extends Fragment implements PitchesAdapter.PitcherListener {

    private FragmentPitchDetailsBinding binding;
    private PitchesViewModel mViewModel;
    private RecyclerView recyclerView;
    private PitchesAdapter pitchesAdapter;
    private List<Pitches> pitchesList;
    private Pitches pitche;
    private ProgressDialog progressDialog;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject
    apiRepository repository;
    private Date selectedDeliveryDate;

    public PitchDetailsFragment(Pitches pitches) {
        pitche = pitches;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PitchesViewModel.class);
        binding = FragmentPitchDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActivityName("Pitches Details ");
        recyclerView = view.findViewById(R.id.similar_pitch_recycler);
        pitchesAdapter = new PitchesAdapter(getContext(), repository, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new GridAutoFitItemDecoration(1, getResources().getDimensionPixelSize(R.dimen.alternative_horizontal_margin_page)));
        recyclerView.setAdapter(pitchesAdapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Login ..........");

        binding.pitchName.setText(pitche.getName());
        binding.pitchPrice.setText(pitche.getPrice() + " Dh");
        binding.pitchCap.setText("Capacity :" + pitche.getCapacity());
        binding.pitchDesc.setText(pitche.getDescripton());
        Glide.with(getContext())
                .load(pitche.getPhoto())
                .placeholder(R.drawable.img_placeholder)
                .into(binding.pitcheImg);

        RxView.clicks(binding.reservation)
                .throttleFirst(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NotNull Unit unit) {
                        BookReservation();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(Objects.requireNonNull(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        subscribe(pitche);
    }

    private void BookReservation() {
        openDatePickerDialog();
    }

    private void openDatePickerDialog() {
        Calendar calendar = Calendar.getInstance(Locale.FRENCH);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(
                requireActivity(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    int realMonth = monthOfYear + 1;
                    selectedDeliveryDate = new Date(year, realMonth, dayOfMonth);
                }, currentYear, currentMonth, currentDay
        );

        pickerDialog.getDatePicker().setMinDate(currentDay);
        pickerDialog.getDatePicker().setMaxDate(getValableDates());
        pickerDialog.setOnDismissListener(dialogInterface -> {
            showMessageOKCancel();
        });
        pickerDialog.show();
    }


    private Long getValableDates() {
        //todo add valable date
        return 1L;
    }


    private void showMessageOKCancel() {
        new AlertDialog.Builder(getContext())
                .setMessage("Book Reservation")
                .setPositiveButton("OK", (dialogInterface, i) -> RunReservation())
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void RunReservation() {
        progressDialog.show();
        mViewModel.RunReservation(pitche, new Date(), selectedDeliveryDate);
        mViewModel.getReservationMutableLiveData().observe(getViewLifecycleOwner(), valeur -> {
            progressDialog.dismiss();
        });
    }

    private void subscribe(Pitches p) {
        mViewModel.getAllPitches();
        mViewModel.getListPitchesMutableLiveData().observe(getViewLifecycleOwner(), pitches -> {
            pitchesList = pitches;
            if (p != null) {
                List<Pitches> pitchesList1 = pitches.stream()
                        .filter(pitches1 ->
                                pitches1.getComplexe().getLocation()
                                        .contains(p.getComplexe().getLocation())
                                        && pitches1.getPitchId() != p.getPitchId()
                                        && CheckDistanceBetweenTwoLocations(
                                        new LatLng(pitches1.getComplexe().getLatitude()
                                                , pitches1.getComplexe().getLongitude()),
                                        new LatLng(p.getComplexe().getLatitude(),
                                                p.getComplexe().getLongitude()))
                        )
                        .collect(Collectors.toList());
                if (!pitchesList1.isEmpty()) {
                    binding.blocSimilars.setVisibility(View.VISIBLE);
                    pitchesAdapter.setList(
                            (ArrayList<Pitches>) pitchesList1);
                }
                else
                    binding.blocSimilars.setVisibility(View.GONE);
            }
        });
    }

    public Boolean CheckDistanceBetweenTwoLocations(LatLng location, LatLng location2) {
        double lat1 = Math.toRadians(location.latitude);
        double lon1 = Math.toRadians(location.longitude);
        double lat2 = Math.toRadians(location2.latitude);
        double lon2 = Math.toRadians(location2.longitude);

        // Calculate the distance between the two locations using the Haversine formula
        final int EARTH_RADIUS = 6371; // Earth's radius in kilometers
        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(Math.pow(Math.sin((lat2 - lat1) / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lon2 - lon1) / 2), 2)));

        return distance <= 10;
    }

    @Override
    public void onPitcherClicked(Pitches Pitches) {
        replace(new PitchDetailsFragment(Pitches));
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(PitchesFragment.class.getName());
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        progressDialog.dismiss();
    }
}
