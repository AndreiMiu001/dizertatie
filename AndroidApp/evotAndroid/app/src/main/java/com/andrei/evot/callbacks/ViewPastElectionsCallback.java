package com.andrei.evot.callbacks;

import com.andrei.evot.model.ElectionModel;

import java.util.ArrayList;

public interface ViewPastElectionsCallback {
    public void onResult(ArrayList<ElectionModel> electionList);
}
