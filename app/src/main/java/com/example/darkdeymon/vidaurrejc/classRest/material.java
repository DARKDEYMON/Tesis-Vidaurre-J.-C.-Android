package com.example.darkdeymon.vidaurrejc.classRest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 22/10/2016.
 */

public class material {
    String id;
    String decripcion;
    String unidad;
    String observaciones;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public static ArrayList<material> listMaterial(String json){
        Gson g= new Gson();
        Type t= new TypeToken<ArrayList<material>>(){}.getType();
        return g.fromJson(json,t);
    }
}
