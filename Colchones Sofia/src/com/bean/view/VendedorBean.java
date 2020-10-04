package com.bean.view;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.entity.*;
import com.dao.*;
import com.util.Face;
import com.util.Fecha;
import com.bean.session.*;

/**
 * Implementation VendedorBean.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@ManagedBean(name = "vendedor")
@ViewScoped
public class VendedorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private FacesMessage message;

	private String estado;
	private int index;
	private boolean insert;
	private boolean search;
	private boolean update;
	private boolean remove; 
	private boolean hidden;
	private boolean error;
	private boolean active;

	private Vendedor vendedor;

	///////////////////////////////////////////////////////
	// Managed
	///////////////////////////////////////////////////////
	@ManagedProperty("#{sesion}")
	private SessionBean sesion;

	@ManagedProperty("#{table}")
	private DataTableBean table;

	@ManagedProperty("#{image}")
	private ImageBean image;

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public VendedorBean() {
	}

	///////////////////////////////////////////////////////
	// Post
	///////////////////////////////////////////////////////
	@PostConstruct
	public void init() {
		initVendedor();
		this.estado = "";
		this.index = -1;
		this.update = false;
		this.error = false;
		this.hidden = false;
		this.insert = false;
		this.remove = false;
		this.search = false;
		this.active = false;
	}

	///////////////////////////////////////////////////////
	// Init
	///////////////////////////////////////////////////////
	/**
	 * Metodo que inicializa el vendedor.
	 */
	public void initVendedor() {
		this.vendedor = new Vendedor();
		this.vendedor.setPersona(new Persona());
		this.vendedor.getPersona().setTipoDocumentoBean(new TipoDocumento());
	}

	/**
	 * Metodo que limpia el filtro de la tabla.
	 */
	public void initTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('sofia-table-update').clearFilters());");
	}

	/**
	 * Metodo que cambia el estado a dialogo de formulario.
	 * 
	 * @param estado representa el estado.
	 */
	public void initDialogForm(int estado) {
		PrimeFaces current = PrimeFaces.current();
		switch (estado) {
		case 1:
			current.executeScript("PF('sofia-dialog-update').show();");
			break;
		case 2:
			current.executeScript("PF('sofia-dialog-update').hide();");
			break;
		default:
			break;
		}
	}

	///////////////////////////////////////////////////////
	// Metodo
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite conocer un vendedor por su indice en la tabla.
	 */
	public void vendedor() {
		this.message = null;
		this.error = true;
		this.index = -1;
		String documento = Face.get("documento-vendedor");
		String tipo_documento = Face.get("tipo-documento-vendedor");
		if (documento != null && documento.length() > 0 && tipo_documento != null && tipo_documento.length() > 0) {
			this.index = index(Integer.parseInt(documento), tipo_documento);
			if (this.index >= 0 && this.table.getVendedor() != null && this.index < this.table.getVendedor().size()) {
				this.vendedor = this.table.getVendedor().get(this.index);
				if (this.vendedor != null) {
					this.error = false;
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"El vendedor no se encuentra registrado.");
				}
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El vendedor no se encuentra registrado.");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No se ha seleccionado ningun vendedor.");
		}
	}

	/**
	 * Metodo que permite buscar en una lista el indice de un vendedor.
	 * 
	 * @param documento representa el documento vendedor.
	 * @param tipo      representa el tipo documento vendedor.
	 * @return representa el indice del vendedor en la lista.
	 */
	public int index(int documento, String tipo) {
		int aux = 0;
		for (Vendedor v : this.table.getVendedor()) {
			if (v.getDocumento() == documento) {
				if (v.getPersona().getTipoDocumentoBean().getNombre().equals(tipo)) {
					return aux;
				}
			}
			aux++;
		}
		return -1;
	}

	/**
	 * Metodo que permite filtrar los datos de una persona por su documento.
	 */
	public void filtrar() {
		this.search = false;
		this.error = true;
		this.active = false;
		if (this.vendedor != null && this.vendedor.getDocumento() > 0) {
			int documento = this.vendedor.getDocumento();
			String tipo_documento = this.vendedor.getPersona().getTipoDocumentoBean().getNombre();
			VendedorDao dao = new VendedorDao();
			this.vendedor = dao.find(this.vendedor.getDocumento());
			if (this.vendedor == null) {
				PersonaDao pDao = new PersonaDao();
				this.vendedor = new Vendedor();
				this.vendedor.setDocumento(documento);
				this.vendedor.setPersona(pDao.find(documento));
				if (this.vendedor.getPersona() != null) {
					if (this.vendedor.getPersona().getTipoDocumentoBean().getNombre().equals(tipo_documento)) {
						message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
								"Se han encontrado los datos de la persona este documento " + documento + ".");
						this.search = true;
						this.error = false;
						this.active = true;
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"No se han encontrado la persona con este documento " + documento + ".");
					}
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
							"No se han encontrado los datos de la persona con este documento " + documento + ".");
				}
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Ya esta registrado un vendedor con ese documento " + documento + ".");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El campo documento es obligatorio.");
		}

		if (this.error) {
			initVendedor();
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Metodo que permite activar el formulario.
	 */
	public void activar() {
		if (!this.active) {
			this.active = true;
		} else {
			this.active = false;
		}
	}

	/**
	 * Metodo que desactiva los datos del proveedor.
	 */
	public void desactivar() {
		if (this.search) {
			this.initVendedor();
			this.search = false;
			this.error = false;
		}
	}

	///////////////////////////////////////////////////////
	// CRUD
	///////////////////////////////////////////////////////
	/**
	 * Metodo que registra un vendedor.
	 */
	public void registrar() {
		FacesContext.getCurrentInstance().addMessage(null, faceRegistrarVendedor());
	}

	/**
	 * Metodo que permite actualizar un vendedor.
	 */
	public void actualizar() {
		FacesContext.getCurrentInstance().addMessage(null, faceActualizarVendedor());
	}

	/**
	 * Metodo que permite eliminar un vendedor.
	 */
	public void eliminar() {
		FacesContext.getCurrentInstance().addMessage(null, faceEliminarVendedor());
	}

	/**
	 * Metodo que cambia el valor del estado del vendedor.
	 */
	@SuppressWarnings("deprecation")
	public void estado() {
		this.update = false;
		this.vendedor();
		if (!this.error && this.index >= 0) {
			VendedorDao dao = new VendedorDao();
			boolean estado = vendedor.getEstado();
			estado = (estado) ? false : true;
			this.vendedor.setEstado(estado);
			Fecha fecha = new Fecha();
			this.vendedor.setFechaActualizacion(new Date(fecha.fecha()));
			this.table.getVendedor().set(this.index, this.vendedor);
			this.update = true;
			dao.update(this.vendedor);
			this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Se ha cambiado el estado al vendedor con documento " + vendedor.getDocumento() + " a estado"
							+ ((estado) ? " Activo." : " Bloqueado."));
			// Init
			initTable();
			this.index = -1;
			this.initVendedor();
		}
		FacesContext.getCurrentInstance().addMessage(null, this.message);
	}

	///////////////////////////////////////////////////////
	// Validator
	///////////////////////////////////////////////////////
	/**
	 * Metodo que valida los datos del vendedor.
	 * 
	 * @param v Representa el vendedor
	 * @param u Representa el usuario logeado.
	 */
	public FacesMessage validar(Vendedor v, Usuario u) {
		this.message = null;
		this.error = true;
		if (u != null && u.getDocumento() > 0) {
			if (v != null && v.getDocumento() > 0) {
				if (v.getPersona() != null) {
					Persona p = v.getPersona();
					if (p.getNombre() != null && p.getNombre().length() > 0) {
						if (p.getApellido() != null && p.getApellido().length() > 0) {
							if (p.getDireccion() != null && p.getDireccion().length() > 0) {
								if (p.getEmail() != null && p.getEmail().length() > 0) {
									if (p.getTelefono() != null && p.getTelefono().length() > 0) {
										if (p.getNacimiento() != null) {
											if (p.getSexo() != null && p.getSexo().length() > 0) {
												this.error = false;
												return null;
											} else {
												message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
														"El sexo es obligatorio.");
											}
										} else {
											message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
													"La fecha nacimiento es obligatoria.");
										}
									} else {
										message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
												"El telefono es obligatorio.");
									}
								} else {
									message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
											"El email es obligatorio.");
								}
							} else {
								message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
										"La dirección es obligatorio.");
							}
						} else {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El apellido es obligatorio.");
						}
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El nombre es obligatorio.");
					}
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Los datos personales son obligatorios.");
				}
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El documento vendedor es obligatorio.");
			}

		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No te has logeado.");
		}
		return message;
	}

	///////////////////////////////////////////////////////
	// Statu
	///////////////////////////////////////////////////////
	/**
	 * Metodo que obtiene el estato del vendedor a registrar.
	 */
	public void statuRegistrar() {
		if (this.update) {
			this.initVendedor();
			this.update = false;
		}
		this.initDialogForm(1);
		this.hidden = false;
		this.estado = "Registrar";
	}

	/**
	 * Metodo que obtiene el estato del vendedor a actualizar.
	 */
	public void statuActualizar() {
		this.update = true;
		this.hidden = true;
		initVendedor();
		this.message = null;
		this.error = true;
		this.vendedor();
		if (!this.error && this.index >= 0) {
			this.estado = "Actualizar";
			if (this.vendedor.getDocumento() > 0) {
				this.error = false;
				this.initDialogForm(1);
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"El documento " + this.vendedor.getDocumento() + " del vendedor no es valido.");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha obtenido el documento vendedor.");
		}
		if (message != null) {
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * Metodo que enviado al metodo correspondiente el estado.
	 */
	public void status() {
		switch (estado) {
		case "Actualizar":
			this.actualizar();
			if (this.update && !this.error) {
				initDialogForm(0);
			} else {
				initDialogForm(1);
			}
			break;
		case "Registrar":
			this.registrar();
			if (this.insert && !this.error) {
				initDialogForm(0);
			} else {
				initDialogForm(1);
			}
			break;
		default:
			break;
		}
	}

	///////////////////////////////////////////////////////
	// FACE
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite insertar el vendedor.
	 * 
	 * @return representa el mensaje obtenido.
	 */
	@SuppressWarnings({ "deprecation" })
	private FacesMessage faceRegistrarVendedor() {
		this.message = validar(this.vendedor, sesion.getLogeado());
		this.insert = false;
		if (message == null) {
			this.error = true;
			PersonaDao dao = new PersonaDao();
			Persona p = dao.find(this.vendedor.getDocumento());
			if (p != null) {
				this.search = true;
				this.update = true;
				Fecha fecha = new Fecha();
				if(image.getImage() != null) {
					p.setFoto(image.getImage());
				}
				p.setFechaActualizada(new Date(fecha.fecha()));
				p.setApellido(p.getApellido().toUpperCase());
				p.setNombre(p.getNombre().toUpperCase());
				dao.update(p);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Se ha actualizado los datos de la persona con documento " + p.getDocumento() + "."));
			} else {
				p = this.vendedor.getPersona();
				p.setApellido(p.getApellido().toUpperCase());
				p.setNombre(p.getNombre().toUpperCase());
				p.setDocumento(this.vendedor.getDocumento());
				if(image.getImage() != null) {
					p.setFoto(image.getImage());
				}
				Fecha fecha = new Fecha();
				p.setFechaCreacion(new Date(fecha.fecha()));
				dao.insert(p);
				p = dao.find(this.vendedor.getDocumento());
				if (p != null) {
					this.insert = true;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
							"Se ha registrado la persona con documento " + p.getDocumento() + "."));
				}
			}
			this.image.setImage(null);
			this.insert = false;
			this.error = true;
			if (p != null) {
				this.vendedor.setPersona(p);
				this.vendedor.setDocumento(p.getDocumento());
				this.vendedor.setEstado(true);
				Fecha fecha = new Fecha();
				this.vendedor.setFechaRegistro(new Date(fecha.fecha()));
				this.vendedor.setUsuario(this.sesion.getLogeado());
				VendedorDao vDao = new VendedorDao();
				vDao.insert(this.vendedor);
				this.insert = true;
				this.active = false;
				this.search = false;
				this.error = false;
				this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Se ha registrado el vendedor con documento " + this.vendedor.getDocumento() + ".");
				this.table.initVendedor();
				this.initVendedor();
			} else {
				this.message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha registrado el vendedor.");
			}
		}
		return message;
	}

	/**
	 * Metodo que permite actualizar un vendedor.
	 * 
	 * @return representa el mensage generado.
	 */
	@SuppressWarnings("deprecation")

	private FacesMessage faceActualizarVendedor() {
		this.message = validar(this.vendedor, sesion.getLogeado());
		this.update = false;
		if (!this.error && this.index >= 0) {
			this.error = true;
			this.vendedor = table.getVendedor().get(this.index);
			if (this.vendedor != null) {
				VendedorDao dao = new VendedorDao();
				Fecha fecha = new Fecha();
				this.vendedor.setFechaActualizacion(new Date(fecha.fecha()));
				this.vendedor.setUsuario(this.sesion.getLogeado());
				this.vendedor.getPersona().setApellido(this.vendedor.getPersona().getApellido().toUpperCase());
				this.vendedor.getPersona().setNombre(this.vendedor.getPersona().getNombre().toUpperCase());
				if(image.getImage() != null) {
					this.vendedor.getPersona().setFoto(this.image.getImage());
				}
				this.table.getVendedor().set(this.index, this.vendedor);
				dao.update(this.vendedor);
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Se ha actualizado el vendedor con documento " + this.vendedor.getDocumento() + ".");
				this.update = true;
				this.error = false;
				this.image.setImage(null);
				initVendedor();
				this.index = -1;
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"No existe ningun vendedor con ese documento " + this.vendedor.getDocumento() + ".");
			}
		}
		return message;
	}

	/**
	 * Metodo que permite eliminar un vendedor.
	 * 
	 * @return representa el mensage generado.
	 */
	private FacesMessage faceEliminarVendedor() {
		this.remove = false;
		this.vendedor();
		if (!this.error && this.index >= 0) {
			this.error = true;
			if (this.vendedor.getVentas() != null && this.vendedor.getVentas().size() == 0) {
				VendedorDao dao = new VendedorDao();
				dao.delete(this.vendedor);
				this.table.getVendedor().remove(this.index);
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Se ha eliminado el vendedor documento " + vendedor.getDocumento() + ".");
				this.remove = true;
				this.error = false;
				initVendedor();
				this.index = -1;
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"No se ha podido eliminar al vendedor con documento " + this.vendedor.getDocumento()
								+ ",esta asociado a " + this.vendedor.getVentas().size() + " ventas.");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El vendedor no fue encontrado.");
		}

		return message;
	}

	///////////////////////////////////////////////////////
	// Getter y Setters
	///////////////////////////////////////////////////////

	public FacesMessage getMessage() {
		return message;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public DataTableBean getTable() {
		return table;
	}

	public void setTable(DataTableBean table) {
		this.table = table;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ImageBean getImage() {
		return image;
	}

	public void setImage(ImageBean image) {
		this.image = image;
	}
}
