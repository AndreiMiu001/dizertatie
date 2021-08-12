package com.andrei.evot;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.andrei.evot.adapters.ViewingAdapter;
import com.andrei.evot.bw.PastElectionVotesBW;
import com.andrei.evot.callbacks.ViewPastElectionsCallback;
import com.andrei.evot.callbacks.ViewPastVoteCallback;
import com.andrei.evot.model.ElectionModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class PastElectionVotesList extends CommonBasicActivity {

    private RecyclerView recyclerView;
    private ViewingAdapter adapter;
    private ArrayList<ElectionModel> electionList;
    private final WeakReference<Context> context = new WeakReference<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_election_votes_list);
        recyclerView = (RecyclerView) findViewById(R.id.viewElectionRV);

        PastElectionVotesBW bg = new PastElectionVotesBW(context, new ViewPastElectionsCallback() {
            @Override
            public void onResult(ArrayList<ElectionModel> list) {
                electionList = list;
                adapter = new ViewingAdapter(electionList, new ViewPastVoteCallback() {
                    @Override
                    public void onResult(ElectionModel election) {
                        Intent showDetailActivity = new Intent(getBaseContext(), ViewPastVote.class);
                        showDetailActivity.putExtra("SelectedElection", election);
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