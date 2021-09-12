package com.andrei.evot;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.andrei.evot.adapters.ViewingAdapter;
import com.andrei.evot.bw.PastElectionVotesBW;
import com.andrei.evot.callbacks.ElectionListCallback;
import com.andrei.evot.callbacks.ElectionModelCallback;
import com.andrei.evot.model.ElectionModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class PastElectionVotesList extends CommonBackActionActivity {

    private RecyclerView recyclerView;
    private ViewingAdapter adapter;
    private ArrayList<ElectionModel> electionList;
    private final WeakReference<Context> context = new WeakReference<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUserState();
        setContentView(R.layout.activity_past_election_votes_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Voting history");
        }

        recyclerView = (RecyclerView) findViewById(R.id.viewElectionRV);
        Drawable mDivider = ContextCompat.getDrawable(this, R.drawable.divider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(mDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);

        PastElectionVotesBW bg = new PastElectionVotesBW(context, new ElectionListCallback() {
            @Override
            public void onResult(ArrayList<ElectionModel> list) {
                electionList = list;
                adapter = new ViewingAdapter(electionList, new ElectionModelCallback() {
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