package com.andrei.evot.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.andrei.evot.R;
import com.andrei.evot.model.CandidateModel;

import java.util.ArrayList;

public class SelectedUpcomingElectionsAdapter  extends RecyclerView.Adapter<SelectedUpcomingElectionsAdapter.ViewHolder> {

    private final ArrayList<CandidateModel> candidateList;

    public SelectedUpcomingElectionsAdapter(ArrayList<CandidateModel> candidateList) {
        this.candidateList = candidateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_past_candidates_list, parent, false);
        return new SelectedUpcomingElectionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CandidateModel candidate = candidateList.get(position);
        holder.nameTV.setText(candidate.getName());
        holder.descTV.setText(candidate.getDescription());
    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTV;
        public TextView descTV;
        public ImageView winnerIV;
        public ImageView votedIV;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.viewCandidatePastNameTV);
            descTV = itemView.findViewById(R.id.viewCandidatePastDescTV);
            winnerIV = itemView.findViewById(R.id.winnerIV);
            votedIV = itemView.findViewById(R.id.votedIV);
            constraintLayout = itemView.findViewById(R.id.viewPastCandidatesCL);
        }
    }
}
