package com.bean.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.bean.session.*;
import com.entity.*;
import com.entity.other.*;
import com.dao.*;
import com.util.*;

/**
 * Implementation ProveedorBean.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@ManagedBean(name = "proveedor")
@ViewScoped
public class ProveedorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private FacesMessage message;

	private String estado;

	private int index;
	private int id;

	private boolean insert;
	private boolean search;
	private boolean update;
	private boolean remove;
	private boolean hidden;
	private boolean error;
	private boolean active;

	private String[] select;

	private Proveedor proveedor;

	private List<Producto> seleccionadas;
	private List<Producto> productos;

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
	public ProveedorBean() {
	}

	///////////////////////////////////////////////////////
	// Post
	///////////////////////////////////////////////////////
	@PostConstruct
	public void init() {
		initProveedor();
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
	 * Metodo que inicializa el proveedor.
	 */
	public void initProveedor() {
		this.proveedor = new Proveedor();
		generar();
		this.select = null;
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
	 * Metodo que permite generar un id del proveedor.
	 */
	public void generar() {
		ProveedorDao dao = new ProveedorDao();
		Proveedor p = dao.ultimoAdd();
		this.id = -1;
		if (p != null) {
			this.id = p.getIdProveedor() + 1;
		}
	}

	/**
	 * Metodo que permite conocer un proveedor por su indice en la tabla.
	 */
	public void proveedor() {
		this.message = null;
		this.error = true;
		this.index = -1;
		String id = Face.get("id-proveedor");
		if (id != null && id.length() > 0) {
			this.index = index(Integer.parseInt(id));
			if (this.index >= 0 && this.table.getProveedor() != null && this.index < this.table.getProveedor().size()) {
				this.proveedor = this.table.getProveedor().get(this.index);
				if (this.proveedor != null) {
					this.error = false;
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"El proveedor no se encuentra registrado.");
				}
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El proveedor no se encuentra registrado.");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No se ha seleccionado ningun proveedor.");
		}
	}

	/**
	 * Metodo que permite buscar en una lista el indice de un proveedor.
	 * 
	 * @param id representa el id del proveedor.
	 * @return representa el indice del proveedor en la lista.
	 */
	public int index(int id) {
		int aux = 0;
		for (Proveedor p : this.table.getProveedor()) {
			if (p.getIdProveedor() == id) {
				return aux;
			}
			aux++;
		}
		return -1;
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
			this.initProveedor();
			this.search = false;
			this.error = false;
		}
	}

	///////////////////////////////////////////////////////
	// CRUD
	///////////////////////////////////////////////////////
	/**
	 * Metodo que registra un proveedor.
	 */
	public void registrar() {
		FacesContext.getCurrentInstance().addMessage(null, faceRegistrarProveedor());
	}

	/**
	 * Metodo que permite actualizar un proveedor.
	 */
	public void actualizar() {
		FacesContext.getCurrentInstance().addMessage(null, faceActualizarProveedor());
	}

	/**
	 * Metodo que permite eliminar un proveedor.
	 */
	public void eliminar() {
		FacesContext.getCurrentInstance().addMessage(null, faceEliminarProveedor());
	}

	/**
	 * Metodo que activa el dialogo de productos de un proveedor en especifico.
	 */
	public void productos() {
		PrimeFaces current = PrimeFaces.current();
		this.proveedor();
		if (proveedor != null) {
			current.executeScript("PF('sofia-dialog-product-update').show();");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No has seleccionado ningun proveedor."));
		}
	}

	/**
	 * Metodo que agrega todos los productos seleccionado a un proveedor.
	 */
	public void addProductos() {
		PrimeFaces current = PrimeFaces.current();
		this.insert = false;
		this.select = null;
		this.message = null;
		if (this.getProveedor() != null && this.getProveedor().getIdProveedor() > 0) {
			if (this.seleccionadas != null && this.seleccionadas.size() > 0) {
				this.select = new String[this.seleccionadas.size()];
				int index = 0;
				for (Producto p : this.seleccionadas) {
					this.select[index] = String.valueOf(p.getIdProducto());
					index++;
				}
				FacesContext.getCurrentInstance().addMessage(null, faceRegistrarProductos(this.proveedor));
				this.table.setRenderizar_proveedor(0);
				this.initProveedor();
				this.id = 0;
				this.error = false;
				this.seleccionadas = new ArrayList<Producto>();
				current.executeScript("PF('sofia-dialog-product-update').hide();");
				return;
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No has seleccionado ningun producto.");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No has seleccionado ningun proveedor");
		}
		current.executeScript("PF('sofia-dialog-product-update').hide();");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Metodo que cambia el valor del estado del proveedor.
	 */
	@SuppressWarnings("deprecation")
	public void estado() {
		this.update = false;
		this.proveedor();
		if (!this.error && this.index >= 0) {
			ProveedorDao dao = new ProveedorDao();
			boolean estado = proveedor.getEstado();
			estado = (estado) ? false : true;
			this.proveedor.setEstado(estado);
			Fecha fecha = new Fecha();
			this.proveedor.setFechaActualizacion(new Date(fecha.fecha()));
			this.proveedor.setUsuario(this.sesion.getLogeado());
			this.table.getProveedor().set(this.index, this.proveedor);
			this.update = true;
			dao.update(this.proveedor);
			this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Se ha cambiado el estado al proveedor con ID " + proveedor.getIdProveedor() + " a estado"
							+ ((estado) ? " Activo." : " Bloqueado."));
			// Init
			initTable();
			this.index = -1;
			this.initProveedor();
		}
		FacesContext.getCurrentInstance().addMessage(null, this.message);
	}

	///////////////////////////////////////////////////////
	// Validator
	///////////////////////////////////////////////////////
	/**
	 * Metodo que valida los datos del proveedor.
	 * 
	 * @param p Representa el proveedor
	 * @param u Representa el usuario logeado.
	 */
	public FacesMessage validar(Proveedor p, Usuario u) {
		this.message = null;
		this.error = true;
		if (u != null && u.getDocumento() > 0) {
			if (p != null && p.getIdProveedor() > 0) {
				if (p.getNombre() != null && p.getNombre().length() > 0) {
					if (p.getTelefono() != null && p.getTelefono().length() > 0) {
						if (p.getDireccion() != null && p.getDireccion().length() > 0) {
							this.error = false;
							return null;
						} else {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
									"El campo dirección es obligatorio.");
						}
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
								"El campo dirección es obligatorio.");
					}
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El campo nombre es obligatorio.");
				}
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El ID proveedor no es valido.");
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
	 * Metodo que obtiene el estato del proveedor a registrar.
	 */
	public void statuRegistrar() {
		this.generar();
		if (this.update) {
			this.initProveedor();
			this.update = false;
		}
		this.initDialogForm(1);
		this.hidden = false;
		this.estado = "Registrar";
	}

	/**
	 * Metodo que obtiene el estato del proveedor a actualizar.
	 */
	public void statuActualizar() {
		this.update = true;
		this.hidden = true;
		this.initProveedor();
		this.message = null;
		this.error = true;
		this.proveedor();
		if (!this.error && this.index >= 0) {
			this.estado = "Actualizar";
			this.id = this.proveedor.getIdProveedor();
			if (this.id > 0) {
				this.error = false;
				this.initDialogForm(1);
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"El ID " + this.id + " del proveedor no es valido.");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha obtenido el ID proveedor.");
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
	// Renderizar
	///////////////////////////////////////////////////////
	/**
	 * Metodo que consulta todos los productos.
	 * 
	 * @return una lista con todos los productos.
	 */
	public List<Producto> getProductos() {
		CategoriaDao dao = new CategoriaDao();
		List<Categoria> categorias = dao.findByFieldList("estado", true);
		this.productos = new ArrayList<Producto>();
		// Proveedor
		ProveedorProductoDao ppDao = new ProveedorProductoDao();
		List<ProveedorProducto> pp = ppDao.findByFieldList("proveedor", this.proveedor);
		for (Categoria c : categorias) {
			for (Producto p : c.getProductos()) {
				if (!existe(pp, p)) {
					this.productos.add(p);
				}
			}
		}
		return productos;
	}

	/**
	 * Metodo que permite verificar si existe un proveedor ya tiene asociado un
	 * producto.
	 * 
	 * @param pp representa el proveedor.
	 * @param p  representa el producto.
	 * @return true si lo tiene asociado false si no.
	 */
	public boolean existe(List<ProveedorProducto> pp, Producto p) {
		for (ProveedorProducto aux : pp) {
			if (aux.getProducto() != null && aux.getProducto().getIdProducto() == p.getIdProducto()) {
				return true;
			}
		}
		return false;
	}

	///////////////////////////////////////////////////////
	// Face
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite insertar el proveedor.
	 * 
	 * @return representa el mensaje obtenido.
	 */
	@SuppressWarnings({ "deprecation" })
	private FacesMessage faceRegistrarProveedor() {
		if (this.proveedor != null && this.id != this.proveedor.getIdProveedor()) {
			this.proveedor.setIdProveedor(this.id);
		}
		this.message = validar(this.proveedor, sesion.getLogeado());
		this.insert = false;
		if (message == null) {
			this.error = true;
			ProveedorDao pDao = new ProveedorDao();
			Proveedor aux = pDao.find(this.proveedor.getIdProveedor());
			if (aux == null) {
				this.proveedor.setNombre(this.proveedor.getNombre().toUpperCase());
				this.proveedor.setTelefono(Convertidor.telefono(this.proveedor.getTelefono()));
				Fecha fecha = new Fecha();
				this.proveedor.setEstado(true);
				this.proveedor.setFechaRegistro(new Date(fecha.fecha()));
				this.proveedor.setUsuario(this.sesion.getLogeado());
				if (this.image.getImage() != null) {
					this.proveedor.setFoto(this.image.getImage());
				}
				// INSERT
				pDao.insert(this.proveedor);
				this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Se ha registrado el proveedor con ID " + this.proveedor.getIdProveedor() + ".");
				FacesContext.getCurrentInstance().addMessage(null, this.message);
				this.message = faceRegistrarProductos(this.proveedor);
				// RESET
				this.image.setImage(null);
				this.insert = true;
				this.active = false;
				this.search = false;
				this.error = false;
				this.table.initProveedor();
				this.initProveedor();
			} else {
				this.message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Ya existe un proveedor con ese ID " + this.id + ".");
			}
		}
		return message;
	}

	/**
	 * Metodo que permite registrar los productos de un proveedor.
	 * 
	 * @param p represenat el proveedor.
	 * @return representa el mensage generado.
	 */
	private FacesMessage faceRegistrarProductos(Proveedor p) {
		this.message = null;
		if (p != null && p.getIdProveedor() > 0) {
			if (select != null && select.length > 0) {
				ProductoDao dDao = new ProductoDao();
				ProveedorProductoDao dao = new ProveedorProductoDao();
				int j = 0;
				for (String aux : select) {
					int id = Integer.parseInt(aux);
					ProveedorProducto pp = new ProveedorProducto();
					pp.setProducto(dDao.find(id));
					pp.setProveedor(p);
					if (pp.getProducto() != null) {
						dao.insert(pp);
						j++;
					}
				}
				if (j == select.length) {
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
							"Todos los productos del proveedor con ID." + p.getIdProveedor() + ",fueron registrados ("
									+ j + "/" + select.length + ",productos).");
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
							"Faltaron algunos productos del proveedor con ID." + p.getIdProveedor()
									+ ", por registrar (" + j + "/" + select.length + ",productos).");
				}
				this.select = null;
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No has seleccionado ningun producto.");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Los campos del proveedor son obligatorios.");
		}
		return message;
	}

	/**
	 * Metodo que permite actualizar un proveedor.
	 * 
	 * @return representa el mensage generado.
	 */
	@SuppressWarnings("deprecation")
	private FacesMessage faceActualizarProveedor() {
		this.message = validar(this.proveedor, sesion.getLogeado());
		this.update = false;
		if (!this.error && this.index >= 0) {
			this.error = true;
			ProveedorDao dao = new ProveedorDao();
			if (this.proveedor != null) {
				Fecha fecha = new Fecha();
				this.proveedor.setNombre(this.proveedor.getNombre().toUpperCase());
				this.proveedor.setTelefono(Convertidor.telefono(this.proveedor.getTelefono()));
				this.proveedor.setFechaActualizacion(new Date(fecha.fecha()));
				this.proveedor.setUsuario(this.sesion.getLogeado());
				if (this.image.getImage() != null) {
					this.proveedor.setFoto(this.image.getImage());
				}
				dao.update(this.proveedor);
				this.table.getProveedor().set(this.index, this.proveedor);
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Se ha actualizado el proveedor con ID." + this.id + ".");
				this.update = true;
				this.error = false;
				this.image.setImage(null);
				this.initProveedor();
				this.index = -1;
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"No existe ningun proveedor con ese ID." + this.id + ".");
			}
		}
		return message;
	}

	/**
	 * Metodo que permite eliminar el proveedor.
	 * 
	 * @return representa el mensage generado.
	 */
	private FacesMessage faceEliminarProveedor() {
		this.remove = false;
		this.proveedor();
		if (!this.error && this.index >= 0) {
			ProveedorDao dao = new ProveedorDao();
			if (this.proveedor != null) {
				if (this.proveedor.getDetalleCompras() != null && this.proveedor.getDetalleCompras().size() == 0) {
					List<ProveedorProducto> list = this.proveedor.getProveedorProductos();
					if (list != null) {
						if (list.size() == 0) {
							dao.delete(this.proveedor);
							this.table.getProveedor().remove(this.index);
							message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
									"Se ha eliminado el proveedor ID. " + proveedor.getIdProveedor() + ".");
							this.remove = true;
							this.error = false;
							initProveedor();
							this.index = -1;
						} else {
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
									"No se ha eliminado el proveedor con ID. " + proveedor.getIdProveedor()
											+ ",primero tienes que eliminar los productos que el tiene asociado que son "
											+ list.size() + " productos.");
						}
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"Se ha presentado un error a listar los productos del proveedor con ID." + this.id
										+ " vuelva a intentarlo.");
					}
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"No se ha podido eliminar al proveedor con ID." + this.proveedor.getIdProveedor()
									+ " porque esta asociado a " + this.proveedor.getDetalleCompras().size()
									+ " detalles de compras.");
				}
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"No existe ningun proveedor con ese ID." + this.id + ".");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"El ID." + this.id + " del proveedor no es valido.");
		}

		return message;
	}

	///////////////////////////////////////////////////////
	// Getter y Setters
	///////////////////////////////////////////////////////

	public FacesMessage getMessage() {
		return message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ImageBean getImage() {
		return image;
	}

	public void setImage(ImageBean image) {
		this.image = image;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String[] getSelect() {
		return select;
	}

	public void setSelect(String[] select) {
		this.select = select;
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

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isActive() {
		return active;
	}

	public List<Producto> getSeleccionadas() {
		return seleccionadas;
	}

	public void setSeleccionadas(List<Producto> seleccionadas) {
		this.seleccionadas = seleccionadas;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
