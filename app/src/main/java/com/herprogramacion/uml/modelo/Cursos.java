package com.herprogramacion.uml.modelo;

import android.support.v7.app.AppCompatActivity;

import com.herprogramacion.uml.R;

import java.util.ArrayList;
import java.util.List;


public class Cursos extends AppCompatActivity{
    //declaracion de las variables que llenaran nuestra vista
    private String curso;
    private String eslogan;
    private int idDrawable;

    //el cursor se encarga de posicionar cada elemento es su lugar
    public Cursos(String curso, String eslogan, int idDrawable) {
        this.curso = curso;
        this.eslogan = eslogan;
        this.idDrawable = idDrawable;
    }
    //Lista de cursos, imagenes que se muestran el inicio de la aplicacion
    public static  final List<Cursos> CURSOS = new ArrayList<Cursos>();



    /**
     * Nombre y descripcion de otros cursos en inicio de la aplicacion se podran descargar
     */
    static{
        CURSOS.add(new Cursos("UML","Casos de Uso", R.drawable.casouso));
        CURSOS.add(new Cursos("UML","Diagrama de Actividades", R.drawable.diaact));
        CURSOS.add(new Cursos("UML","Diagrama de Estado", R.drawable.diaest));
        CURSOS.add(new Cursos("UML","Diagrama de Interaccion", R.drawable.diaint));


    }

    //Getters y Setters
    public String getCurso() {
        return curso;
    }

    public String getEslogan() {
        return eslogan;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
}


