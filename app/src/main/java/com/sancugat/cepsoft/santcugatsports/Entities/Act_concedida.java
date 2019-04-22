package com.sancugat.cepsoft.santcugatsports.Entities;

public class Act_concedida {

    private int id;
    private String nombre;
    private int id_tipo;
    private int id_equipo;
    private int id_act_demandadas;
    private int id_espacio;

	public Act_concedida()
	{
	}

	public Act_concedida(int id, String nombre, int id_tipo, int id_equipo, int id_act_demandadas, int id_espacio)
	{
		this.id = id;
		this.nombre = nombre;
		this.id_tipo = id_tipo;
		this.id_equipo = id_equipo;
		this.id_act_demandadas = id_act_demandadas;
		this.id_espacio = id_espacio;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getId_tipo()
	{
		return id_tipo;
	}

	public void setId_tipo(int id_tipo)
	{
		this.id_tipo = id_tipo;
	}

	public int getId_equipo()
	{
		return id_equipo;
	}

	public void setId_equipo(int id_equipo)
	{
		this.id_equipo = id_equipo;
	}

	public int getId_act_demandadas()
	{
		return id_act_demandadas;
	}

	public void setId_act_demandadas(int id_act_demandadas)
	{
		this.id_act_demandadas = id_act_demandadas;
	}

	public int getId_espacio()
	{
		return id_espacio;
	}

	public void setId_espacio(int id_espacio)
	{
		this.id_espacio = id_espacio;
	}
}
