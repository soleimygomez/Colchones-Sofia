package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int documento;

	private String clave;

	@Column(name="email_confirmacion")
	private String emailConfirmacion;

	private boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_sesion")
	private Date fechaSesion;

	@Lob
	private byte[] foto;

	private boolean session;

	@Column(name="ultima_clave")
	private String ultimaClave;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ultima_session")
	private Date ultimaSession;

	//bi-directional many-to-one association to Proveedor
	@OneToMany(mappedBy="usuario")
	private List<Proveedor> proveedors;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="documento")
	private Persona persona;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="rol")
	private Rol rolBean;

	public Usuario() {
	}

	@Override
	public String toString() {
		return "Usuario [documento=" + documento + ", estado=" + estado + "]";
	}





	public int getDocumento() {
		return this.documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmailConfirmacion() {
		return this.emailConfirmacion;
	}

	public void setEmailConfirmacion(String emailConfirmacion) {
		this.emailConfirmacion = emailConfirmacion;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaSesion() {
		return this.fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public boolean getSession() {
		return this.session;
	}

	public void setSession(boolean session) {
		this.session = session;
	}

	public String getUltimaClave() {
		return this.ultimaClave;
	}

	public void setUltimaClave(String ultimaClave) {
		this.ultimaClave = ultimaClave;
	}

	public Date getUltimaSession() {
		return this.ultimaSession;
	}

	public void setUltimaSession(Date ultimaSession) {
		this.ultimaSession = ultimaSession;
	}

	public List<Proveedor> getProveedors() {
		return this.proveedors;
	}

	public void setProveedors(List<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}

	public Proveedor addProveedor(Proveedor proveedor) {
		getProveedors().add(proveedor);
		proveedor.setUsuario(this);

		return proveedor;
	}

	public Proveedor removeProveedor(Proveedor proveedor) {
		getProveedors().remove(proveedor);
		proveedor.setUsuario(null); 

		return proveedor;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Rol getRolBean() {
		return this.rolBean;
	}

	public void setRolBean(Rol rolBean) {
		this.rolBean = rolBean;
	}

}