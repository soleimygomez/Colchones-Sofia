package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * Implementation Persona. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int documento;
	
	private String nombre;
	private String apellido;
	private String direccion;
	private String sexo;
	private String telefono;
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_actualizada")
	private Date fechaActualizada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Lob
	private byte[] foto;

	@Temporal(TemporalType.DATE)
	private Date nacimiento;

	@OneToOne(mappedBy="persona")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name="tipo_documento")
	private TipoDocumento tipoDocumentoBean;

	@OneToOne(mappedBy="persona")
	private Usuario usuario;

	@OneToOne(mappedBy="persona")
	private Vendedor vendedor;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Persona() {
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	
	@Override
	public String toString() {
		return "Persona [documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	///////////////////////////////////////////////////////
	// Getter y Setters 
	///////////////////////////////////////////////////////
	public int getDocumento() {
		return this.documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaActualizada() {
		return this.fechaActualizada;
	}

	public void setFechaActualizada(Date fechaActualizada) {
		this.fechaActualizada = fechaActualizada;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Date getNacimiento() {
		return this.nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoDocumento getTipoDocumentoBean() {
		return this.tipoDocumentoBean;
	}

	public void setTipoDocumentoBean(TipoDocumento tipoDocumentoBean) {
		this.tipoDocumentoBean = tipoDocumentoBean;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}