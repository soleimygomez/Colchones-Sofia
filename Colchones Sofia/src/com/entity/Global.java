package com.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the global database table.
 * 
 */
@Entity
@NamedQuery(name="Global.findAll", query="SELECT g FROM Global g")
public class Global implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String nombre;

	private String descripcion;

	@Lob
	private byte[] logo;

	@Lob
	private byte[] portada;

	public Global() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public byte[] getPortada() {
		return this.portada;
	}

	public void setPortada(byte[] portada) {
		this.portada = portada;
	}

}