package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;

/**
 * Implementation DetalleCompra.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@Table(name = "detalle_compra")
@NamedQuery(name = "DetalleCompra.findAll", query = "SELECT d FROM DetalleCompra d")
public class DetalleCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int cantidad;
	private String descripcion;
	private BigInteger descuento;
	private BigInteger precio;

	///////////////////////////////////////////////////////
	// Map
	///////////////////////////////////////////////////////
	@ManyToOne
	@JoinColumn(name = "id_compra")
	private Compra compra;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private DetalleProducto detalleProducto;

	@ManyToOne
	@JoinColumn(name = "id_proveedor")
	private Proveedor proveedor;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public DetalleCompra() {
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "DetalleCompra [id=" + id + ", cantidad=" + cantidad + ", descuento=" + descuento + ", precio=" + precio
				+ "]";
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

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigInteger getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigInteger descuento) {
		this.descuento = descuento;
	}

	public BigInteger getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigInteger precio) {
		this.precio = precio;
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