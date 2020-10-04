package com.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the proveedor_producto database table.
 * 
 */
@Entity
@Table(name="proveedor_producto")
@NamedQuery(name="ProveedorProducto.findAll", query="SELECT p FROM ProveedorProducto p")
public class ProveedorProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	public ProveedorProducto() {
	}

	
	
	@Override
	public String toString() {
		return "ProveedorProducto [id=" + id + ", producto=" + producto + ", proveedor=" + proveedor + "]";
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}