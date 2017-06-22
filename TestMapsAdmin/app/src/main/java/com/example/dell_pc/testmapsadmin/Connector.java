package com.example.dell_pc.testmapsadmin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dell - PC on 4/10/2017.
 */

public class Connector {
    public static HttpURLConnection connect (String urlAdd) //returns an http url connection object
    {
        try
        {
            URL url = new URL(urlAdd);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //set connection properties
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            return connection; //returning connection object on success

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; //returns null on connection failure
    }
}
