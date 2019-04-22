package com.sancugat.cepsoft.santcugatsports.Entities;

public class Nivel {
    int id;
    String nombre;

    public Nivel() {
    }

    public Nivel(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
