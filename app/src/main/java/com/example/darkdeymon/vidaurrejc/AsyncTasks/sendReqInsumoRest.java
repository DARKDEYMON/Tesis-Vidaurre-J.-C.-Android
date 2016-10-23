package com.example.darkdeymon.vidaurrejc.AsyncTasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.darkdeymon.vidaurrejc.Multipart.JasonMultipartSend;
import com.example.darkdeymon.vidaurrejc.R;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DARKDEYMON on 22/10/2016.
 */

public class sendReqInsumoRest extends AsyncTask<Void,Void,String> {
    String username;
    String password;
    String data;
    AppCompatActivity main;
    public sendReqInsumoRest(String username, String password,String data, AppCompatActivity main)
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
            String res = s.getData(new URL(main.getString(R.string.server)+main.getString(R.string.reqinsumos)),username,password,data);
            if(res!=null)
                return res;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
