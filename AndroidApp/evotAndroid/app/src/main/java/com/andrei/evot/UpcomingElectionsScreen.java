package com.andrei.evot;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andrei.evot.adapters.UpcomingElectionsAdapter;
import com.andrei.evot.bw.UpcomingElectionsBW;
import com.andrei.evot.callbacks.ElectionListCallback;
import com.andrei.evot.callbacks.ElectionModelCallback;
import com.andrei.evot.model.ElectionModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class UpcomingElectionsScreen extends CommonBackActionActivity {

    private final WeakReference<Context> context = new WeakReference<>(this);
    private RecyclerView recyclerView;
    private UpcomingElectionsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_elections_screen);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Upcoming elections");
        }

        recyclerView = findViewById(R.id.upcomingElectionsRV);

        Drawable mDivider = ContextCompat.getDrawable(context.get(), R.drawable.divider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(mDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);

        UpcomingElectionsBW bg = new UpcomingElectionsBW(context, new ElectionListCallback() {
            @Override
            public void onResult(ArrayList<ElectionModel> electionList) {
                adapter = new UpcomingElectionsAdapter(electionList, new ElectionModelCallback() {
                    @Override
                    public void onResult(ElectionModel election) {
                        Intent showDetailActivity = new Intent(getBaseContext(), SelectedUpcomingElectionScreen.class);
                        showDetailActivity.putExtra("SelectedUpcomingElection", election);
                        startActivity(showDetailActivity);
                    }
                });
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context.get()));
            }
        });
        bg.execute();
    }
}