package com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Implementation Venta. 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@Entity
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_venta")
	private int idVenta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private double total;

	@OneToMany(mappedBy="venta")
	private List<DetalleVenta> detalleVentas;

	@OneToMany(mappedBy="venta")
	private List<DevolucionGarantia> devolucionGarantias;

	@OneToMany(mappedBy="venta")
	private List<EstadoVenta> estadoVentas;

	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name="id_pago")
	private TipoPago tipoPago;

	@ManyToOne
	@JoinColumn(name="id_vendedor")
	private Vendedor vendedor;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public Venta() {
	}
	
	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	public EstadoVenta addEstadoVenta(EstadoVenta estadoVenta) {
		getEstadoVentas().add(estadoVenta);
		estadoVenta.setVenta(this);

		return estadoVenta;
	}

	public EstadoVenta removeEstadoVenta(EstadoVenta estadoVenta) {
		getEstadoVentas().remove(estadoVenta);
		estadoVenta.setVenta(null);

		return estadoVenta;
	}
	
	public DevolucionGarantia addDevolucionGarantia(DevolucionGarantia devolucionGarantia) {
		getDevolucionGarantias().add(devolucionGarantia);
		devolucionGarantia.setVenta(this);

		return devolucionGarantia;
	}

	public DevolucionGarantia removeDevolucionGarantia(DevolucionGarantia devolucionGarantia) {
		getDevolucionGarantias().remove(devolucionGarantia);
		devolucionGarantia.setVenta(null);

		return devolucionGarantia;
	}
	
	public DetalleVenta addDetalleVenta(DetalleVenta detalleVenta) {
		getDetalleVentas().add(detalleVenta);
		detalleVenta.setVenta(this);

		return detalleVenta;
	}

	public DetalleVenta removeDetalleVenta(DetalleVenta detalleVenta) {
		getDetalleVentas().remove(detalleVenta);
		detalleVenta.setVenta(null);

		return detalleVenta;
	}
	
	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", total=" + total + ", cliente=" + cliente + ", tipoPago=" + tipoPago
				+ ", vendedor=" + vendedor + "]";
	}

	///////////////////////////////////////////////////////
	// Getter y Setters 
	///////////////////////////////////////////////////////
	public int getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<DetalleVenta> getDetalleVentas() {
		return this.detalleVentas;
	}

	public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}

	public List<DevolucionGarantia> getDevolucionGarantias() {
		return this.devolucionGarantias;
	}

	public void setDevolucionGarantias(List<DevolucionGarantia> devolucionGarantias) {
		this.devolucionGarantias = devolucionGarantias;
	}

	public List<EstadoVenta> getEstadoVentas() {
		return this.estadoVentas;
	}

	public void setEstadoVentas(List<EstadoVenta> estadoVentas) {
		this.estadoVentas = estadoVentas;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoPago getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}