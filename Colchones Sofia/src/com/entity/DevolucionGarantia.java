package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the devolucion_garantia database table.
 * 
 */
@Entity
@Table(name="devolucion_garantia")
@NamedQuery(name="DevolucionGarantia.findAll", query="SELECT d FROM DevolucionGarantia d")
public class DevolucionGarantia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="id_venta")
	private Venta venta;

	public DevolucionGarantia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}