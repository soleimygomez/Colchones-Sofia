package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Implementation DetalleProducto.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@Table(name = "detalle_producto")
@NamedQuery(name = "DetalleProducto.findAll", query = "SELECT d FROM DetalleProducto d")
public class DetalleProducto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String color;
	private String descripcion;
	private BigInteger descuento;
	private String dimension;
	private int stock;

	@Lob
	private byte[] foto;

	@Column(name = "precio_costo")
	private BigInteger precioCosto;

	@Column(name = "precio_venta")
	private BigInteger precioVenta;

	///////////////////////////////////////////////////////
	// Map
	///////////////////////////////////////////////////////
	@OneToMany(mappedBy = "detalleProducto")
	private List<DetalleCompra> detalleCompras;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	@OneToMany(mappedBy = "detalleProducto")
	private List<DetalleVenta> detalleVentas;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public DetalleProducto() {
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "DetalleProducto [id=" + id + ", color=" + color + ", descripcion=" + descripcion + ", descuento="
				+ descuento + ", dimension=" + dimension + ", stock=" + stock + ", precioCosto=" + precioCosto
				+ ", precioVenta=" + precioVenta + "]";
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

	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
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

	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public BigInteger getPrecioCosto() {
		return this.precioCosto;
	}

	public void setPrecioCosto(BigInteger precioCosto) {
		this.precioCosto = precioCosto;
	}

	public BigInteger getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(BigInteger precioVenta) {
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
}