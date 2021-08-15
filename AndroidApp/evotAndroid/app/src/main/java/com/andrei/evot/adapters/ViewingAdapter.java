package com.andrei.evot.adapters;

import android.util.Log;
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

public class ViewingAdapter extends RecyclerView.Adapter<ViewingAdapter.ViewHolder> {

    private final ArrayList<ElectionModel> electionList;
    private final ElectionModelCallback electionModelCallback;

    public ViewingAdapter(ArrayList<ElectionModel> electionList, ElectionModelCallback electionModelCallback) {
        this.electionList = electionList;
        this.electionModelCallback = electionModelCallback;
    }

    @NonNull
    @Override
    public ViewingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_list, parent, false);
        return new ViewingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewingAdapter.ViewHolder holder, int position) {
        final ElectionModel electionTemp = electionList.get(position);
        holder.nameTV.setText(electionTemp.getElectionName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Clicked on ", electionTemp.getElectionName());
                electionModelCallback.onResult(electionTemp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return electionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTV;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.viewNameTV);
            constraintLayout = itemView.findViewById(R.id.viewElectionCL);
        }
    }
}
