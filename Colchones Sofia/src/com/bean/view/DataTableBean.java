package com.bean.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.entity.*;
import com.dao.*;

/**
 * Implementation DataTableBean.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@ManagedBean(name = "table")
@ViewScoped
public class DataTableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Vendedor> vendedor;
	private List<Vendedor> filter_vendedor;
	private int renderizar_vendedor;

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public DataTableBean() {
	}

	///////////////////////////////////////////////////////
	// Post
	///////////////////////////////////////////////////////
	@PostConstruct
	public void init() {
		this.renderizar_vendedor = 0;
	}

	///////////////////////////////////////////////////////
	// Init
	///////////////////////////////////////////////////////
	/**
	 * Metodo que inicializa los valores de la tabla vendedor.
	 */
	public void initVendedor() {
		this.vendedor = vendedores();
		List<Vendedor> aux = vendedores();
		this.vendedor = new ArrayList<Vendedor>();
		for (Vendedor v : aux) {
			VentaDao dao = new VentaDao();
			v.setVentas(dao.findByFieldList("vendedor", v));
			this.vendedor.add(v);
		}
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Metodo que lista todas los vendedores.
	 * 
	 * @return una lista con los vendedores.
	 */
	public List<Vendedor> vendedores() {
		VendedorDao dao = new VendedorDao();
		return dao.list();
	}

	///////////////////////////////////////////////////////
	// Renderizar
	///////////////////////////////////////////////////////
	/**
	 * Renderizando la tabla vendedor.
	 * 
	 * @return una lista nueva.
	 */
	public List<Vendedor> getVendedor() {
		if (this.renderizar_vendedor == 0) {
			initVendedor();
			this.renderizar_vendedor = 1;
		}
		return vendedor;
	}

	///////////////////////////////////////////////////////
	// Getter y Setters
	///////////////////////////////////////////////////////
	public List<Vendedor> getFilter_vendedor() {
		return filter_vendedor;
	}

	public void setFilter_vendedor(List<Vendedor> filter_vendedor) {
		this.filter_vendedor = filter_vendedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setVendedor(List<Vendedor> vendedor) {
		this.vendedor = vendedor;
	}
}
