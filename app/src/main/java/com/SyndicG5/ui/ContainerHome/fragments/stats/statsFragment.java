package com.SyndicG5.ui.ContainerHome.fragments.stats;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import static java.lang.Thread.sleep;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.SyndicG5.R;
import com.SyndicG5.databinding.StatsFragmentBinding;
import com.SyndicG5.ui.ContainerHome.fragments.home.HomefragementViewModel;
import com.SyndicG5.ui.login.loginViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Immeuble;
import com.syndicg5.networking.models.Revenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;
import timber.log.Timber;

@RequiresApi(api = Build.VERSION_CODES.N)
@AndroidEntryPoint
public class statsFragment extends Fragment {

    private StatsFragmentBinding binding;
    private BarChart chart;
    private Spinner spin;
    private List<String> type = new ArrayList<>();
    private HomefragementViewModel homeViewModel;
    private loginViewModel loginViewModel;
    List<Double> dep = new ArrayList<>();
    List<Double> rev = new ArrayList<>();
    private ArrayList<Immeuble> immeubleList;
    private int selectedID = 0;

    public static statsFragment newInstance() {
        return new statsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
      //  binding = StatsFragmentBinding.inflate(inflater, container, false);
        View v = inflater.inflate(R.layout.stats_fragment, container, false);
        chart = v.findViewById(R.id.chart);
        //spin = v.findViewById(R.id.spinner);
        initializeBarChart();
        getImmeubleList();
        subscribe();

     /*   spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Timber.d("8855 %s", position);
                Map<String, Integer> map = new HashMap<>();
                ArrayList<Integer> val = new ArrayList<>(
                        Arrays.asList(2000,3500,3659,55853));
                ArrayList<String> mois = new ArrayList<>(
                        Arrays.asList("JAN","FEV","MAR","AVR"));
                int i = 0;
                for(int m : val) {
                    map.put(mois.get(i), m*10);
                    i++;
                }
                createBarChart(map);
                selectedID = immeubleList.get(position).getId();
                getBalanceView(immeubleList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
        return v.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActivityName("Statistics");
    }

    private void getImmeubleList() {
        loginViewModel.getUserInfo();
        loginViewModel.getUserLoginLiveData().observe(getViewLifecycleOwner(), user -> {
                homeViewModel.getAllImmeuble(user.getId(), user.getEmail(), user.getType());
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    private void initializeBarChart() {
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(4);
        chart.getXAxis().setDrawGridLines(false);
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(true);
        chart.getXAxis().setDrawGridLines(false);
        chart.animateY(1500);
        chart.getLegend().setEnabled(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisLeft().setDrawLabels(true);
        chart.setTouchEnabled(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.getXAxis().setEnabled(true);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.invalidate();
    }

    private void createBarChart(Map<String, Integer> map) {
        ArrayList<BarEntry> values = new ArrayList<>();
        int i = 0;
        for(String key : map.keySet()) {
            values.add(new BarEntry(i, map.get(key)));
            ++i;
        }

        BarDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setDrawValues(true);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            chart.setData(data);
            chart.setVisibleXRange(1,4);
            chart.setFitBars(true);
            XAxis xAxis = chart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(map.keySet()));
            for (IDataSet set : chart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());
            chart.invalidate();
        }
    }
//
//    private void initializeBarChart() {
//        chart.getDescription().setEnabled(false);
//        chart.setMaxVisibleValueCount(4);
//        chart.getXAxis().setDrawGridLines(false);
//        chart.setPinchZoom(false);
//        chart.setDrawBarShadow(false);
//        chart.setDrawGridBackground(false);
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setDrawGridLines(false);
//        chart.getAxisLeft().setDrawGridLines(false);
//        chart.getAxisRight().setDrawGridLines(false);
//        chart.getAxisRight().setEnabled(false);
//        chart.getAxisLeft().setEnabled(true);
//        chart.getXAxis().setDrawGridLines(false);
//        chart.animateY(1500);
//        chart.getLegend().setEnabled(false);
//        chart.getAxisRight().setDrawLabels(false);
//        chart.getAxisLeft().setDrawLabels(true);
//        chart.setTouchEnabled(false);
//        chart.setDoubleTapToZoomEnabled(false);
//        chart.getXAxis().setEnabled(true);
//        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart.invalidate();
//        subscribe();
//    }
//
//    private void createBarChart(List<Double> byMarqueList) {
//        ArrayList<BarEntry> values = new ArrayList<>();
//
//        for (int i = 0; i < byMarqueList.size(); i++) {
//            Double dataObject = byMarqueList.get(i);
//            values.add(new BarEntry(i, Float.parseFloat(dataObject.toString())));
//        }
//        BarDataSet set1;
//        if (chart.getData() != null &&
//                chart.getData().getDataSetCount() > 0) {
//            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
//            set1.setValues(values);
//            chart.getData().notifyDataChanged();
//            chart.notifyDataSetChanged();
//        } else {
//            set1 = new BarDataSet(values, "Data Set");
//            set1.setDrawValues(true);
//            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//            dataSets.add(set1);
//            BarData data = new BarData(dataSets);
//            chart.setData(data);
//            chart.setVisibleXRange(1, 4);
//            chart.setFitBars(true);
//            XAxis xAxis = chart.getXAxis();
//            xAxis.setGranularity(1f);
//            xAxis.setGranularityEnabled(true);
//            xAxis.setValueFormatter(new IndexAxisValueFormatter(type));
//            for (IDataSet set : chart.getData().getDataSets())
//                set.setDrawValues(!set.isDrawValuesEnabled());
//            chart.invalidate();
//        }
//    }

    private void subscribe() {
        homeViewModel.getImmeubleInfo().observe(getViewLifecycleOwner(), immeuble -> {
           /* Map<String, Integer> map = new HashMap<>();
            ArrayList<Integer> val;
            if(immeuble.getId()==1) {
                val = new ArrayList<>(
                        Arrays.asList(2000, 3500, 3659, 55853));

                ArrayList<String> mois = new ArrayList<>(
                        Arrays.asList("JAN","FEV","MAR","AVR"));
                int i = 0;
                for(int m : val) {
                    map.put(mois.get(i), m*10);
                    i++;
                }
                createBarChart(map);
            }else {
                val = new ArrayList<>(
                        Arrays.asList(100, 2500, 1000));
                ArrayList<String> mois = new ArrayList<>(
                        Arrays.asList("JAN","FEV","MAR","AVR"));
                int i = 0;
                for(int m : val) {
                    map.put(mois.get(i), m*10);
                    i++;
                }
                createBarChart(map);
            }*/
                getBalanceView(immeuble.getId());
                getCharts();
        });
        homeViewModel.getListImmeubleMutableLiveData().observe(getViewLifecycleOwner(), immeubles -> {
            if (!immeubles.isEmpty()) {
                immeubleList = (ArrayList<Immeuble>) immeubles;
                List<String> names = immeubles.stream().map(Immeuble::getNom).collect(Collectors.toList());
                ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, names);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               // spin.setAdapter(aa);
            }
        });
    }

    private void getCharts() {
        homeViewModel.getListDepenseByImmeubleMutableLiveData().observe(getViewLifecycleOwner(), depenses -> {
            if (depenses != null) {
                double outcome = 0.0;
                type.clear();
                for (Depense depense : depenses) {
                    outcome += depense.getMontant();
                    dep.add(depense.getMontant());
                    type.add(depense.getDate().getYear() + "");
                }
            }
        });
        homeViewModel.getListRevenuByImmeubleMutableLiveData().observe(getViewLifecycleOwner(), revenus -> {
            if (revenus != null) {
                double income = 0.0;
                type.clear();
                for (Revenu revenu : revenus) {
                    income += revenu.getMontant();
                    rev.add(revenu.getMontant());
                    type.add(revenu.getDate().getMonth() + "");
                }
                Map<String, Integer> map = new HashMap<>();
                int i = 0;
                for(Double m : rev) {
                    map.put(type.get(i), (int) (m*10));
                    i++;
                }
                createBarChart(map);
                //createBarChart(rev);
            }
        });
    }

    private void getBalanceView(int immeuble) {
        homeViewModel.getDepenseByImmeuble(immeuble);
        homeViewModel.getRevenusByImmeuble(immeuble);
    }

}