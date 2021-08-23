package com.andrei.evot.bw;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.andrei.evot.MyCertificateManager;
import com.andrei.evot.callbacks.CandidatesCallback;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.andrei.evot.model.CandidateModel;
import com.andrei.evot.model.ElectionModel;


public class ReadCandidatesBW extends AsyncTask<String, Void, String> implements MyCertificateManager {

    private final CandidatesCallback candidatesCallback;
    private final WeakReference<Context> context;
    private final ElectionModel election;

    public ReadCandidatesBW(WeakReference<Context> context, ElectionModel election, CandidatesCallback candidatesCallback) {
        this.candidatesCallback = candidatesCallback;
        this.context = context;
        this.election = election;
    }

    @Override
    protected String doInBackground(String[] strings) {
        String URL = "https://10.0.2.2:8442/evot/webapi/elections/candidates";
        trustAllCertificates();
        RequestQueue requestQueue = Volley.newRequestQueue(context.get());
        Gson jsonConverter = new Gson();
        ArrayList<ElectionModel> tempList = new ArrayList<>();
        tempList.add(election);
        String jsonString = jsonConverter.toJson(tempList);
        JSONArray postData = null;
        try {
            postData = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.POST,
                URL,
                postData,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<CandidateModel> candidateList = new ArrayList<>();
                        Gson jsonConverter = new Gson();
                        Type type = new TypeToken<ArrayList<CandidateModel>>(){}.getType();
                        candidateList = jsonConverter.fromJson(response.toString(), type);
                        candidatesCallback.onResult(candidateList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("rest err", error.toString());

                    }
                }
        );
        requestQueue.add(objectRequest);
        return null;
    }
}