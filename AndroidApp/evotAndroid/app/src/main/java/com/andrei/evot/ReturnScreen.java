package com.andrei.evot;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import com.andrei.evot.model.CandidateModel;

import java.lang.ref.WeakReference;

public class ReturnScreen extends CommonBackActionActivity {

    private final WeakReference<Context> context = new WeakReference<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUserState();
        setContentView(R.layout.return_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CandidateModel candidateModel = (CandidateModel) getIntent().getSerializableExtra("VotedCandidate");


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Congratulations!");
        }

        TextView finalMsg = (TextView) findViewById(R.id.finalMsg);
        Button finishBtn = (Button) findViewById(R.id.finishBtn);
        if (candidateModel != null) {
            finalMsg.setText("You have succesfully voted for " + candidateModel.getName());
        } else {
            finalMsg.setText("There was a problem getting the candidate information.");
        }
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showDetailActivity = new Intent(context.get(), MainMenu.class);
                context.get().startActivity(showDetailActivity);
            }
        });

    }
}
