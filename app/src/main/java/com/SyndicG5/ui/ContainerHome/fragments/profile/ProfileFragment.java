package com.SyndicG5.ui.ContainerHome.fragments.profile;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Update;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.SyndicG5.databinding.ProfileFragmentBinding;
import com.SyndicG5.ui.login.loginViewModel;
import com.jakewharton.rxbinding3.view.RxView;
import com.syndicg5.networking.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import timber.log.Timber;

@RequiresApi(api = Build.VERSION_CODES.N)
@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private ProfileFragmentBinding binding;
    loginViewModel loginViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        binding = ProfileFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setActivityName("");
    }

    @Override
    public void onResume() {
        super.onResume();
        subscribe();
    }

    private void subscribe() {
        loginViewModel.getUserInfo();
        loginViewModel.getUserLoginLiveData().observe(getViewLifecycleOwner(),user -> {
            if(user!=null){
                binding.clientName.setText(user.getNom()+" "+user.getPrenom());
                binding.name.setText(user.getEmail());
                Objects.requireNonNull(binding.passEditText.getEditText()).setText(user.getPassword());
            }
        });
    }

}
