package com.example.darkdeymon.vidaurrejc.AsyncTasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.darkdeymon.vidaurrejc.Multipart.JsonMultipart;
import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.item;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 16/10/2016.
 */

public class itemRest extends AsyncTask<Void,Void,ArrayList<item>> {

    String username;
    String password;
    AppCompatActivity main;
    public itemRest(String username, String password, AppCompatActivity main)
    {
        this.username = username;
        this.password = password;
        this.main = main;
    }
    @Override
    protected ArrayList<item> doInBackground(Void... params) {
        try {
            String url = main.getString(R.string.server)+main.getString(R.string.useritem);
            Log.e("valores",username+":"+password);
            //MultipartUtility multi = new MultipartUtility(url,username,password,"UTF-8");
            JsonMultipart j = new JsonMultipart();
            String a = null;

            a = j.getData(new URL(url),username,password);
            if(a!=null)
                return item.getListItem(a);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
