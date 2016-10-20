package com.example.darkdeymon.vidaurrejc.AsyncTasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.darkdeymon.vidaurrejc.Multipart.JasonMultipartSend;
import com.example.darkdeymon.vidaurrejc.Multipart.JsonMultipart;
import com.example.darkdeymon.vidaurrejc.R;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DARKDEYMON on 20/10/2016.
 */

public class sendReporteRest extends AsyncTask <Void,Void,String> {

    String username;
    String password;
    String data;
    AppCompatActivity main;
    public sendReporteRest(String username, String password,String data, AppCompatActivity main)
    {
        this.username = username;
        this.password = password;
        this.data = data;
        this.main = main;
    }
    @Override
    protected String doInBackground(Void... params) {
        JasonMultipartSend s= new JasonMultipartSend();
        try {
            String d = s.getData(new URL(main.getString(R.string.server)+main.getString(R.string.senditem)),username,password,data);
            if(d!=null)
                return d;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
