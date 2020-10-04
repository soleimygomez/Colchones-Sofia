package com.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalle_compra database table.
 * 
 */
@Entity
@Table(name="detalle_compra")
@NamedQuery(name="DetalleCompra.findAll", query="SELECT d FROM DetalleCompra d")
public class DetalleCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private boolean estado;

	@Column(name="precio_costo")
	private double precioCosto;

	private double subtotal;

	//bi-directional many-to-one association to Compra
	@ManyToOne
	@JoinColumn(name="id_compra")
	private Compra compra;

	//bi-directional many-to-one association to DetalleProducto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private DetalleProducto detalleProducto;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	public DetalleCompra() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public double getPrecioCosto() {
		return this.precioCosto;
	}

	public void setPrecioCosto(double precioCosto) {
		this.precioCosto = precioCosto;
	}

	public double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public DetalleProducto getDetalleProducto() {
		return this.detalleProducto;
	}

	public void setDetalleProducto(DetalleProducto detalleProducto) {
		this.detalleProducto = detalleProducto;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}