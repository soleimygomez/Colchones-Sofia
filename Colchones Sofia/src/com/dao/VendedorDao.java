package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import com.entity.Vendedor;
import com.util.Conexion;

import com.entity.other.*;

/**
 * Implementation VendedorDao.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class VendedorDao extends Conexion<Vendedor> implements Interface<Vendedor> {

	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public VendedorDao() {
		super(Vendedor.class);
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite conocer cuantos vendedores hay por genero.
	 * 
	 * @return una lista con el resultado.
	 */
	public List<ChartJS> cantidadGenero() {
		String sql = "SELECT pp.genero AS genero, COUNT(pp.genero) FROM Vendedor p JOIN Persona pp ON (p.documento = pp.documento) GROUP BY pp.genero";
		Query query = getEm().createQuery(sql);
		@SuppressWarnings("rawtypes")
		List result = query.getResultList();
		List<ChartJS> c = new ArrayList<ChartJS>();
		@SuppressWarnings("rawtypes")
		Iterator res = result.iterator();
		while (res.hasNext()) {
			Object[] tupla = (Object[]) res.next();
			ChartJS cc = new ChartJS();
			cc.setNombre(String.valueOf(tupla[0]));
			cc.setCantidad(Integer.parseInt(String.valueOf(tupla[1])));
			c.add(cc);
		}
		return c;
	}
}
