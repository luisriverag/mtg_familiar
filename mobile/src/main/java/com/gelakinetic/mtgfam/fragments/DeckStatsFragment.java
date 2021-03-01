package com.gelakinetic.mtgfam.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.gelakinetic.mtgfam.R;
import com.gelakinetic.mtgfam.helpers.DeckStatsGenerator;
import com.gelakinetic.mtgfam.helpers.MtgCard;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeckStatsFragment extends FamiliarFragment {
    private DeckStatsGenerator mStatGenerator;
    private List<MtgCard> mDeckToStat;
    private PieChart mTypeChart;
    private PieChart mColorChart;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myFragmentView =
                inflater.inflate(R.layout.stat_frag, container, false);
        assert myFragmentView != null;
        this.mDeckToStat = mDeckToStat;
        mStatGenerator = new DeckStatsGenerator(mDeckToStat);
        mTypeChart = (PieChart) myFragmentView.findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<>();
        Map<String, Float> typeColorMap = mStatGenerator.getTypeStats();
        for (String type : typeColorMap.keySet()) {
            entries.add(new PieEntry(typeColorMap.get(type), type));
        }
        PieDataSet set = new PieDataSet(entries, "Card Types");
        PieData data = new PieData(set);
        mTypeChart.setData(data);
        mTypeChart.invalidate();
        return myFragmentView;
    }
}
