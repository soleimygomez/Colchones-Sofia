package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * Implementation Carrusel.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@Table(name = "tipo_pago")
@NamedQuery(name = "TipoPago.findAll", query = "SELECT t FROM TipoPago t")
public class TipoPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String nombre;

	private boolean estado;

	///////////////////////////////////////////////////////
	// Map
	///////////////////////////////////////////////////////
	@ManyToOne
	@JoinColumn(name = "tipo_banco")
	private TipoBanco tipoBanco;

	@OneToMany(mappedBy = "tipoPago")
	private List<Venta> ventas;

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public TipoPago() {
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "TipoPago [nombre=" + nombre + ", estado=" + estado + ", tipoBanco=" + tipoBanco + "]";
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setTipoPago(this);
		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setTipoPago(null);
		return venta;
	}
	
	///////////////////////////////////////////////////////
	// Getter and Setters 
	///////////////////////////////////////////////////////
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public TipoBanco getTipoBanco() {
		return this.tipoBanco;
	}

	public void setTipoBanco(TipoBanco tipoBanco) {
		this.tipoBanco = tipoBanco;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
}