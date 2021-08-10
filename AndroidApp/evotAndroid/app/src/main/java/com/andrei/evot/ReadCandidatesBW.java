package com.andrei.evot;


import android.os.AsyncTask;

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
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class ReadCandidatesBW extends AsyncTask<String, Void, String> implements MyCertificateManager {

    private String cmd = "readCandidates";
    private List<Candidate> candidateList;

    ReadCandidatesBW(List<Candidate> candidateList) {
        this.candidateList = candidateList;
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
            String name = "";
            String info = "";
            String id = "";
            boolean err = false;
            Candidate il;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("0")) {
                    name = line.substring(1);
                } else if (line.startsWith("1")) {
                    info = line.substring(1);

                } else if (line.startsWith("2")) {
                    id = line.substring(1);
                    il = new Candidate(name, info, id);
                    candidateList.add(il);
                } else {
                    err = true;
                }
            }
            if (err == true) {
                System.exit(0);
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
    protected String doInBackground(String[] strings) {
        try {
            URL url = new URL(reg_url);
            String post_data = "";/*URLEncoder.encode("cmd", "UTF-8") + "=" + URLEncoder.encode(cmd, "UTF-8") + "&" +
                    URLEncoder.encode("cnp", "UTF-8") + "=" + URLEncoder.encode(User.getCnp(), "UTF-8") + "&" +
                    URLEncoder.encode("seriesPers", "UTF-8") + "=" + URLEncoder.encode(User.getSeriesPers(), "UTF-8") + "&" +
                    URLEncoder.encode("numberPers", "UTF-8") + "=" + URLEncoder.encode(User.getNumberPers(), "UTF-8") + "&" +
                    URLEncoder.encode("idElections", "UTF-8") + "=" + URLEncoder.encode(User.getIdElection_(), "UTF-8");
            queryServer(post_data, url);*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}