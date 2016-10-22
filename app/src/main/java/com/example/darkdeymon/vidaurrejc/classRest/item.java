package com.example.darkdeymon.vidaurrejc.classRest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 16/10/2016.
 */

public class item {
    String id;
    String proyecto_name;
    String descripcion;
    String fecha_inicio;
    String plazo_finalizacion;
    String unidad;
    String cantidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProyecto_name() {
        return proyecto_name;
    }

    public void setProyecto_name(String proyecto_name) {
        this.proyecto_name = proyecto_name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getPlazo_finalizacion() {
        return plazo_finalizacion;
    }

    public void setPlazo_finalizacion(String plazo_finalizacion) {
        this.plazo_finalizacion = plazo_finalizacion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    public static ArrayList<item> getListItem(String json){
        Gson g= new Gson();
        Type t= new TypeToken<ArrayList<item>>() {}.getType();
        return g.fromJson(json,t);
    }
    public static String getItemJson(item i){
        Gson g= new Gson();
        return g.toJson(i);
    }
    public static item getJsonItem(String i){
        Gson g= new Gson();
        return g.fromJson(i,item.class);
    }
}
