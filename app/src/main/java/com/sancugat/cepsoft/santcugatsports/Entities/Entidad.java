package com.sancugat.cepsoft.santcugatsports.Entities;

import java.util.ArrayList;
import java.util.List;

public class Entidad
{
    private ArrayList<Equipo> lstEquipos;
    private ArrayList<Telefonos> lstTelefonos;

    private int id;
    private String nombre;
    private  String correo;
    private String contrasenya;
    private int temporada;
    private String direccion;
    private String nif;
    private String foto;


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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Equipo> getLstEquipos() {
        return lstEquipos;
    }

    public void setLstEquipos(ArrayList<Equipo> lstEquipos) {
        this.lstEquipos = lstEquipos;
    }

    public List<Telefonos> getLstTelefonos() {
        return lstTelefonos;
    }

    public void setLstTelefonos(ArrayList<Telefonos> lstTelefonos) {
        this.lstTelefonos = lstTelefonos;
    }
}
