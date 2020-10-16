package com.dao;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation ClienteDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class ClienteDao extends Conexion<Cliente> implements Interface<Cliente> {

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public ClienteDao() {
		super(Cliente.class);
	}
}
