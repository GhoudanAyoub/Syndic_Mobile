package com.SyndicG5.ui.ContainerHome.fragments.stats;

import static com.SyndicG5.ui.ContainerHome.HomeContainer.setActivityName;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SyndicG5.R;
import com.SyndicG5.databinding.StatsFragmentBinding;
import com.SyndicG5.ui.ContainerHome.fragments.home.HomefragementViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Revenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class statsFragment extends Fragment {

    private StatsFragmentBinding binding;
    private BarChart chart;
    private List<String> type = new ArrayList<>();
    private HomefragementViewModel homeViewModel;
    List<Double> dep = new ArrayList<>();
    List<Double> rev = new ArrayList<>();

    public static statsFragment newInstance() {
        return new statsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        binding = StatsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setActivityName("Statistics");
        chart = binding.chart1;
        initializeBarChart();
        subscribe();
    }

    @Override
    public void onResume() {
        super.onResume();

        subscribe();
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
        subscribe();
    }

    private void createBarChart(List<Double> byMarqueList) {
        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < byMarqueList.size(); i++) {
            Double dataObject = byMarqueList.get(i);
            values.add(new BarEntry(i, Float.parseFloat(dataObject.toString())));
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
            xAxis.setValueFormatter(new IndexAxisValueFormatter(type));
            for (IDataSet set : chart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());
            chart.invalidate();
        }
    }

    private void subscribe() {
        homeViewModel.getImmeubleInfo().observe(getViewLifecycleOwner(), immeuble -> {
            getBalanceView(immeuble.getId());
        });
        homeViewModel.getListDepenseByImmeubleMutableLiveData().observe(getViewLifecycleOwner(), depenses -> {
            if (depenses != null) {
                double outcome =0.0;
                for (Depense depense  : depenses){
                    outcome += depense.getMontant();
                    dep.add(depense.getMontant());
                    type.add(depense.getDate().getYear()+"");
                }
            }
        });
        homeViewModel.getListRevenuByImmeubleMutableLiveData().observe(getViewLifecycleOwner(), revenus -> {
            if (revenus != null) {
                double  income = 0.0;
                for (Revenu revenu  : revenus){
                    income += revenu.getMontant();
                    rev.add(revenu.getMontant());
                }
                createBarChart(rev);
            }
        });
    }

    private void getBalanceView(int immeuble) {
        homeViewModel.getDepenseByImmeuble(immeuble);
        homeViewModel.getRevenusByImmeuble(immeuble);
    }
}