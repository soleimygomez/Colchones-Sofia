package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Implementation Producto.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_producto")
	private int idProducto;

	private String descripcion;
	private boolean estado;
	private boolean garantia;
	private String nombre;
	private int stock;

	@Lob
	@Column(name = "codigo_barra")
	private byte[] codigoBarra;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Lob
	private byte[] foto;

	///////////////////////////////////////////////////////
	// Map
	///////////////////////////////////////////////////////
	@OneToMany(mappedBy = "producto")
	private List<DetalleProducto> detalleProductos;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "producto")
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
		return "Producto [idProducto=" + idProducto + ", estado=" + estado + ", garantia=" + garantia + ", nombre="
				+ nombre + ", stock=" + stock + ", categoria=" + categoria + "]";
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

	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public byte[] getCodigoBarra() {
		return this.codigoBarra;
	}

	public void setCodigoBarra(byte[] codigoBarra) {
		this.codigoBarra = codigoBarra;
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

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public boolean getGarantia() {
		return this.garantia;
	}

	public void setGarantia(boolean garantia) {
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ProveedorProducto> getProveedorProductos() {
		return this.proveedorProductos;
	}

	public void setProveedorProductos(List<ProveedorProducto> proveedorProductos) {
		this.proveedorProductos = proveedorProductos;
	}
}