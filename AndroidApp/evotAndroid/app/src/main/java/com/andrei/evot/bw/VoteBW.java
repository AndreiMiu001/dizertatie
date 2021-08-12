package com.andrei.evot.bw;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.andrei.evot.MyCertificateManager;
import com.andrei.evot.callbacks.VoteCallback;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import com.andrei.evot.model.VoteModel;


public class VoteBW extends AsyncTask<String, Void, String> implements MyCertificateManager {

    private final WeakReference<Context> context;
    private final VoteCallback voteCallback;
    private VoteModel vote;

    public VoteBW(WeakReference<Context> context, VoteModel vote, VoteCallback voteCallback) {
        this.vote = vote;
        this.context = context;
        this.voteCallback = voteCallback;
    }

    @Override
    protected String doInBackground(String[] strings) {
        String URL = "http://10.0.2.2:8080/evot/webapi/elections/vote";
        RequestQueue requestQueue = Volley.newRequestQueue(context.get());
        Gson jsonConverter = new Gson();
        String jsonString = jsonConverter.toJson(vote);
        JSONObject postData = null;
        try {
            postData = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("rest resp", response.toString());
                        voteCallback.onResult();
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

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
