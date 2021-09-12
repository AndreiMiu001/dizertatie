package com.andrei.evot;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.widget.TextView;

import com.andrei.evot.adapters.SelectedUpcomingElectionsAdapter;
import com.andrei.evot.bw.ReadCandidatesBW;
import com.andrei.evot.callbacks.CandidatesCallback;
import com.andrei.evot.model.CandidateModel;
import com.andrei.evot.model.ElectionModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SelectedUpcomingElectionScreen extends CommonBackActionActivity {

    private final WeakReference<Context> context = new WeakReference<>(this);
    private SelectedUpcomingElectionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUserState();
        setContentView(R.layout.activity_selected_upcoming_election_screen);

        ElectionModel election = (ElectionModel) getIntent().getSerializableExtra("SelectedUpcomingElection");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(election.getElectionName());
        }

        TextView electionNameTV = findViewById(R.id.selectedUpcomingElectionNameTV);
        TextView dateTV = findViewById(R.id.upcomingActiveDateValuesTV);
        RecyclerView recyclerView = findViewById(R.id.upcomingCandidatesRV);

        electionNameTV.setText(election.getElectionName());
        dateTV.setText(election.getStartingDate() + " -- " + election.getEndingDate());

        ReadCandidatesBW bg = new ReadCandidatesBW(context, election, new CandidatesCallback() {
            @Override
            public void onResult(ArrayList<CandidateModel> candidateList) {
                adapter = new SelectedUpcomingElectionsAdapter(candidateList);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context.get()));
            }
        });
        bg.execute();

        ShapeDrawable hLine = new ShapeDrawable(new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {
                paint.setStrokeWidth(10);
                paint.setColor(Color.BLACK);
                canvas.drawLine(0, 0, 500, 0, paint);
            }
        });
        Drawable mDivider = ContextCompat.getDrawable(this, R.drawable.divider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(mDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}