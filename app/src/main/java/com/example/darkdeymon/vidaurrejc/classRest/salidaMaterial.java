package com.example.darkdeymon.vidaurrejc.classRest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 28/10/2016.
 */

public class salidaMaterial {
    String id;
    String almacen;
    String material;
    String item;
    Boolean confirmado;
    String fecha;
    String cantidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    public static ArrayList<salidaMaterial> getListSalidaMaterial(String json){
        Gson g= new Gson();
        Type t= new TypeToken<ArrayList<salidaMaterial>>(){}.getType();
        return g.fromJson(json,t);
    }
    public static String getJsonSalidaMaterial(salidaMaterial s){
        Gson g= new Gson();
        return g.toJson(s);
    }
    public static salidaMaterial getSalidaMaterialFromJson (String  json){
        Gson g= new Gson();
        return g.fromJson(json,salidaMaterial.class);
    }
}
