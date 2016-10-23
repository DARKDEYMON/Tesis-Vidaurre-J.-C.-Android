package com.example.darkdeymon.vidaurrejc.classRest;

import com.google.gson.Gson;

/**
 * Created by DARKDEYMON on 22/10/2016.
 */

public class sendReqInsumo {
    String item;
    String insumos;
    String cantidad;
    String precio_estimado_total;
    String observaciones;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getInsumos() {
        return insumos;
    }

    public void setInsumos(String insumos) {
        this.insumos = insumos;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio_estimado_total() {
        return precio_estimado_total;
    }

    public void setPrecio_estimado_total(String precio_estimado_total) {
        this.precio_estimado_total = precio_estimado_total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public static String reqInsumoToJson(sendReqInsumo i){
        Gson g= new Gson();
        return g.toJson(i);
    }
}
