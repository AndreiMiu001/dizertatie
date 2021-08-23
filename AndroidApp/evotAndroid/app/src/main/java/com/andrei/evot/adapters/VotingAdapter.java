package com.andrei.evot.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.andrei.evot.R;
import com.andrei.evot.model.CandidateModel;

public class VotingAdapter extends RecyclerView.Adapter<VotingAdapter.ViewHolder> {

    private final List<CandidateModel> candidateList;

    public VotingAdapter(List<CandidateModel> itemList) {
        this.candidateList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vote_item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final CandidateModel tempList = candidateList.get(i);
        viewHolder.nameTV.setText(tempList.getName());
        viewHolder.infoTV.setText(tempList.getDescription());
        if (candidateList.get(i).getId() == 0 ) {
            viewHolder.checkBox.setTag(i);
        }
        else
            viewHolder.checkBox.setTag(0);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tempList.setChecked(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTV;
        public TextView infoTV;
        public CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.viewNameTV);
            infoTV = itemView.findViewById(R.id.viewDescTV);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}