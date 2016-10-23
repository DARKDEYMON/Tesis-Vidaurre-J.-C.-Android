package com.example.darkdeymon.vidaurrejc.AsyncTasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.darkdeymon.vidaurrejc.Multipart.JsonMultipart;
import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.insumo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 22/10/2016.
 */

public class insumoRest extends AsyncTask<Void,Void,ArrayList<insumo>> {

    String username;
    String password;
    AppCompatActivity main;
    public insumoRest(String username, String password, AppCompatActivity main)
    {
        this.username = username;
        this.password = password;
        this.main = main;
    }
    @Override
    protected ArrayList<insumo> doInBackground(Void... params) {
        JsonMultipart j= new JsonMultipart();
        try {
            String dat = j.getData(new URL(main.getString(R.string.server)+main.getString(R.string.getinsumos)),username,password);
            if(dat!= null)
                return insumo.listInsumo(dat);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
