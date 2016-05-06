package com.herprogramacion.uml.ahorcado_uml;

import android.content.Context;

import java.util.Random;


public class GeneradorPalabras  {

    private Context context;
    private int numPalabras;
    private String[]palabras;

    public  GeneradorPalabras(Context context, int recurso){
        this.context = context;
        this.palabras = context.getResources().getStringArray(recurso);
        this.numPalabras = this.palabras.length;
    }

    public String generarPalabra(){

        int index = new Random().nextInt(this.numPalabras);
        return this.palabras[index];
    }

}
