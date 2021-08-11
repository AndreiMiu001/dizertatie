package com.andrei.evot;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andrei.evot.callbacks.CandidatesCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import model.CandidateModel;
import model.ElectionModel;


public class VotingScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<CandidateModel> candidateList;
    private Button voteBtn;
    private WeakReference<Context> context = new WeakReference<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canditat_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ElectionModel election = (ElectionModel) getIntent().getSerializableExtra("SelectedElection");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        candidateList = new ArrayList<>();

        ReadCandidatesBW readBg = new ReadCandidatesBW(context, election, new CandidatesCallback() {
            @Override
            public void onResult(ArrayList<CandidateModel> cList) {
                candidateList = cList;
                adapter = new MyAdapter(candidateList);
                recyclerView.setAdapter((RecyclerView.Adapter) adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context.get()));
            }
        });
        readBg.execute();


        voteBtn = (Button) findViewById(R.id.voteBtn);
        voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    VoteBW bg = new VoteBW(context, candidateList);
            //    bg.execute();
            }
        });
    }
}