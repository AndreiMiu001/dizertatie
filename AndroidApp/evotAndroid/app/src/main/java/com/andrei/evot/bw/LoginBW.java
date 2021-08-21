package com.andrei.evot.bw;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.andrei.evot.MainMenu;
import com.andrei.evot.MyCertificateManager;
import com.andrei.evot.model.User;
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

    public LoginBW(WeakReference<Context> context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String[] strings) {
        String URL = "https://10.0.2.2:8442/evot/webapi/login";
        RequestQueue requestQueue = Volley.newRequestQueue(context.get());
        JSONObject postData = new JSONObject();
        trustAllCertificates();
        try {
            postData.put("cnp", User.mCnp);
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
                        try {
                            if (response.getBoolean("state")) {
                                Log.e("rest resp2", "true");
                                Intent showDetailActivity = new Intent(context.get(), MainMenu.class);
                                context.get().startActivity(showDetailActivity);
                            } else {
                                Toast.makeText(context.get(), "Invalid username of password!", Toast.LENGTH_LONG).show();
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
