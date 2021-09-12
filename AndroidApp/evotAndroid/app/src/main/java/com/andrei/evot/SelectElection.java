package com.andrei.evot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;

import com.andrei.evot.bw.GetElectionsBW;
import com.andrei.evot.callbacks.ElectionCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.andrei.evot.model.ElectionModel;
import com.andrei.evot.model.User;

public class SelectElection extends CommonBackActionActivity {

    private Spinner spinner;
    private Button startBtn;
    private ArrayList<ElectionModel> electionList;
    private WeakReference<Context> context = new WeakReference<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUserState();
        setContentView(R.layout.activity_select_election);

        electionList = new ArrayList<ElectionModel>();
        spinner = (Spinner) findViewById(R.id.spinner);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Select election");
        }

        GetElectionsBW bg = new GetElectionsBW(context, new ElectionCallback() {
            @Override
            public void onResult(ArrayList<ElectionModel> list) {
                electionList = list;
                List<String> electionNames = new ArrayList<>();
                for (ElectionModel election : electionList) {
                    electionNames.add(election.getElectionName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context.get(), R.layout.spinner_item, electionNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });
        bg.execute();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.mIdElection = (String.valueOf(electionList.get(spinner.getSelectedItemPosition()).getIdElection()));
                Intent showDetailActivity = new Intent(context.get(), VotingScreen.class);
                showDetailActivity.putExtra("SelectedElection", electionList.get(spinner.getSelectedItemPosition()));
                context.get().startActivity(showDetailActivity);
            }
        });
    }
}