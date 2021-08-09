package com.example.evot_new2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Candidate> candidateList;
    private Context context;

    public Adapter(List<Candidate> itemList, Context context) {
        this.candidateList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Candidate tempList = candidateList.get(i);
        viewHolder.nameTV.setText(tempList.getName());
        viewHolder.infoTV.setText(tempList.getAdditionaInfo());
        if (candidateList.get(i).getCandidateId() != null ) {
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
            nameTV = (TextView) itemView.findViewById(R.id.nameTV);
            infoTV = (TextView) itemView.findViewById(R.id.infoTV);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
