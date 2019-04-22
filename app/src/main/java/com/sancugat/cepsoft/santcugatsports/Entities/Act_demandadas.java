package com.sancugat.cepsoft.santcugatsports.Entities;

import java.sql.Time;

public class Act_demandadas
{

	private int id;
	private String nombre;
	private int id_tipo;
	private int id_espacio;
	private int id_equipo;
	private Time durada;
	private int dias;
	private String asignada;


	public Act_demandadas()
	{
	}

	public Act_demandadas(int id, String nombre, int id_tipo, int id_espacio, int id_equipo, Time durada, int dias, String asignada)
	{
		this.id = id;
		this.nombre = nombre;
		this.id_tipo = id_tipo;
		this.id_espacio = id_espacio;
		this.id_equipo = id_equipo;
		this.durada = durada;
		this.dias = dias;
		this.asignada = asignada;
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

	public int getId_espacio()
	{
		return id_espacio;
	}

	public void setId_espacio(int id_espacio)
	{
		this.id_espacio = id_espacio;
	}

	public int getId_equipo()
	{
		return id_equipo;
	}

	public void setId_equipo(int id_equipo)
	{
		this.id_equipo = id_equipo;
	}

	public Time getDurada()
	{
		return durada;
	}

	public void setDurada(Time durada)
	{
		this.durada = durada;
	}

	public int getDias()
	{
		return dias;
	}

	public void setDias(int dias)
	{
		this.dias = dias;
	}

	public String getAsignada()
	{
		return asignada;
	}

	public void setAsignada(String asignada)
	{
		this.asignada = asignada;
	}
}
