package com.dao;

import com.entity.*;
import com.util.Conexion;


/**
 * Implementation TelefonoDao. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class TelefonoDao 
       extends Conexion<Telefono>
	   implements Interface<Telefono>{
	
	public TelefonoDao() {
		super(Telefono.class);
	}
}
