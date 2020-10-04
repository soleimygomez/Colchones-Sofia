package com.dao;

import java.util.List;

import javax.persistence.Query;

import com.entity.*;
import com.util.Conexion;

/**
 * Implementation ProveedorDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class ProveedorDao extends Conexion<Proveedor> implements Interface<Proveedor> {
	
	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public ProveedorDao() {
		super(Proveedor.class);
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite conocer el ultimo proveedor agregado.
	 * @return representa el ultimo proveedor agregado.
	 */
	@SuppressWarnings("unchecked")
	public Proveedor ultimoAdd() {
		Query query = getEm().createQuery("FROM Proveedor ORDER BY idProveedor DESC");
		List<Proveedor> list= query.getResultList();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}	
}