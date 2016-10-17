package com.example.darkdeymon.vidaurrejc.AsyncTasks;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.darkdeymon.vidaurrejc.Multipart.JsonMultipart;
import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.usuario;

import java.io.IOException;
import java.net.URL;

/**
 * Created by DARKDEYMON on 15/10/2016.
 */

public class loginRest extends AsyncTask <Void,Integer,Boolean>{

    String username;
    String password;
    AppCompatActivity main;
    public loginRest(String username, String password, AppCompatActivity main)
    {
        this.username = username;
        this.password = password;
        this.main = main;
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            String url = main.getString(R.string.server)+main.getString(R.string.userdata);
            Log.e("valores",username+":"+password);
            //MultipartUtility multi = new MultipartUtility(url,username,password,"UTF-8");
            JsonMultipart j = new JsonMultipart();

            String a = j.getData(new URL(url),username,password);
            if (a==null)
                return false;
            //List a =multi.finish();
            Log.e("recupero",a.toString());
            usuario u = usuario.getUsuario(a);

            saveAcces();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPreExecute() {
        //LoginActivity pr =(LoginActivity)main;
        //pr.showProgress(true);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        //aqui cuando acabe
        //LoginActivity pr =(LoginActivity)main;
        //pr.showProgress(false);
        super.onPostExecute(aBoolean);
    }

    public void saveAcces()
    {
        AccesData acceso = new AccesData();
        acceso.setUsername(username);
        acceso.setPassword(password);

        SharedPreferences preferences = main.getSharedPreferences(StaticValues.GeneralPreferences,main.MODE_PRIVATE);
        //SharedPreferences preferences = main.getPreferences(main.MODE_PRIVATE);
        Editor prefsEditor = preferences.edit();
        prefsEditor.putString(StaticValues.SharedPreferencesAuth,AccesData.AccesDataJson(acceso));
        prefsEditor.commit();
    }
}
