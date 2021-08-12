package com.andrei.evot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button voteViewBtn = (Button) findViewById(R.id.voteViewBtn);
        Button pastVotesBtn = (Button) findViewById(R.id.pastVotesBtn);

        voteViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showDetailActivity = new Intent(getBaseContext(), SelectElection.class);
                startActivity(showDetailActivity);
            }
        });

        pastVotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showDetailActivity = new Intent(getBaseContext(), PastElectionVotesList.class);
                startActivity(showDetailActivity);
            }
        });

    }
}