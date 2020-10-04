package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_banco database table.
 * 
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

	//bi-directional many-to-one association to TipoPago
	@OneToMany(mappedBy="tipoBancoBean")
	private List<TipoPago> tipoPagos;

	public TipoBanco() {
	}

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

	public TipoPago addTipoPago(TipoPago tipoPago) {
		getTipoPagos().add(tipoPago);
		tipoPago.setTipoBancoBean(this);

		return tipoPago;
	}

	public TipoPago removeTipoPago(TipoPago tipoPago) {
		getTipoPagos().remove(tipoPago);
		tipoPago.setTipoBancoBean(null);

		return tipoPago;
	}

}