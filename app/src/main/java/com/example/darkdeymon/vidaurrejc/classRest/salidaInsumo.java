package com.example.darkdeymon.vidaurrejc.classRest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 27/10/2016.
 */

public class salidaInsumo {
    String id;
    String almacen;
    String insumos;
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

    public String getInsumos() {
        return insumos;
    }

    public void setInsumos(String insumos) {
        this.insumos = insumos;
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
    public static ArrayList<salidaInsumo> getListSalidaInsumo(String json){
        Gson g= new Gson();
        Type t= new TypeToken<ArrayList<salidaInsumo>>(){}.getType();
        return g.fromJson(json,t);
    }
    public static String getJsonSalidaInsumo(salidaInsumo s){
        Gson g= new Gson();
        return g.toJson(s);
    }
    public static salidaInsumo getSalidaInsumoFromJson (String  json){
        Gson g= new Gson();
        return g.fromJson(json,salidaInsumo.class);
    }
}
