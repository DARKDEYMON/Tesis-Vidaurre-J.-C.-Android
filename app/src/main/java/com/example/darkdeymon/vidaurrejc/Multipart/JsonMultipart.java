package com.example.darkdeymon.vidaurrejc.Multipart;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 15/10/2016.
 */

public class JsonMultipart {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public String getData(URL url, String username, String password){

        HttpURLConnection res= null;
        try {
            res = (HttpURLConnection)url.openConnection();


            String userCredentials = username+":"+password;

                /* autenticacion*/
            String basicAuth = "Basic " + Base64.encodeToString(userCredentials.getBytes(),Base64.NO_WRAP | Base64.URL_SAFE);
            Log.e("pass", basicAuth);
            res.setRequestProperty ("Authorization", basicAuth);

            Log.e("pass", String.valueOf(res.getResponseCode()));
            if(res.getResponseCode()==200)
            {
                BufferedReader read=new BufferedReader(new InputStreamReader(res.getInputStream()));
                String jsonText = readAll(read);
                Log.e("jason", jsonText);
                JSONArray json = null;
                read.close();
                res.disconnect();
                //json = new JSONArray(jsonText);
                return jsonText;
            }
            else{
                res.disconnect();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
