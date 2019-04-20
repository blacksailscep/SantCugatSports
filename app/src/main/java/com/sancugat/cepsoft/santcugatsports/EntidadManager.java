package com.sancugat.cepsoft.santcugatsports;

import com.sancugat.cepsoft.santcugatsports.Entities.Entidad;


public class EntidadManager
{

    private static Entidad entidad;

    public static Entidad getEntidad() {
        return entidad;
    }

    public static void setEntidad(Entidad entidad) {
        EntidadManager.entidad = entidad;
    }
}
