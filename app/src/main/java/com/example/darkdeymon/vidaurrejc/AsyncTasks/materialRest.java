package com.example.darkdeymon.vidaurrejc.AsyncTasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.darkdeymon.vidaurrejc.Multipart.JsonMultipart;
import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.material;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 22/10/2016.
 */

public class materialRest extends AsyncTask<Void,Void,ArrayList<material>> {

    String username;
    String password;
    AppCompatActivity main;
    public materialRest(String username, String password, AppCompatActivity main)
    {
        this.username = username;
        this.password = password;
        this.main = main;
    }
    @Override
    protected ArrayList<material> doInBackground(Void... params) {

        JsonMultipart j= new JsonMultipart();
        try {
            String dat = j.getData(new URL(main.getString(R.string.server)+main.getString(R.string.getmateriales)),username,password);
            Log.e("aqui",dat);
            if(dat!=null)
                return material.listMaterial(dat);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
