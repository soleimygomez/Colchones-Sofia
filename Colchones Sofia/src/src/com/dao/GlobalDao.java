package com.dao;

import com.entity.*;
import com.util.Conexion;
/**
 * Implementation GlobalDao. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class GlobalDao 
       extends Conexion<Global>
	   implements Interface<Global>{
	
	public GlobalDao() {
		super(Global.class);
	}
}
