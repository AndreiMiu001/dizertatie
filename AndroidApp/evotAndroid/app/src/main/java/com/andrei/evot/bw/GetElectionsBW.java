package com.andrei.evot.bw;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.andrei.evot.MyCertificateManager;
import com.andrei.evot.callbacks.ElectionCallback;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.ElectionModel;


public class GetElectionsBW extends AsyncTask<String, Void, String> implements AdapterView.OnItemSelectedListener, MyCertificateManager {

    private final WeakReference<Context> context;
    private ArrayList<ElectionModel> electionList;
    private final List<String> elections;
    private final ElectionCallback myCallback;


    public GetElectionsBW(WeakReference<Context> context, ElectionCallback callBack) {
        this.electionList = new ArrayList<>();
        elections = new ArrayList<>();
        this.context = context;
        myCallback = callBack;
    }

    @Override
    protected String doInBackground(String[] strings) {
        String URL = "http://10.0.2.2:8080/evot/webapi/elections";
        RequestQueue requestQueue = Volley.newRequestQueue(context.get());
        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson jsonConverter = new Gson();
                        Type type = new TypeToken<ArrayList<ElectionModel>>(){}.getType();
                        electionList = jsonConverter.fromJson(response.toString(), type);
                        for(ElectionModel election : electionList) {
                            Log.e("election: ", election.toString());
                        }
                        myCallback.onResult(electionList);
                        Log.e("rest resp array", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("rest err array", error.toString());
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
