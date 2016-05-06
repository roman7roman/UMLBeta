package com.herprogramacion.uml.quiz;


public class Tema {

    private int  idTema;
    private String nombre;
    private int idUsuario;
    private boolean activo;
    private int calificacion;

    public Tema() {
        idTema = 0;
        nombre = "";
        idUsuario = 0;
        activo = false;
        calificacion = 0;
    }

    public Tema(String nombre, int idUsuario, boolean activo, int calificacion) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.activo = activo;
        this.calificacion = calificacion;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "idTema=" + idTema +
                ", nombre='" + nombre + '\'' +
                ", idUsuario=" + idUsuario +
                ", activo=" + activo +
                ", calificacion=" + calificacion +
                '}';
    }
}
