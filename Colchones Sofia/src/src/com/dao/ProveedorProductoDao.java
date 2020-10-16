package com.dao;

import java.util.*;

import javax.persistence.Query;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation ProveedorProductoDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class ProveedorProductoDao extends Conexion<ProveedorProducto> implements Interface<ProveedorProducto> {

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public ProveedorProductoDao() {
		super(ProveedorProducto.class);
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite conocer la categorias de los productos de un proveedor.
	 * @param proveedor representa el proveedor.
	 * @return la lista de productos de este proveedor.
	 */
	@SuppressWarnings("unchecked")
	public List<ProveedorProducto> categorias(int proveedor) {
		Query query = getEm().createQuery("Select p from ProveedorProducto p where p.proveedor.idProveedor=" + proveedor
				+ "GROUP BY p.producto.categoria.id ORDER BY p.producto.categoria.id");
		return query.getResultList();
	} 

	/**
	 * Metodo que permite conocer los productos de un proveedor.
	 * @param proveedor representa el proveedor.
	 * @return la lista de productos de este proveedor.
	 */
	@SuppressWarnings("unchecked")
	public List<ProveedorProducto> pruductos(int proveedor) {
		Query query = getEm().createQuery("Select p from ProveedorProducto p where p.producto.estado=true and p.proveedor.idProveedor=" + proveedor
				+ "ORDER BY p.producto.categoria.id");
		return query.getResultList();
	}

	/**
	 * Metodo que permite traer la categoria y producto del proveedor.
	 * @param proveedor representa el proveedor.
	 * @return una lista con las categorias correspondientes.
	 */
	public List<Categoria> listCategoria(int proveedor) {
		List<Categoria> categorias = new ArrayList<Categoria>();
		List<ProveedorProducto> c = categorias(proveedor);
		List<ProveedorProducto> p = pruductos(proveedor);
		boolean seguir;
		int j=0;
		for (ProveedorProducto cc : c) {
			Categoria categoria= cc.getProducto().getCategoria();
			categoria.setProductos(new ArrayList<Producto>());
			if(categoria.getEstado()) {
				List<Producto> productos = new ArrayList<Producto>();
				seguir= true;
				for(int i=j;  i< p.size() && seguir; i++) {
					ProveedorProducto aux= p.get(i);
					if(categoria.getId() == aux.getProducto().getCategoria().getId()) {
						productos.add(aux.getProducto());
						j++; 
					}else {	
						seguir= false;
					}
				}
				categoria.setProductos(productos);
				categorias.add(categoria);
			}else {
				seguir= true;
				for(int i=j;  i< p.size() && seguir; i++) {
					ProveedorProducto aux= p.get(i);
					if(categoria.getId() == aux.getProducto().getCategoria().getId()) {
						j++; 
					}else {	
						seguir= false;
					}
				}
			}
		}
		return categorias;
	}
}