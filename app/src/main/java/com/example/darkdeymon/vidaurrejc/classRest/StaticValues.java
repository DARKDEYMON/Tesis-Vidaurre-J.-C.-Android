package com.example.darkdeymon.vidaurrejc.classRest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by DARKDEYMON on 15/10/2016.
 */

public class StaticValues {
    public static String GeneralPreferences = "GeneralPreferences";
    public static String SharedPreferencesAuth = "SharedPreferencesAuth";

    public static AccesData getLogetInfo(AppCompatActivity act){

        SharedPreferences mPrefs = act.getSharedPreferences(StaticValues.GeneralPreferences,act.MODE_PRIVATE);

        String json = mPrefs.getString(StaticValues.SharedPreferencesAuth, "");
        Log.e("hay algo 2","hola"+json);
        if(json.compareTo("")==0)
            return null;
        return AccesData.getData(json);
    }
}