package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * Implementation Producto. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int idProducto;

	@Lob
	@Column(name="codigo_barras")
	private byte[] codigoBarras;

	@Lob
	private byte[] foto;
	private String descripcion;
	private boolean estado;
	private byte garantia;
	private String nombre;
	private int stock;

	@OneToMany(mappedBy="producto")
	private List<DetalleProducto> detalleProductos;

	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;

	@OneToMany(mappedBy="producto")
	private List<ProveedorProducto> proveedorProductos;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Producto() {
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", estado=" + estado + ", nombre=" + nombre + ", stock=" + stock
				+ ", categoria=" + categoria + "]";
	}
	
	public ProveedorProducto addProveedorProducto(ProveedorProducto proveedorProducto) {
		getProveedorProductos().add(proveedorProducto);
		proveedorProducto.setProducto(this);

		return proveedorProducto;
	}

	public ProveedorProducto removeProveedorProducto(ProveedorProducto proveedorProducto) {
		getProveedorProductos().remove(proveedorProducto);
		proveedorProducto.setProducto(null);

		return proveedorProducto;
	}
	
	public DetalleProducto addDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().add(detalleProducto);
		detalleProducto.setProducto(this);

		return detalleProducto;
	}

	public DetalleProducto removeDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().remove(detalleProducto);
		detalleProducto.setProducto(null);

		return detalleProducto;
	}
	
	///////////////////////////////////////////////////////
	// Getter y Setters 
	///////////////////////////////////////////////////////
	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public byte[] getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(byte[] codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public byte getGarantia() {
		return this.garantia;
	}

	public void setGarantia(byte garantia) {
		this.garantia = garantia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<DetalleProducto> getDetalleProductos() {
		return this.detalleProductos;
	}

	public void setDetalleProductos(List<DetalleProducto> detalleProductos) {
		this.detalleProductos = detalleProductos;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<ProveedorProducto> getProveedorProductos() {
		return this.proveedorProductos;
	}

	public void setProveedorProductos(List<ProveedorProducto> proveedorProductos) {
		this.proveedorProductos = proveedorProductos;
	}
}