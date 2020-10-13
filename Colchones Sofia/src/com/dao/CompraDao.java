package com.dao;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation CompraDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class CompraDao extends Conexion<Compra> implements Interface<Compra> {

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public CompraDao() {
		super(Compra.class);
	}
}
