package com.dao;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation DetalleProductoDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class DetalleProductoDao extends Conexion<DetalleProducto> implements Interface<DetalleProducto> {

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public DetalleProductoDao() {
		super(DetalleProducto.class);
	}
}
