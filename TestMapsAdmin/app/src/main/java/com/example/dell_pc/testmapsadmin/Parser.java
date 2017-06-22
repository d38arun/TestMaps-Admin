package com.example.dell_pc.testmapsadmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dell - PC on 4/10/2017.
 */

public class Parser extends AsyncTask<Void,Integer,Integer> {


    Context c;
    GoogleMap map;
    String data;
    ProgressDialog progressDialog;
    ArrayList<String> latitudeArray = new ArrayList<>();
    ArrayList<String> longitudeArray = new ArrayList<>();
    ArrayList<String> idArray = new ArrayList<>();

    public Parser(Context c, GoogleMap map, String data) {
        this.c = c;
        this.map = map;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(c);
        progressDialog.setTitle("Setting Data");
        progressDialog.setMessage("Setting Data..Please Wait");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer == 1)
        {


            for(int i=0;i<latitudeArray.size();i++)
            {
                double latitude =Double.parseDouble( latitudeArray.get(i));
                double longitude =Double.parseDouble(longitudeArray.get(i));
                String complaint_id = idArray.get(i);
                LatLng latLng = new LatLng(latitude,longitude);
                if(map != null) {
                    map.addMarker(new MarkerOptions().position(latLng).title(complaint_id));

                }
                else
                    Toast.makeText(c,"NULL",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(c,"Unable to parse",Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }

    private int parse(){
        try{
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = null;

            latitudeArray.clear();
            longitudeArray.clear();
            idArray.clear();

            for(int i=0;i<jsonArray.length();i++)
            {
                String lat,longe,id;
                jsonObject = jsonArray.getJSONObject(i);
                lat = jsonObject.getString("latitude");
                longe = jsonObject.getString("longitude");
                id = jsonObject.getString("id");
                idArray.add(id);
                latitudeArray.add(lat);
                longitudeArray.add(longe);

            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
