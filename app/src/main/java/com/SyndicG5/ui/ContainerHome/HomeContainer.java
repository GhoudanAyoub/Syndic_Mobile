package com.SyndicG5.ui.ContainerHome;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.SyndicG5.R;
import com.SyndicG5.SyndicActivity;
import com.SyndicG5.databinding.ActivityHomeContainerBinding;
import com.SyndicG5.ui.ContainerHome.fragments.emptyFragment;
import com.SyndicG5.ui.ContainerHome.fragments.home.homefragment;
import com.SyndicG5.ui.ContainerHome.fragments.teams.TeamsFragment;
import com.SyndicG5.ui.ContainerHome.fragments.profile.ProfileFragment;
import com.SyndicG5.ui.ContainerHome.fragments.pitches.PitchesFragment;
import com.SyndicG5.ui.login.login;
import com.SyndicG5.ui.login.loginViewModel;
import com.syndicg5.networking.models.Login;

import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

@RequiresApi(api = Build.VERSION_CODES.N)
public class HomeContainer extends SyndicActivity implements View.OnClickListener {

    private ActivityHomeContainerBinding binding;
    private static Toolbar toolbar;
    private LinearLayout ll_Home, ll_profile, ll_Teams,ll_Pitches, ll_Logout;
    private static boolean open = false;
    loginViewModel mViewModel;

     private static FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        mViewModel.getImmeubleInfo();
        mViewModel.getUserInfo();
        transaction =  getSupportFragmentManager().beginTransaction();
        replace(emptyFragment.newInstance());
        init();
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

    public static void openHome(){
        transaction.replace(R.id.frame, homefragment.newInstance());
        transaction.commit();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_Home:
                replace(new homefragment(), "Games");
                break;

            case R.id.ll_profile:
                replace(new ProfileFragment(), "Profile");
                break;


            case R.id.ll_Teams:
                replace(new TeamsFragment(), "Teams");
                break;

            case R.id.ll_Pitches:
                replace(new PitchesFragment(), "Pitches");
                break;
            case R.id.ll_Logout:
                mViewModel.UpdateLogin(new Login(1, false,1));
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

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
