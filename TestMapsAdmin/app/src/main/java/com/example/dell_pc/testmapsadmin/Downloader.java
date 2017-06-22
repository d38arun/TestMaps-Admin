package com.example.dell_pc.testmapsadmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dell - PC on 4/10/2017.
 */

public class Downloader extends AsyncTask<Void, Integer,String> {
    Context c;
    String address;
    GoogleMap map;
    ProgressDialog progressDialog;

    public Downloader(Context c, String address, GoogleMap map) {
        this.c = c;
        this.address = address;
        this.map = map;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(c);
        progressDialog.setTitle("Getting");
        progressDialog.setMessage("Downloading..Wait..");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String data = downloadData();
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        if(s != null)
        {
            Parser parser = new Parser(c,map,s);
            parser.execute();
        }else{
            Toast.makeText(c,"Unable to Download",Toast.LENGTH_LONG).show();
        }
    }
    private String downloadData()
    {
        InputStream inputStream = null;
        String line = null;

        try{
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            if(bufferedReader !=  null)
            {
                while((line = bufferedReader.readLine()) != null)
                {
                    stringBuffer.append(line+"\n");

                }
            }else
            { return null;}
            return stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
