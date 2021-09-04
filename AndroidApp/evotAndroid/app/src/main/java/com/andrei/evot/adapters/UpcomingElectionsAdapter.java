package com.andrei.evot.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.andrei.evot.R;
import com.andrei.evot.callbacks.ElectionModelCallback;
import com.andrei.evot.model.ElectionModel;

import java.util.ArrayList;

public class UpcomingElectionsAdapter extends RecyclerView.Adapter<UpcomingElectionsAdapter.ViewHolder> {

    private final ArrayList<ElectionModel> electionList;
    private ElectionModelCallback electionModelCallback;

    public UpcomingElectionsAdapter(ArrayList<ElectionModel> electionList, ElectionModelCallback electionModelCallback) {
        this.electionList = electionList;
        this.electionModelCallback = electionModelCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_elections_list, parent, false);
        return new UpcomingElectionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElectionModel currElection = electionList.get(position);
        holder.nameTV.setText(currElection.getElectionName());
        holder.dateTV.setText(currElection.getStartingDate());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                electionModelCallback.onResult(currElection);
            }
        });
    }

    @Override
    public int getItemCount() {
        return electionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTV;
        public TextView dateTV;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.upcomingElectionNameTV);
            dateTV = itemView.findViewById(R.id.upcomingElectionDateTV);
            constraintLayout = itemView.findViewById(R.id.upcomingElectionsCL);
        }
    }
}
