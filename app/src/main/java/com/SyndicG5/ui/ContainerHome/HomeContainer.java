package com.SyndicG5.ui.ContainerHome;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.SyndicG5.R;
import com.SyndicG5.SyndicActivity;
import com.SyndicG5.databinding.ActivityHomeContainerBinding;
import com.SyndicG5.ui.ContainerHome.fragments.home.homefragment;
import com.SyndicG5.ui.ContainerHome.fragments.map.MapFragment;
import com.SyndicG5.ui.ContainerHome.fragments.pitches.PitchesFragment;
import com.SyndicG5.ui.ContainerHome.fragments.profile.ProfileFragment;
import com.SyndicG5.ui.login.login;
import com.SyndicG5.ui.login.loginViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.syndicg5.networking.models.Login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;
import timber.log.Timber;

@RequiresApi(api = Build.VERSION_CODES.N)
public class HomeContainer extends SyndicActivity implements View.OnClickListener {
    private static int PERMISSION_REQUEST_CODE = 1000;

    private ActivityHomeContainerBinding binding;
    private static Toolbar toolbar;
    private LinearLayout ll_Home, ll_profile, ll_Teams, ll_Pitches, ll_Logout;
    private static boolean open = false;
    loginViewModel mViewModel;
    FusedLocationProviderClient fusedLocationProviderClient;

    private static Double currentLatitude= 0.0;
    private static Double currentLongitude= 0.0;
    private static FragmentTransaction transaction;
    private static boolean isGPSEnabled = false;
    private static boolean isPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        mViewModel.getImmeubleInfo();
        mViewModel.getUserInfo();
        transaction = getSupportFragmentManager().beginTransaction();
        isGpsEnabled();
        isPermissionGranted = isPermissionGiven();
        grantLocationPermission();
        replace(MapFragment.newInstance());
        init();
    }

    private void grantLocationPermission() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if (!isGPSEnabled) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                        isGPSEnabled = true;
                        isPermissionGranted = true;
                        Timber.d("permission granted");
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(
                                HomeContainer.this,
                                "Please enable location permission",
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    private boolean isPermissionGiven() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean isGpsEnabled() {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Timber.d("GPS already enabled");
        } catch (Exception ex) {
            Timber.e(ex);
        }
        return isGPSEnabled;
    }

    public static void setActivityName(String name) {
        toolbar.setTitle(name);
    }

    private void init() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, binding.drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        binding.drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        View contentView = binding.drawer.getContentView();
        View menuView = binding.drawer.getMenuView();

        ll_Home = menuView.findViewById(R.id.ll_Home);
        ll_profile = menuView.findViewById(R.id.ll_profile);
        ll_Teams = menuView.findViewById(R.id.ll_Teams);
        ll_Pitches = menuView.findViewById(R.id.ll_Pitches);
        ll_Logout = menuView.findViewById(R.id.ll_Logout);


        ll_Home.setOnClickListener(this);
        ll_profile.setOnClickListener(this);
        ll_Teams.setOnClickListener(this);
        ll_Pitches.setOnClickListener(this);
        ll_Logout.setOnClickListener(this);
        mViewModel.getLoginInfo();
//        mViewModel.getLoginLiveData().observe(this,login -> {
//            if (login.getType()==1){
//                mViewModel.getImmeubleInfoLiveData().observe(this,immeuble -> {
//                    if(immeuble==null)
//                        replace(immeubleFragment.newInstance());
//                    else
//                        replace(homefragment.newInstance());
//                });
//            }else{
//                ll_Home.setVisibility(View.GONE);
//                replace(PaymentFragment.newInstance());
//            }
//        });
    }

    public static void openHome() {
        transaction.replace(R.id.frame, homefragment.newInstance());
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_Home:
//                replace(new homefragment(), "Games");
                break;

            case R.id.ll_profile:
                replace(new ProfileFragment(), "Profile");
                break;


            case R.id.ll_Teams:
//                replace(new TeamsFragment(), "Teams");
                break;

            case R.id.ll_Pitches:
                replace(new PitchesFragment(), "Pitches");
                break;
            case R.id.ll_Logout:
                mViewModel.UpdateLogin(new Login(1, false, 1));
                startActivity(new Intent(getApplicationContext(), login.class));
                break;
        }
        binding.drawer.closeDrawer();
    }

    private void replace(Fragment fragment, String s) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(s);
        transaction.commit();
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    public static LatLng getCurrentLocation(FusedLocationProviderClient fusedLocationProviderClient, Context context) {
        Timber.d("getting the current location");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        try {
            if (isPermissionGranted && isGPSEnabled) {
                Task<Location> result = fusedLocationProviderClient.getLastLocation();
                result.addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        Location currentLocation = task.getResult();
                        LatLng newLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                        Timber.d("new coordinates %f %f", newLatLng.latitude, newLatLng.longitude);
                        currentLatitude = currentLocation.getLatitude();
                        currentLongitude = currentLocation.getLongitude();
                    } else {
                        Toast.makeText(
                                context,
                                "Unable to get current location",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
            } else {
                //inflate custom view for the alertDialog
                Toast.makeText(
                        context,
                        "Please enable location permission",
                        Toast.LENGTH_LONG
                ).show();
            }
        } catch (SecurityException e) {
            Timber.e(e);
        }
        return new LatLng(currentLatitude, currentLongitude);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<String> requiredPermissions = new ArrayList<>(Arrays.asList(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requiredPermissions.add(Manifest.permission.ACTIVITY_RECOGNITION);
            requiredPermissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        } else {
            requiredPermissions.add(Manifest.permission.READ_PHONE_STATE);
        }

        List<String> missingPermissions = new ArrayList<>();
        for (String permission : requiredPermissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }

        if (!missingPermissions.isEmpty()) {
            String[] permissionsArray = missingPermissions.toArray(new String[0]);
            ActivityCompat.requestPermissions(this, permissionsArray, PERMISSION_REQUEST_CODE);
        }

    }
}
