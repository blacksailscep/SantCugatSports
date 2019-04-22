package com.sancugat.cepsoft.santcugatsports.Entities;

import java.util.ArrayList;

public class Equipo {

    private int id;
    private String nombre;
    private int id_sport;
    private int id_competicion;
    private int id_categoria_edad;
    private int id_nivel;
    private int id_sexo;
    private int id_identidad;
    private String foto;

    private ArrayList<Act_concedida> lstAct_Concedidas;

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

    public int getId_sport() {
        return id_sport;
    }

    public void setId_sport(int id_sport) {
        this.id_sport = id_sport;
    }

    public int getId_competicion() {
        return id_competicion;
    }

    public void setId_competicion(int id_competicion) {
        this.id_competicion = id_competicion;
    }

    public int getId_categoria_edad() {
        return id_categoria_edad;
    }

    public void setId_categoria_edad(int id_categoria_edad) {
        this.id_categoria_edad = id_categoria_edad;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public int getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(int id_sexo) {
        this.id_sexo = id_sexo;
    }

    public int getId_identidad() {
        return id_identidad;
    }

    public void setId_identidad(int id_identidad) {
        this.id_identidad = id_identidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public ArrayList<Act_concedida> getLstAct_Concedidas() {
        return lstAct_Concedidas;
    }

    public void setLstAct_Concedidas(ArrayList<Act_concedida> lstAct_Concedidas) {
        this.lstAct_Concedidas = lstAct_Concedidas;
    }
}
