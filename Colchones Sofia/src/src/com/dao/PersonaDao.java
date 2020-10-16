package com.dao;

import com.entity.*;
import com.util.Conexion;


/**
 * Implementation PersonaDao. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class PersonaDao 
	   extends Conexion<Persona> 
       implements Interface<Persona> {

	public PersonaDao() {
		super(Persona.class);
	}
}