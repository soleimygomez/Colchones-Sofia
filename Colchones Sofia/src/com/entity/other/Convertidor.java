package com.entity.other;

import java.io.Serializable;

/**
 * Implementation Convertidor.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
public class Convertidor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Convertidor() {	
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Metodo que retorna el genero de una persona.
	 * @param nombre representa el genero.
	 * @return  el nombre del genero.
	 */
	public static String genero(String genero) {
		String aux = "";
		switch (genero) {
		case "F":
			aux = "Femenino";
			break;
		case "M":
			aux = "Masculino";
			break;
		default:
			aux = "Otro";
			break;
		}
		return aux;
	}

	///////////////////////////////////////////////////////
	// Getter y Setter
	///////////////////////////////////////////////////////
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
