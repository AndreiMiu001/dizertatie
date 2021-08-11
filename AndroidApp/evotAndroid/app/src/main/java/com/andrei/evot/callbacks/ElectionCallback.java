package com.andrei.evot.callbacks;

import java.util.ArrayList;

import model.ElectionModel;

public interface ElectionCallback {

    public void onResult(ArrayList<ElectionModel> electionList);
}
