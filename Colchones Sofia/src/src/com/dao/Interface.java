package com.dao;

import java.util.List;

/**
 * Implementation Interface.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public interface Interface<T> {

	public List<T> list();

	public <E> T find(E id);

	public void insert(T o);

	public void update(T o);

	public void delete(T o);
}
