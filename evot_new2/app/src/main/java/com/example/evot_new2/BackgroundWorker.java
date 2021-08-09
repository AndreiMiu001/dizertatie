package com.example.evot_new2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static android.content.ContentValues.TAG;

public class BackgroundWorker extends AsyncTask<String, Void, String> implements AdapterView.OnItemSelectedListener {

    private String cmd;
    private String values;
    private String idElection;
    private String reg_url = "https://10.0.2.2/eVot/eVot2.php";
    private String tempResult = "";
    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Candidate> candidateList;
    private List<ElectionType> electionList;
    private Spinner spinner;
    private List<String> elections;
    private Context contextSpinner;
    private int voteFlag = -1;


    public BackgroundWorker(String cmd, List<Candidate> cl) {
        this.cmd = cmd;
        this.candidateList = cl;
    }

    BackgroundWorker(String cmd, RecyclerView recyclerView, Context context, List<Candidate> candidateList) {
        this.cmd = cmd;
        this.recyclerView = recyclerView;
        this.context = context;
        this.candidateList = candidateList;
    }

    public BackgroundWorker(String cmd, String tempResult, Context context, Activity activity) {
        this.cmd = cmd;
        this.idElection = idElection;
        this.tempResult = tempResult;
        this.context = context;
        this.activity = activity;
    }

    public BackgroundWorker(String cmd, List<ElectionType> electionList, String tempResult, Spinner spinner, Context context, Context contextSpinner) {
        this.cmd = cmd;
        this.electionList = electionList;
        this.tempResult = tempResult;
        this.spinner = spinner;
        elections = new ArrayList<>();
        this.context = context;
        this.contextSpinner = contextSpinner;
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
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("cmd", "UTF-8") + "=" + URLEncoder.encode(cmd, "UTF-8") + "&" +
                    URLEncoder.encode("CNP", "UTF-8") + "=" + URLEncoder.encode(Person.getCnp(), "UTF-8") + "&" +
                    URLEncoder.encode("idElections", "UTF-8") + "=" + URLEncoder.encode(Person.getIdElection_(), "UTF-8")+ "&" +
                    URLEncoder.encode("idCandidates", "UTF-8") + "=" + URLEncoder.encode(candidateId, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            Log.d(TAG, "writeData: exit");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void queryServer(String post_data, URL url) {
        try {
            trustAllCertificates();
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            if (cmd.equals("loginBtn")) {
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    tempResult += line;
                    if(tempResult.equals("PCSC")) {
                        voteFlag = 1;
                        tempResult = "";
                    }
                    else if(tempResult.equals("ERR:EAE"))
                    {
                        voteFlag = 0;
                        tempResult = "";
                    }
                }
            } else if (cmd.equals("getElections")) {
                String line = "";
                String idTipVot = "";
                String numeTipVot = "";
                ElectionType el;
                while ((line = bufferedReader.readLine()) != null) {
                    tempResult += line;
                    if (line.startsWith("0")) {
                        idTipVot = line.substring(1, line.length());
                    } else if (line.startsWith("1")) {
                        numeTipVot = line.substring(1, line.length());
                        el = new ElectionType(idTipVot, numeTipVot);
                        electionList.add(el);
                    }
                }
            } else if (cmd.equals("readCandidates")) {
                String line = "";
                String temp = new String();
                String name = new String();
                String info = new String();
                String id = new String();
                boolean err = false;
                Candidate il;
                while ((line = bufferedReader.readLine()) != null) {
                    tempResult += line;
                    if (line.startsWith("0")) {
                        name = line.substring(1, line.length());
                    } else if (line.startsWith("1")) {
                        info = line.substring(1, line.length());

                    } else if (line.startsWith("2")) {
                        id = line.substring(1, line.length());
                        il = new Candidate(name, info, id);
                        candidateList.add(il);
                    } else {
                        err = true;
                    }
                }
                if (err == true) {
                    Log.d(TAG, "doInBackground: ERROR");
                    System.exit(0);
                }
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            Log.d(TAG, "writeData: exit");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (cmd.equals("getElections")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, elections);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }
        else if (cmd.equals("vote"))
        {
            try {
                Intent showDetailActivity = new Intent(context, ReturnScreen.class);
                Bundle b = new Bundle();
                b.putString("final","SUCCES");
                showDetailActivity.putExtras(b);
                context.startActivity(showDetailActivity);
                tempResult = "1";
            }catch (Exception e) {
                e.printStackTrace();
            }
            }
        // TODO FIX CHECK FOR DUPLICATE VOTING
        if (tempResult.equals("MBSC") && voteFlag == 1) {
            try {

                Intent showDetailActivity = new Intent(context, VotingScreen.class);
                context.startActivity(showDetailActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tempResult.equals("ERR:WOIC") && voteFlag == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Informatie gresita").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if (voteFlag == 0)
        {
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
    protected String doInBackground(String[] strings) {
        try {
            URL url = new URL(reg_url);
            if (cmd.equals("loginBtn")) {
                String post_data = URLEncoder.encode("cmd", "UTF-8") + "=" + URLEncoder.encode(cmd, "UTF-8") + "&" +
                        URLEncoder.encode("cnp", "UTF-8") + "=" + URLEncoder.encode(Person.getCnp(), "UTF-8") + "&" +
                        URLEncoder.encode("seriesPers", "UTF-8") + "=" + URLEncoder.encode(Person.getSeriesPers(), "UTF-8") + "&" +
                        URLEncoder.encode("numberPers", "UTF-8") + "=" + URLEncoder.encode(Person.getNumberPers(), "UTF-8") + "&" +
                        URLEncoder.encode("idElections", "UTF-8") + "=" + URLEncoder.encode(Person.getIdElection_(), "UTF-8");
                queryServer(post_data, url);

            } else if (cmd.equals("readCandidates")) {
                String post_data = URLEncoder.encode("cmd", "UTF-8") + "=" + URLEncoder.encode(cmd, "UTF-8")+ "&" +
                        URLEncoder.encode("idElections", "UTF-8") + "=" + URLEncoder.encode(Person.getIdElection_(), "UTF-8");
                queryServer(post_data, url);
                Log.d(TAG, "doInBackground: exit");
            } else if (cmd.equals("getElections")) {
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

            } else if (cmd.equals("vote")) {
                for (int i = 0; i < candidateList.size(); i++) {
                    Candidate candidate = candidateList.get(i);
                    if (candidate.isChecked() == true) {
                        values = candidate.getCandidateId();
                        String res = queryServerVote(url, candidate.getCandidateId());
                    }
                }
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void trustAllCertificates() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception e) {
        }
    }
}
