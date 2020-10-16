package com.dao;

import java.util.List;

import javax.persistence.Query;

import com.entity.*;
import com.util.*;

/**
 * Implementation CategoriaDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class CategoriaDao extends Conexion<Categoria> implements Interface<Categoria> {

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public CategoriaDao() {
		super(Categoria.class);
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite traer la ultima categoria insertada.
	 * @return representa la ultima categoria insertada.
	 */
	@SuppressWarnings("unchecked")
	public Categoria ultimoAdd() {
		Query query = getEm().createQuery("FROM Categoria ORDER BY id DESC");
		List<Categoria> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}