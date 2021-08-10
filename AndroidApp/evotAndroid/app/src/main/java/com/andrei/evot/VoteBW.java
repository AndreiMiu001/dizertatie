package com.andrei.evot;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

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


public class VoteBW extends AsyncTask<String, Void, String> implements MyCertificateManager {

    private String cmd = "vote";
    private Context context;
    private List<Candidate> candidateList;

    VoteBW(Context context, List<Candidate> candidateList) {
        this.context = context;
        this.candidateList = candidateList;
    }

    @Override
    protected String doInBackground(String[] strings) {
        String str = null;
        try {
            URL url = new URL(reg_url);
            for (int i = 0; i < candidateList.size(); i++) {
                Candidate candidate = candidateList.get(i);
                if (candidate.isChecked() == true) {
                    str = queryServerVote(url, candidate.getCandidateId());
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return str;
    }

    private String queryServerVote(URL url, String candidateId) {
        String result = "";
        trustAllCertificates();
        try {
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String post_data = URLEncoder.encode("cmd", "UTF-8") + "=" + URLEncoder.encode(cmd, "UTF-8") + "&" +
                    URLEncoder.encode("CNP", "UTF-8") + "=" + URLEncoder.encode(User.getCnp(), "UTF-8") + "&" +
                    URLEncoder.encode("idElections", "UTF-8") + "=" + URLEncoder.encode(User.getIdElection_(), "UTF-8") + "&" +
                    URLEncoder.encode("idCandidates", "UTF-8") + "=" + URLEncoder.encode(candidateId, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            Intent showDetailActivity = new Intent(context, ReturnScreen.class);
            Bundle b = new Bundle();
            if (s.equals("update succes")) {
                b.putString("final", "Votul a fost înregistrat!");
            } else
                b.putString("final", "A apărut o erroare, ne pare rău !!");
            showDetailActivity.putExtras(b);
            context.startActivity(showDetailActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
