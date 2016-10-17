package com.example.darkdeymon.vidaurrejc.classRest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by DARKDEYMON on 15/10/2016.
 */

public class AccesData {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static AccesData getData(String json){
        Gson n = new Gson();
        return n.fromJson(json,AccesData.class);
    }

    public static String AccesDataJson(AccesData a){
        Gson n = new Gson();
        return n.toJson(a);
    }
}
