package com.example.darkdeymon.vidaurrejc.Multipart;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DARKDEYMON on 20/10/2016.
 */

public class JasonMultipartSend {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    private Boolean put;
    public JasonMultipartSend(){
        this.put=false;
    }
    public JasonMultipartSend(Boolean put){
        this.put=put;
    }
    public String getData(URL url, String username, String password, String data){

        HttpURLConnection res= null;
        try {
            res = (HttpURLConnection)url.openConnection();

            res.setDoOutput(true);
            res.setDoInput(true);
            res.setRequestProperty( "Content-Type", "application/json" );
            if(put)
                res.setRequestMethod("PUT");
            else
                res.setRequestMethod("POST");
             /* autenticacion*/
            String userCredentials = username+":"+password;
            String basicAuth = "Basic " + Base64.encodeToString(userCredentials.getBytes(),Base64.NO_WRAP | Base64.URL_SAFE);
            Log.e("passita", basicAuth);
            res.setRequestProperty ("Authorization", basicAuth);


            /* autenticacion
            String userCredentials = username+":"+password;
            String basicAuth = "Basic " + Base64.encodeToString(userCredentials.getBytes(),Base64.NO_WRAP);
            Log.e("pass", basicAuth);
            res.setRequestProperty ("Authorization", basicAuth);
            */

            OutputStream os = res.getOutputStream();
            OutputStreamWriter o = new OutputStreamWriter(os, "UTF-8");
            o.write(data);
            o.close();
            os.close();

            Log.e("data", data);
            Log.e("pass", String.valueOf(res.getResponseCode()));
            if(res.getResponseCode()==201)
            {
                BufferedReader read=new BufferedReader(new InputStreamReader(res.getInputStream()));
                String jsonText = readAll(read);
                Log.e("jason", jsonText);
                //JSONArray json = null;
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
            res.disconnect();
            e.printStackTrace();
            return null;
        }
    }
}
