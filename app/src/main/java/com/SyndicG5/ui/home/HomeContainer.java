package com.SyndicG5.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.SyndicG5.R;
import com.SyndicG5.databinding.ActivityHomrContainerBinding;
import com.SyndicG5.ui.home.fragments.home.homefragment;
import com.SyndicG5.ui.home.fragments.profile.ProfileFragment;
import com.SyndicG5.ui.home.fragments.stats.statsFragment;

import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class HomeContainer extends AppCompatActivity  implements View.OnClickListener{

    ActivityHomrContainerBinding binding;
    private static Toolbar toolbar;
    private LinearLayout ll_Home, ll_profile,ll_stats;
    private static boolean open = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomrContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        ll_stats = menuView.findViewById(R.id.ll_stats);


        ll_Home.setOnClickListener(this);
        ll_profile.setOnClickListener(this);
        ll_stats.setOnClickListener(this);


       replace(new homefragment());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_Home:
               replace(new homefragment(), "Home");
                break;

            case R.id.ll_profile:
                 replace(new ProfileFragment(), "Profile");
                break;


            case R.id.ll_stats:
                replace(new statsFragment(), "Stats");
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