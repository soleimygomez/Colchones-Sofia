package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;

/**
 * Implementation Cliente.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@NamedQuery(name = "Global.findAll", query = "SELECT g FROM Global g")
public class Global implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String nombre;

	private String descripcion;
	private String direccion;
	private String nit;
	private BigInteger presupuesto;

	@Lob
	private byte[] logo;

	@Lob
	private byte[] portada;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Global() {
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "Global [nombre=" + nombre + ", descripcion=" + descripcion + ", direccion=" + direccion + ", nit=" + nit
				+ ", presupuesto=" + presupuesto + "]";
	}

	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
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

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public byte[] getPortada() {
		return this.portada;
	}

	public void setPortada(byte[] portada) {
		this.portada = portada;
	}

	public BigInteger getPresupuesto() {
		return this.presupuesto;
	}

	public void setPresupuesto(BigInteger presupuesto) {
		this.presupuesto = presupuesto;
	}
}