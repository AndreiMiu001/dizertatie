package com.andrei.evot.callbacks;

import com.andrei.evot.model.ElectionModel;

import java.util.ArrayList;

public interface ElectionListCallback {
    public void onResult(ArrayList<ElectionModel> electionList);
}
