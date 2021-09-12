package com.andrei.evot;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andrei.evot.adapters.VotingAdapter;
import com.andrei.evot.bw.ReadCandidatesBW;
import com.andrei.evot.bw.VoteBW;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.andrei.evot.model.CandidateModel;
import com.andrei.evot.model.ElectionModel;
import com.andrei.evot.model.User;
import com.andrei.evot.model.VoteModel;


public class VotingScreen extends CommonBackActionActivity {

    private RecyclerView recyclerView;
    private VotingAdapter adapter;
    private ArrayList<CandidateModel> candidateList;
    private final WeakReference<Context> context = new WeakReference<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUserState();
        setContentView(R.layout.canditat_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Let's vote");
        }

        ElectionModel election = (ElectionModel) getIntent().getSerializableExtra("SelectedElection");
        recyclerView = (RecyclerView) findViewById(R.id.voteCandidateRV);

        Drawable mDivider = ContextCompat.getDrawable(this, R.drawable.divider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(mDivider);

        recyclerView.addItemDecoration(dividerItemDecoration);

        candidateList = new ArrayList<>();

        ReadCandidatesBW readBg = new ReadCandidatesBW(context, election, cList -> {
            candidateList = cList;
            adapter = new VotingAdapter(candidateList);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context.get()));
        });
        readBg.execute();

        Button voteBtn = (Button) findViewById(R.id.voteBtn);
        voteBtn.setOnClickListener(v -> {
            VoteModel vote = new VoteModel();
            CandidateModel candidate = getCheckedCandidate(candidateList);
            if ( candidate != null) {
                vote.setCandidate(candidate);
                vote.setElection(election);
                vote.setCnp(User.mCnp);
                VoteBW bg = new VoteBW(context, vote, () -> {
                    Intent showDetailActivity = new Intent(context.get(), ReturnScreen.class);
                    showDetailActivity.putExtra("VotedCandidate", candidate);
                    context.get().startActivity(showDetailActivity);
                });
                bg.execute();
            } else {
                Toast.makeText(context.get(), "You can only vote for 1 candidate!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private CandidateModel getCheckedCandidate(ArrayList<CandidateModel> candidateList) {
        CandidateModel returnCandidate = null;
        int count = 0;
        for (CandidateModel candidate : candidateList) {
            if (candidate.isChecked()) {
                returnCandidate = candidate;
                count++;
            }
        }
        if (count == 1) {
            return returnCandidate;
        }
        return null;
    }
}