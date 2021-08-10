package com.andrei.evot;


import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class GetElectionsBW extends AsyncTask<String, Void, String> implements AdapterView.OnItemSelectedListener, MyCertificateManager {

    private Context context;
    private List<ElectionType> electionList;
    private Spinner spinner;
    private List<String> elections;

    GetElectionsBW(List<ElectionType> electionList, Spinner spinner, Context context) {
        this.electionList = electionList;
        this.spinner = spinner;
        elections = new ArrayList<>();
        this.context = context;
    }

    @Override
    protected String doInBackground(String[] strings) {
        String cmd = "getElections";
        URL url = null;
        try {
            url = new URL(reg_url);
            String post_data = URLEncoder.encode("cmd", "UTF-8") + "=" + URLEncoder.encode(cmd, "UTF-8");
            queryServer(post_data, url);
            for (int i = 0; i < electionList.size(); i++) {
                String str = electionList.get(i).getElectionName();
                elections.add(str);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, elections);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, elections);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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
            String line = "";
            String idTipVot = "";
            String numeTipVot = "";
            ElectionType el;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("0")) {
                    idTipVot = line.substring(1);
                } else if (line.startsWith("1")) {
                    numeTipVot = line.substring(1);
                    el = new ElectionType(idTipVot, numeTipVot);
                    electionList.add(el);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
