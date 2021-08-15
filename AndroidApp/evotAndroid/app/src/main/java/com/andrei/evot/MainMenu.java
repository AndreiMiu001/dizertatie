package com.andrei.evot;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends CommonBasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button voteViewBtn = (Button) findViewById(R.id.voteViewBtn);
        Button pastVotesBtn = (Button) findViewById(R.id.pastVotesBtn);
        Button upcomingBtn = (Button) findViewById(R.id.upcomingElectionsBtn);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Main menu");
        }

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

        upcomingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showDetailActivity = new Intent(getBaseContext(), UpcomingElectionsScreen.class);
                startActivity(showDetailActivity);
            }
        });

    }
}