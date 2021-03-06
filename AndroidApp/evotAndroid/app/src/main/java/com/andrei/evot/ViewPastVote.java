package com.andrei.evot;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.andrei.evot.adapters.ViewPastCandidatesAdapter;
import com.andrei.evot.bw.ReadCandidatesBW;
import com.andrei.evot.callbacks.CandidatesCallback;
import com.andrei.evot.model.CandidateModel;
import com.andrei.evot.model.ElectionModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ViewPastVote extends CommonBackActionActivity {

    private RecyclerView recyclerView;
    private ViewPastCandidatesAdapter adapter;
    private final WeakReference<Context> context = new WeakReference<>(this);
    private CandidateModel votedCandidate;
    private CandidateModel winnerCandidate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUserState();
        setContentView(R.layout.activity_view_past_vote);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Voting history");
        }

        ElectionModel election = (ElectionModel) getIntent().getSerializableExtra("SelectedElection");

        TextView votedCNameTV = findViewById(R.id.yourVoteCandNameTV);
        TextView votedCDescTV = findViewById(R.id.yourVoteCandDescTV);
        TextView winnerCNameTV = findViewById(R.id.electionPastWinnerNameTV);
        TextView winnerCDescTV = findViewById(R.id.electionPastWinnerDescTV);

        recyclerView = findViewById(R.id.otherCandidatesRV);
        Drawable mDivider = ContextCompat.getDrawable(context.get(), R.drawable.divider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(mDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ReadCandidatesBW bg = new ReadCandidatesBW(context, election, new CandidatesCallback() {
            @Override
            public void onResult(ArrayList<CandidateModel> candidateList) {
                votedCandidate = getCandidateFromById(candidateList, election.getIdVotedCandidate());
                winnerCandidate = getCandidateFromById(candidateList, election.getIdWinnerCandidate());
                adapter = new ViewPastCandidatesAdapter(candidateList, votedCandidate, winnerCandidate);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context.get()));
                votedCNameTV.setText(votedCandidate.getName());
                votedCDescTV.setText(votedCandidate.getDescription());
                winnerCNameTV.setText(winnerCandidate.getName());
                winnerCDescTV.setText(winnerCandidate.getDescription());
            }
        });
        bg.execute();
    }

    private CandidateModel getCandidateFromById(ArrayList<CandidateModel> candidateList, int id) {
        for (CandidateModel candidate : candidateList) {
            if (candidate.getId() == id) {
                return candidate;
            }
        }
        return null;
    }
}