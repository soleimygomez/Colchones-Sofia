package com.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the informacion database table.
 * 
 */
@Entity
@NamedQuery(name="Informacion.findAll", query="SELECT i FROM Informacion i")
public class Informacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	@Lob
	private byte[] foto;

	public Informacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}