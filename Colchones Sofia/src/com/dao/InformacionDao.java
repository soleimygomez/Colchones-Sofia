package com.dao;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation InformacionDao. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class InformacionDao 
       extends Conexion<Informacion>
	   implements Interface<Informacion>{
	
	public InformacionDao() {
		super(Informacion.class);
	}
}

