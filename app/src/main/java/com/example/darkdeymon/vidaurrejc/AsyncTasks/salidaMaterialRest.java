package com.example.darkdeymon.vidaurrejc.AsyncTasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.darkdeymon.vidaurrejc.Multipart.JsonMultipart;
import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.salidaInsumo;
import com.example.darkdeymon.vidaurrejc.classRest.salidaMaterial;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 28/10/2016.
 */

public class salidaMaterialRest extends AsyncTask<Void,Void,ArrayList<salidaMaterial>> {

    String username;
    String password;
    AppCompatActivity main;
    public salidaMaterialRest(String username, String password, AppCompatActivity main)
    {
        this.username = username;
        this.password = password;
        this.main = main;
    }
    @Override
    protected ArrayList<salidaMaterial> doInBackground(Void... params) {
        JsonMultipart j= new JsonMultipart();
        try {
            String res = j.getData(new URL(main.getString(R.string.server)+main.getString(R.string.getsalidamaterial)),username,password);
            if(res!=null)
                return salidaMaterial.getListSalidaMaterial(res);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
