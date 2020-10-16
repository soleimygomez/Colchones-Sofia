package com.dao;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation RolDao. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class RolDao 
	   extends Conexion<Rol> 
       implements Interface<Rol> {

	public RolDao() {
		super(Rol.class);
	}
}