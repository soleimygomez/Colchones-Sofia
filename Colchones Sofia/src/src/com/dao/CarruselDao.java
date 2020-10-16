package com.dao;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation CarruselDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class CarruselDao extends Conexion<Carrusel> implements Interface<Carrusel> {

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public CarruselDao() {
		super(Carrusel.class);
	}
}
