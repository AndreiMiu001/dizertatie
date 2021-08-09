package com.example.evot_new2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class VotingScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Candidate> candidateList;
    private Button voteBtn;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canditat_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        candidateList = new ArrayList<>();

        ReadCandidatesBW readBg = new ReadCandidatesBW(candidateList);
        readBg.execute();

        adapter = new Adapter(candidateList, this);
        recyclerView.setAdapter(adapter);
        voteBtn = (Button) findViewById(R.id.voteBtn);
        voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoteBW bg = new VoteBW(context, candidateList);
                bg.execute();
            }
        });
    }
}