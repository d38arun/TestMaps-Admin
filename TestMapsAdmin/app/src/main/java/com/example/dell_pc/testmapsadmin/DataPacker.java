package com.example.dell_pc.testmapsadmin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Dell - PC on 4/10/2017.
 */

public class DataPacker {
    String latitude, longitude;

    public DataPacker(String latitude, String longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    //Data to be sent
    public String pack()
    {
        JSONObject jsonObject = new JSONObject();
        StringBuffer stringBuffer= new StringBuffer();

        try{
            jsonObject.put("Latitude",latitude);
            jsonObject.put("Longitude",longitude);

            Boolean firstVal = true; //checks for first data value for url configuration

            Iterator iterator = jsonObject.keys();

            do{
                String key = iterator.next().toString();
                String value = jsonObject.get(key).toString();

                if(firstVal)
                {
                    firstVal = false;
                }
                else
                {
                    stringBuffer.append("&"); //for separating data in url
                }
                stringBuffer.append(URLEncoder.encode(key,"UTF-8"));
                stringBuffer.append("=");
                stringBuffer.append(URLEncoder.encode(value,"UTF-8"));
            }while(iterator.hasNext());

            return stringBuffer.toString(); //returns configured url

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;  //in case of any error
    }
}
