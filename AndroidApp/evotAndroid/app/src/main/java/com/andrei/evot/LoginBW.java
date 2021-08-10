package com.andrei.evot;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class LoginBW extends AsyncTask<String, Void, String> implements AdapterView.OnItemSelectedListener, MyCertificateManager {

    private String cmd = "loginBtn";
    private String tempResult = "";
    private int voteFlag = -1;
    private Context context;

    public LoginBW(String tempResult, Context context) {
        this.tempResult = tempResult;
        this.context = context;
    }

    @Override
    protected String doInBackground(String[] strings) {
        try {
            URL url = new URL(reg_url);
            if (cmd.equals("loginBtn")) {
                String post_data = URLEncoder.encode("cmd", "UTF-8") + "=" + URLEncoder.encode(cmd, "UTF-8") + "&" +
                        URLEncoder.encode("cnp", "UTF-8") + "=" + URLEncoder.encode(User.getCnp(), "UTF-8") + "&" +
                        URLEncoder.encode("seriesPers", "UTF-8") + "=" + URLEncoder.encode(User.getSeriesPers(), "UTF-8") + "&" +
                        URLEncoder.encode("numberPers", "UTF-8") + "=" + URLEncoder.encode(User.getNumberPers(), "UTF-8") + "&" +
                        URLEncoder.encode("idElections", "UTF-8") + "=" + URLEncoder.encode(User.getIdElection_(), "UTF-8");
                queryServer(post_data, url);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private void queryServer(String post_data, URL url) {
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

    @Override
    protected void onPostExecute(String s) {
        if (tempResult.equals("MBSC") && voteFlag == 1) {
            try {

                Intent showDetailActivity = new Intent(context, VotingScreen.class);
                context.startActivity(showDetailActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
