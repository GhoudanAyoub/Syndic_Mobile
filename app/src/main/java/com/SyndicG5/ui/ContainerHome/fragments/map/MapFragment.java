package com.SyndicG5.ui.ContainerHome.fragments.map;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.SyndicG5.R;
import com.SyndicG5.databinding.FragmentEmptyBinding;
import com.SyndicG5.ui.ContainerHome.fragments.pitches.PitchDetailsFragment;
import com.SyndicG5.ui.ContainerHome.fragments.pitches.PitchesViewModel;
import com.SyndicG5.ui.util.BasicUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.syndicg5.networking.models.Pitches;

import java.util.HashMap;
import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@RequiresApi(api = Build.VERSION_CODES.N)
@AndroidEntryPoint
public class MapFragment extends Fragment implements OnMapReadyCallback {

    public static final int LOCATION_REQUEST_CODE = 100;
    private FragmentEmptyBinding binding;
    MapViewModel viewModel;
    PitchesViewModel pitchesViewModel;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    GoogleMap gMap;
    LatLng globalLatLng;
    BasicUtils utils = new BasicUtils();

    HashMap<String, Pitches> pitchesHashMap = new HashMap<String, Pitches>();
    private boolean isUp = false;

    public static MapFragment newInstance() {
        return new MapFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MapViewModel.class);
        pitchesViewModel = new ViewModelProvider(this).get(PitchesViewModel.class);
        binding = FragmentEmptyBinding.inflate(inflater, container, false);

        initComponents(binding.getRoot());
        attachListeners();

        getPreCurrentLocation();
        if (!utils.isNetworkAvailable(getActivity().getApplication())) {
            Toast.makeText(getActivity(), "No Network Available!", Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActivityName("Map ... ");

    }

    private void initComponents(View root) {
        supportMapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
    }

    private void attachListeners() {

        binding.getLocationBtn.setOnClickListener(view -> supportMapFragment.getMapAsync(googleMap -> {
            gMap = googleMap;
            gMap.clear();
            getPreCurrentLocation();
            attachMarkerOnMap(pitchesHashMap);
        }));

        binding.showFullMap.setOnClickListener(view -> changeGoogleMapScale());
        binding.nearByBtn.setOnClickListener(view -> {
            HashMap<String, Pitches> nearestPitches  = new HashMap<String, Pitches>();
            for (Map.Entry<String, Pitches> pitchAreaEntry : pitchesHashMap.entrySet()) {
                Map.Entry<String, Pitches> mapElement = pitchAreaEntry;
                Pitches pitch = (Pitches) mapElement.getValue();
                LatLng current = new LatLng(pitch.getComplexe().getLatitude(),
                        pitch.getComplexe().getLongitude());
                if (CheckDistanceBetweenTwoLocations(globalLatLng, current)) {
                    nearestPitches.put(pitchAreaEntry.getKey(),pitchAreaEntry.getValue());
                }
            }
            gMap.clear();
            attachMarkerOnMap(nearestPitches);
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(globalLatLng, 12));
        });

        binding.myBookingsBtn.setOnClickListener(view -> {
//            startActivity(new Intent(getActivity(), UserHistoryActivity.class));
//            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private void changeGoogleMapScale() {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        isUp = !isUp;
        if (isUp) {
            int pixels = (int) (600 * scale + 0.5f);
            binding.showFullMap.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_up));
            binding.mapContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    pixels));
        } else {
            int pixels = (int) (250 * scale + 0.5f);
            binding.showFullMap.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down));
            binding.mapContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    pixels));
        }
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

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void attachMarkerOnMap(HashMap<String, Pitches> hashMap) {
        supportMapFragment.getMapAsync(googleMap -> {
            gMap = googleMap;
            googleMap.addMarker(new MarkerOptions().position(globalLatLng).title("You are here"));
            for (Map.Entry<String, Pitches> stringParkingAreaEntry : hashMap.entrySet()) {
                Map.Entry<String, Pitches> mapElement = stringParkingAreaEntry;
                Pitches parking = (Pitches) mapElement.getValue();
                Timber.e("Add marker on map: " + parking.getName());
                LatLng latLngParking = new LatLng(parking.getComplexe().getLatitude(),
                        parking.getComplexe().getLongitude());
                MarkerOptions option = new MarkerOptions().position(latLngParking)
                        .title(mapElement.getKey().toString())
                        .snippet(parking.getName());
                gMap.addMarker(option);
            }
        });
    }

    private void getPreCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
    }

    private void getCurrentLocation(final Boolean zoom) {
        Timber.e("8855 getCurrentLocation");
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                supportMapFragment.getMapAsync(googleMap -> {
                    gMap = googleMap;
                    globalLatLng = new LatLng(location.getLatitude(),
                            location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(globalLatLng).title("You are here"));
                    if (zoom) {
                        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(globalLatLng, 18));

                        pitchesViewModel.getAllPitches();
                        pitchesViewModel.getListPitchesMutableLiveData()
                                .observe(getViewLifecycleOwner(), pitches -> {
                                            for (Pitches pitch : pitches) {
                                                pitchesHashMap.put(pitch.getName(), pitch);
                                            }
                                            attachMarkerOnMap(pitchesHashMap);
                                            Timber.d("GPS Map list %s", String.valueOf(pitchesHashMap));

                                        }
                                );
                    }
                });
            }
        });
        task.addOnFailureListener(e -> {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation(true);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        gMap.setOnInfoWindowClickListener(marker -> {
            LatLng position = marker.getPosition();
            Timber.d("Compare Location: " + globalLatLng.latitude + "," + globalLatLng.longitude + " | " + position.latitude + "," + position.longitude);
            if (position.equals(globalLatLng)) {
                return;
            }
            String[] items = {"Book Place"};
            androidx.appcompat.app.AlertDialog.Builder itemDilog = new AlertDialog.Builder(getActivity());
            itemDilog.setTitle("");
            itemDilog.setCancelable(true);
            itemDilog.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0: {
                            Timber.d("Book Reservation");
                            String UUID = marker.getTitle();
                            Pitches val = (Pitches) pitchesHashMap.get(UUID);
                            replace(new PitchDetailsFragment(val), "MapFragment");
                            Timber.d("Value of UUID: " + UUID);
                        }
                        break;
                    }

                }
            });
            itemDilog.show();

        });

    }

    private void replace(Fragment fragment, String s) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(s);
        transaction.commit();
    }

}
