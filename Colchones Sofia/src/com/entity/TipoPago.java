package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_pago database table.
 * 
 */
@Entity
@Table(name="tipo_pago")
@NamedQuery(name="TipoPago.findAll", query="SELECT t FROM TipoPago t")
public class TipoPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String nombre;

	private boolean estado;

	//bi-directional many-to-one association to TipoBanco
	@ManyToOne
	@JoinColumn(name="tipo_banco")
	private TipoBanco tipoBancoBean;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="tipoPago")
	private List<Venta> ventas;

	public TipoPago() {
	}

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

	public TipoBanco getTipoBancoBean() {
		return this.tipoBancoBean;
	}

	public void setTipoBancoBean(TipoBanco tipoBancoBean) {
		this.tipoBancoBean = tipoBancoBean;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
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

}