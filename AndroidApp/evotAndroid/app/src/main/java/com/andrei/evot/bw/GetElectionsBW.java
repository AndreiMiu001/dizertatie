package com.andrei.evot.bw;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.andrei.evot.MyCertificateManager;
import com.andrei.evot.callbacks.ElectionCallback;
import com.andrei.evot.model.Person;
import com.andrei.evot.model.User;
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
import java.util.List;

import com.andrei.evot.model.ElectionModel;


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
        String URL = "https://10.0.2.2:8442/evot/webapi/elections";
        trustAllCertificates();
        RequestQueue requestQueue = Volley.newRequestQueue(context.get());
        Gson jsonConverter = new Gson();
        ArrayList<Person> tempList = new ArrayList<>();
        Person person = new Person(User.mCnp, User.mPassword);
        tempList.add(person);
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
                        Gson jsonConverter = new Gson();
                        Type type = new TypeToken<ArrayList<ElectionModel>>(){}.getType();
                        electionList = jsonConverter.fromJson(response.toString(), type);
                        myCallback.onResult(electionList);
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
