package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * Implementation EstadoVenta.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@Table(name = "estado_venta")
@NamedQuery(name = "EstadoVenta.findAll", query = "SELECT e FROM EstadoVenta e")
public class EstadoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String estado;

	///////////////////////////////////////////////////////
	// Map
	///////////////////////////////////////////////////////
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@ManyToOne
	@JoinColumn(name = "id_venta")
	private Venta venta;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public EstadoVenta() {
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "EstadoVenta [id=" + id + ", estado=" + estado + ", usuario=" + usuario + "]";
	}

	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}