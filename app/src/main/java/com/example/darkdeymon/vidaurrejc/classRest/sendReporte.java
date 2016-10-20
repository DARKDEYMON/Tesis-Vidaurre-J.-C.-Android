package com.example.darkdeymon.vidaurrejc.classRest;

import com.google.gson.Gson;

/**
 * Created by DARKDEYMON on 20/10/2016.
 */

public class sendReporte {
    String item;
    String alto;
    String largo;
    String ancho;
    String observaciones;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public static String jsonSendReporte(sendReporte r){
        Gson g= new Gson();
        return g.toJson(r);
    }
}
