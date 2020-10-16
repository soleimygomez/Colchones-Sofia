package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;

/**
 * Implementation Cliente. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_compra")
	private int idCompra;

	private String descripcion;
	private BigInteger descuento;
	private BigInteger total;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	///////////////////////////////////////////////////////
	// Map
	///////////////////////////////////////////////////////
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;

	@OneToMany(mappedBy="compra")
	private List<DetalleCompra> detalleCompras;

	@OneToMany(mappedBy="compra")
	private List<HistorialPresupuesto> historial;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Compra() {
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", descripcion=" + descripcion + ", descuento=" + descuento + ", total="
				+ total + "]";
	}
	
	public DetalleCompra addDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().add(detalleCompra);
		detalleCompra.setCompra(this);

		return detalleCompra;
	}

	public DetalleCompra removeDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().remove(detalleCompra);
		detalleCompra.setCompra(null);

		return detalleCompra;
	}
	
	public HistorialPresupuesto addHistorialPresupuesto(HistorialPresupuesto historialPresupuesto) {
		getHistorial().add(historialPresupuesto);
		historialPresupuesto.setCompra(this);

		return historialPresupuesto;
	}

	public HistorialPresupuesto removeHistorial(HistorialPresupuesto historial) {
		getHistorial().remove(historial);
		historial.setCompra(null);
		return historial;
	}
	
	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
	public List<HistorialPresupuesto> getHistorial() {
		return this.historial;
	}

	public void setHistorial(List<HistorialPresupuesto> historial) {
		this.historial= historial;
	}
	
	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
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

	public BigInteger getTotal() {
		return this.total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleCompra> getDetalleCompras() {
		return this.detalleCompras;
	}

	public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}
}