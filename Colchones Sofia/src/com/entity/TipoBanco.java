package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * Implementation TipoBanco. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@Table(name="tipo_banco")
@NamedQuery(name="TipoBanco.findAll", query="SELECT t FROM TipoBanco t")
public class TipoBanco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String banco;
	private String cuenta;
	
	///////////////////////////////////////////////////////
	// Map
	///////////////////////////////////////////////////////
	@OneToMany(mappedBy="tipoBanco")
	private List<TipoPago> tipoPagos;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public TipoBanco() {
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "TipoBanco [id=" + id + ", banco=" + banco + ", cuenta=" + cuenta + "]";
	}

	public TipoPago addTipoPago(TipoPago tipoPago) {
		getTipoPagos().add(tipoPago);
		tipoPago.setTipoBanco(this);
		return tipoPago;
	}

	public TipoPago removeTipoPago(TipoPago tipoPago) {
		getTipoPagos().remove(tipoPago);
		tipoPago.setTipoBanco(null);
		return tipoPago;
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

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public List<TipoPago> getTipoPagos() {
		return this.tipoPagos;
	}

	public void setTipoPagos(List<TipoPago> tipoPagos) {
		this.tipoPagos = tipoPagos;
	}
}