package com.bean.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import com.dao.*;
import com.entity.*;

/**
 * Implementation SelectionBean.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@ManagedBean(name = "selection")
@RequestScoped
public class SelectionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<SelectItem> roles;
	private List<SelectItem> documentos;
	private List<SelectItem> categorias;

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public SelectionBean() {
	}

	///////////////////////////////////////////////////////
	// Post
	///////////////////////////////////////////////////////
	@PostConstruct
	public void init() {
		this.roles = null;
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	
	/**
	 * Metodo que permite traer todos los tipos de roles de la empresa.
	 * 
	 * @return una lista con los roles de la empresa.
	 */
	public List<SelectItem> getRoles() {
		this.roles = new ArrayList<SelectItem>();
		SelectItemGroup s = new SelectItemGroup("Tipo Usuario");
		RolDao dao = new RolDao();
		List<Rol> roles = dao.list();
		SelectItem[] items = new SelectItem[roles.size()];
		for (int i = 0; i < roles.size(); i++) {
			items[i] = new SelectItem(String.valueOf(roles.get(i).getNombre()),
					String.valueOf(roles.get(i).getNombre()));
		}
		s.setSelectItems(items);
		this.roles.add(s);
		return this.roles;
	}
	
	/**
	 * Metodo que permite traer todos los tipos de documentos de la empresa.
	 * @return una lista con todos los tipod de documentos.
	 */
	public List<SelectItem> getDocumentos() {
		TipoDocumentoDao dao= new TipoDocumentoDao();
		List<TipoDocumento> p =dao.findByFieldList("estado", true);
		SelectItemGroup g1 = new SelectItemGroup("Tipo Documento");
		SelectItem[] items = new SelectItem[p.size()];
		for (int i = 0; i < p.size(); i++) {
			TipoDocumento tipo= p.get(i);
			items[i] = new SelectItem(String.valueOf(tipo.getNombre()), String.valueOf(tipo.getNombre()));
		}
		 this.documentos= new ArrayList<SelectItem>();
		g1.setSelectItems(items);
	     this.documentos.add(g1);
		return documentos;
	} 
	
	/**
	 * Metodo que permita traer todas las categorias.
	 * @return una lista con las categorias y sus productos.
	 */
	public List<SelectItem> getCategorias() {
		this.categorias= new ArrayList<SelectItem>();
		CategoriaDao dao= new CategoriaDao();
		List<Categoria> list= dao.findByFieldList("estado", true);
		for (Categoria categoria: list) {
			SelectItemGroup select = new SelectItemGroup(categoria.getNombre());
			SelectItem[] items = new SelectItem[categoria.getProductos().size()];
			int i=0;
			for(Producto p: categoria.getProductos()) {
				items[i]= new SelectItem(String.valueOf(p.getIdProducto()), String.valueOf(p.getNombre()));
				i++;
			}
			select.setSelectItems(items);
			this.categorias.add(select);
		}
		return categorias;
	}
	
	///////////////////////////////////////////////////////
	// Getter y Setters
	///////////////////////////////////////////////////////
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}

	public void setRoles(List<SelectItem> roles) {
		this.roles = roles;
	}
	
	public void setDocumentos(List<SelectItem> documentos) {
		this.documentos = documentos;
	}
}
