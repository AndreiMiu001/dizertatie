package com.andrei.evot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class temp extends Activity {
    private Spinner spinner;
    private Button startBtn;
    private List<ElectionType> electionList;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        electionList = new ArrayList<ElectionType>();
        spinner = (Spinner) findViewById(R.id.spinner);

        GetElectionsBW bg = new GetElectionsBW(electionList, spinner, context);
        bg.execute();

        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.mIdElection = (electionList.get(spinner.getSelectedItemPosition()).getIdElection());
                Intent showDetailActivity = new Intent(context, LoginScreen.class);
                context.startActivity(showDetailActivity);
            }
        });
    }
}
