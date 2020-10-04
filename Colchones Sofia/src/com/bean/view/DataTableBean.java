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
	
	private List<Proveedor> proveedor;
	private List<Proveedor> filter_proveedor;
	private int renderizar_proveedor;

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
		this.renderizar_proveedor = 0;
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
	
	/**
	 * Metodo que inicializa los valores de la tabla proveedor. 
	 */
	public void initProveedor() {
		this.proveedor = proveedores();
		List<Proveedor> aux= new ArrayList<Proveedor>();
		for(Proveedor p: this.proveedor) {
			List<ProveedorProducto> productos= new ArrayList<ProveedorProducto>();
			ProveedorProductoDao dao= new ProveedorProductoDao();
			productos= dao.findByFieldList("proveedor", p);
			p.setProveedorProductos(productos);
			aux.add(p);
		}
		this.proveedor = aux;
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
	
	/**
	 * Metodo que obtine todos los proveedores.
	 * @return una lista con todos los proveedores.
	 */
	public List<Proveedor> proveedores() {
		ProveedorDao dao= new ProveedorDao();
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
	
	/**
	 * Renderizando la tabla proveedores.
	 * @return una lista nueva.
	 */
	public List<Proveedor> getProveedor() {
		if(this.renderizar_proveedor == 0) {
			initProveedor();
			this.renderizar_proveedor = 1;
		}
		return proveedor;
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

	public int getRenderizar_vendedor() {
		return renderizar_vendedor;
	}

	public void setRenderizar_vendedor(int renderizar_vendedor) {
		this.renderizar_vendedor = renderizar_vendedor;
	}

	public void setProveedor(List<Proveedor> proveedor) {
		this.proveedor = proveedor;
	}

	public List<Proveedor> getFilter_proveedor() {
		return filter_proveedor;
	}

	public void setFilter_proveedor(List<Proveedor> filter_proveedor) {
		this.filter_proveedor = filter_proveedor;
	}

	public int getRenderizar_proveedor() {
		return renderizar_proveedor;
	}

	public void setRenderizar_proveedor(int renderizar_proveedor) {
		this.renderizar_proveedor = renderizar_proveedor;
	}
}
