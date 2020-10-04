package com.dao;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation ProductoDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class ProductoDao extends Conexion<Producto> implements Interface<Producto> {
	
	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public ProductoDao() {
		super(Producto.class);
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
}