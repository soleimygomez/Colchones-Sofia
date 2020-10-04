package com.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the estado_venta database table.
 * 
 */
@Entity
@Table(name="estado_venta")
@NamedQuery(name="EstadoVenta.findAll", query="SELECT e FROM EstadoVenta e")
public class EstadoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private char[] estado;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="id_venta")
	private Venta venta;

	public EstadoVenta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char[] getEstado() {
		return this.estado;
	}

	public void setEstado(char[] estado) {
		this.estado = estado;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}