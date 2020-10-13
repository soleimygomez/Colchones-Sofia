package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * Implementation Persona.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int documento;

	private String nombre;
	private String apellido;
	private String direccion;
	private String genero;
	private String email;
	private String telefono;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizada")
	private Date fechaActualizada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Lob
	private byte[] foto;

	@Temporal(TemporalType.DATE)
	private Date nacimiento;

	///////////////////////////////////////////////////////
	// Map
	///////////////////////////////////////////////////////
	@OneToOne(mappedBy = "persona")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "tipo_documento")
	private TipoDocumento tipoDocumento;

	@OneToOne(mappedBy = "persona")
	private Usuario usuario;

	@OneToOne(mappedBy = "persona")
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
		return "Persona [documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido + ", genero="
				+ genero + ", email=" + email + ", telefono=" + telefono + "]";
	}

	///////////////////////////////////////////////////////
	// Getter and Setters
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

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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