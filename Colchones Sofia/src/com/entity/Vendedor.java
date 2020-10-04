package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Implementation Vendedor. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@NamedQuery(name="Vendedor.findAll", query="SELECT v FROM Vendedor v")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int documento;

	private boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@OneToOne
	@JoinColumn(name="documento")
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name="usuario ")
	private Usuario usuario;

	@OneToMany(mappedBy="vendedor")
	private List<Venta> ventas;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Vendedor() {
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setVendedor(this);
		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setVendedor(null);
		return venta;
	}
	
	@Override
	public String toString() {
		return "Vendedor [documento=" + documento + ", estado=" + estado + ", persona=" + persona + ", usuario="
				+ usuario + "]";
	}

	///////////////////////////////////////////////////////
	// Getter y Setters 
	///////////////////////////////////////////////////////
	public int getDocumento() {
		return this.documento;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
}