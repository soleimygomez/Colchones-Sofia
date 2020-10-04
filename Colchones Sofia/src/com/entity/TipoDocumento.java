package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * Implementation TipoDocumento. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@Table(name="tipo_documento")
@NamedQuery(name="TipoDocumento.findAll", query="SELECT t FROM TipoDocumento t")
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre;

	private boolean estado;

	@OneToMany(mappedBy="tipoDocumentoBean")
	private List<Persona> personas;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public TipoDocumento() {
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	public Persona addPersona(Persona persona) {
		getPersonas().add(persona);
		persona.setTipoDocumentoBean(this);
		return persona;
	}

	public Persona removePersona(Persona persona) {
		getPersonas().remove(persona);
		persona.setTipoDocumentoBean(null);
		return persona;
	}
	
	@Override
	public String toString() {
		return "TipoDocumento [nombre=" + nombre + ", estado=" + estado + "]";
	}

	///////////////////////////////////////////////////////
	// Getter y Setters 
	///////////////////////////////////////////////////////
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
}