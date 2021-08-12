package com.andrei.evot.callbacks;

import com.andrei.evot.model.CandidateModel;

import java.util.ArrayList;

public interface CandidatesCallback {
    public void onResult(ArrayList<CandidateModel> candidateList);
}
