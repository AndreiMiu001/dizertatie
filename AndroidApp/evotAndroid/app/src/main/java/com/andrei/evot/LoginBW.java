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
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginBW extends AsyncTask<String, Void, String> implements AdapterView.OnItemSelectedListener, MyCertificateManager {

    private Context context;
    private boolean status;

    public LoginBW(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String[] strings) {
        String URL = "http://10.0.2.2:8080/evot/webapi/evot/login";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
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

    /*  private void queryServer(String post_data, URL url) {
          try {
              trustAllCertificates();
              HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
              httpURLConnection.setRequestMethod("POST");
              httpURLConnection.setDoOutput(true);
              httpURLConnection.setDoInput(true);
              OutputStream outputStream = httpURLConnection.getOutputStream();
              BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
              bufferedWriter.write(post_data);
              bufferedWriter.flush();
              bufferedWriter.close();
              outputStream.close();
              InputStream inputStream = httpURLConnection.getInputStream();
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
              if (cmd.equals("loginBtn")) {
                  String line = "";
                  while ((line = bufferedReader.readLine()) != null) {
                      tempResult += line;
                      if (tempResult.equals("PCSC")) {
                          voteFlag = 1;
                          tempResult = "";
                      } else if (tempResult.equals("ERR:EAE")) {
                          voteFlag = 0;
                          tempResult = "";
                      }
                  }
              }
              bufferedReader.close();
              inputStream.close();
              httpURLConnection.disconnect();
          } catch (
                  MalformedURLException e) {
              e.printStackTrace();
          } catch (
                  IOException e) {
              e.printStackTrace();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  */
    @Override
    protected void onPostExecute(String s) {
        if (status == true) {
            try {
                Intent showDetailActivity = new Intent(context, VotingScreen.class);
                context.startActivity(showDetailActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
        if (tempResult.equals("MBSC") && voteFlag == 1) {

        } else if (tempResult.equals("ERR:WOIC") && voteFlag == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Date personale eronate").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (voteFlag == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Ati votat deja - puteti vote o singura data").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setNegativeButton("Change election", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent showDetailActivity = new Intent(context, MainActivity.class);
                    context.startActivity(showDetailActivity);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
