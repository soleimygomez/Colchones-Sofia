package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the detalle_producto database table.
 * 
 */
@Entity
@Table(name="detalle_producto")
@NamedQuery(name="DetalleProducto.findAll", query="SELECT d FROM DetalleProducto d")
public class DetalleProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String color;

	@Lob
	private byte[] foto;

	private String medida;

	@Column(name="precio_costo")
	private double precioCosto;

	@Column(name="precio_venta")
	private double precioVenta;

	private int stock;

	//bi-directional many-to-one association to DetalleCompra
	@OneToMany(mappedBy="detalleProducto")
	private List<DetalleCompra> detalleCompras;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to DetalleVenta
	@OneToMany(mappedBy="detalleProducto")
	private List<DetalleVenta> detalleVentas;

	public DetalleProducto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getMedida() {
		return this.medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public double getPrecioCosto() {
		return this.precioCosto;
	}

	public void setPrecioCosto(double precioCosto) {
		this.precioCosto = precioCosto;
	}

	public double getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<DetalleCompra> getDetalleCompras() {
		return this.detalleCompras;
	}

	public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}

	public DetalleCompra addDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().add(detalleCompra);
		detalleCompra.setDetalleProducto(this);

		return detalleCompra;
	}

	public DetalleCompra removeDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().remove(detalleCompra);
		detalleCompra.setDetalleProducto(null);

		return detalleCompra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<DetalleVenta> getDetalleVentas() {
		return this.detalleVentas;
	}

	public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}

	public DetalleVenta addDetalleVenta(DetalleVenta detalleVenta) {
		getDetalleVentas().add(detalleVenta);
		detalleVenta.setDetalleProducto(this);

		return detalleVenta;
	}

	public DetalleVenta removeDetalleVenta(DetalleVenta detalleVenta) {
		getDetalleVentas().remove(detalleVenta);
		detalleVenta.setDetalleProducto(null);

		return detalleVenta;
	}

}