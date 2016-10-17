package com.example.darkdeymon.vidaurrejc.classRest;

import com.google.gson.Gson;

/**
 * Created by DARKDEYMON on 15/10/2016.
 */

public class usuario {
    String id;
    String username;
    String first_name;
    String last_name;
    String email;
    boolean is_staff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean is_staff() {
        return is_staff;
    }

    public void setIs_staff(boolean is_staff) {
        this.is_staff = is_staff;
    }

    public static usuario getUsuario(String json)
    {
        Gson g = new Gson();
        return g.fromJson(json,usuario.class);
    }
}
