package com.andrei.evot;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class LoginBW extends AsyncTask<String, Void, String> implements AdapterView.OnItemSelectedListener, MyCertificateManager {

    private final WeakReference<Context> context;
    private static boolean status;

    public LoginBW(WeakReference<Context> context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String[] strings) {
        String URL = "http://10.0.2.2:8080/evot/webapi/login";
        RequestQueue requestQueue = Volley.newRequestQueue(context.get());
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", User.mCnp);
            postData.put("password", User.mPassword);
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
                        try {
                            if (response.getBoolean("state")) {
                                Log.e("rest resp2", "true");
                                Intent showDetailActivity = new Intent(context.get(), MainMenu.class);
                                context.get().startActivity(showDetailActivity);
                                status = true;
                            } else {
                                Log.e("rest resp2", "false");
                                status = false;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
