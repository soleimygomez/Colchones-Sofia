package com.dao;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation EmailDao. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class EmailDao 
	   extends Conexion<Email> 
       implements Interface<Email> {

	public EmailDao() {
		super(Email.class);
	}
}
