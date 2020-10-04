package com.bean.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.dao.*;
import com.entity.*;
import com.util.Face;

/**
 * Implementation ImageBean.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@ManagedBean(name = "image")
@SessionScoped
public class ImageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private FacesMessage mensaje;

	private byte image[];

	private StreamedContent stream;
	private StreamedContent logo;
	private StreamedContent carrousel;
	
	private StreamedContent vendedor;
	private StreamedContent proveedor;
	
	///////////////////////////////////////////////////////
	// Managed Bean
	///////////////////////////////////////////////////////
	@ManagedProperty("#{app}")
	private AppBean app;

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public ImageBean() {
	}

	///////////////////////////////////////////////////////
	// Post
	///////////////////////////////////////////////////////
	@PostConstruct
	public void init() {
		this.image = null;
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	/**
	 * Metodo que obtiene el logo de la empresa.
	 * 
	 * @return retorna el logo de la empresa.
	 * @throws IOException representa el logo de la empresa.
	 */
	public StreamedContent getLogo() throws IOException {
		if (app != null && app.getApp() != null) {
			Global e = null;
			if (app.getApp().getGlobal() != null) {
				e = app.getApp().getGlobal();
				image(e.getLogo());
			} else {
				this.stream = null;
			}
		} else {
			this.stream = null;
		}
		this.logo = this.stream;
		return this.logo;
	}

	/**
	 * Metodo que retorna las fotos del carrosusel.
	 * 
	 * @return representa la foto del carrosusel.
	 */
	@SuppressWarnings("deprecation")
	public StreamedContent getCarrousel() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
			this.carrousel = new DefaultStreamedContent();
		else {
			InformacionDao dao = new InformacionDao();
			int id = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("id"));
			Informacion i = dao.find(id);
			if (i != null) {
				this.carrousel = new DefaultStreamedContent(new ByteArrayInputStream(i.getFoto()), "image/png");
			} else {
				this.carrousel = null;
			}
		}
		return carrousel;
	}

	/**
	 * Metodo que permite leer una image.
	 * 
	 * @param input representa la image.
	 * @return la imagen en bytes.
	 * @throws IOException representa el error.
	 */
	public byte[] leer(InputStream input) throws IOException {
		byte[] byteArray = null;
		InputStream is = input;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];
		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		byteArray = buffer.toByteArray();
		return byteArray;
	}

	/**
	 * Metodo que prepara la imagen.
	 * 
	 * @param event representa la imagen.
	 */
	@SuppressWarnings("unused")
	public void uploadImage(FileUploadEvent event) {
		UploadedFile image = event.getFile();
		boolean cambiar = false;
		byte aux[] = null;
		if (this.image != null) {
			cambiar = true;
			aux = this.image;
		}
		this.image = null;
		try {
			this.image = this.leer(image.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		FacesMessage message = null;
		if (image != null && cambiar) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
					event.getFile().getFileName() + ", se ha cambiado la foto.");
		} else if (image != null && !cambiar) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
					event.getFile().getFileName() + ", foto valida (tamaño " + this.image.length + ").");
		} else if (image == null && cambiar) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "No se ha cambiado la foto.");
			if (aux != null && aux.length > 0) {
				FacesContext.getCurrentInstance().addMessage(null, message);
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Se ha dejado la foto anterior.");
				this.image = aux;
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "No has seleccionado ninguna imagen.");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	///////////////////////////////////////////////////////
	// Generic
	///////////////////////////////////////////////////////
	@SuppressWarnings("deprecation")
	public StreamedContent image(byte[] image) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
			this.stream = new DefaultStreamedContent();
		else {
			if (image != null) {
				this.stream = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/png");
			} else {
				this.stream = null;
			}
		}
		return this.stream;
	}

	///////////////////////////////////////////////////////
	// Renderizar
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite traer la imagen del vendedor.
	 * 
	 * @return representa la imagen del vendedor.
	 */
	@SuppressWarnings({ "deprecation" })
	public StreamedContent getVendedor() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
			this.vendedor = new DefaultStreamedContent();
		else {
			int documento = Integer.parseInt(Face.get("documento-vendedor"));
			String tipo = Face.get("tipo-documento-vendedor");
			if (documento >= 0 && tipo != null && tipo.length() > 0) {
				VendedorDao dao = new VendedorDao();
				// SELLERS
				List<Vendedor> v = dao.findByFieldList("documento", documento);
				Vendedor vendedor = null;
				// SEARCH
				for (Vendedor ve : v) {
					if (ve.getPersona().getTipoDocumentoBean().getNombre().equals(tipo)) {
						vendedor = ve;
						break;
					}
				}
				// RESULT
				if (vendedor != null && vendedor.getPersona() != null) {
					this.vendedor = new DefaultStreamedContent(
							new ByteArrayInputStream(vendedor.getPersona().getFoto()), "image/png");
				} else {
					this.vendedor = null;
				}
			} else {
				this.vendedor = null;
			}
		}
		return vendedor;
	}
	
	/**
	 * Metodo que permite traer la imagen del proveedor.
	 * 
	 * @return representa la imagen del proveedor.
	 */
	@SuppressWarnings("deprecation")
	public StreamedContent getProveedor() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
			this.proveedor = new DefaultStreamedContent();
		else {
			int id = Integer.parseInt(Face.get("id-proveedor"));
			if (id >= 0) {
				ProveedorDao dao= new ProveedorDao();
				Proveedor p= dao.find(id);
				if(p!= null && p.getFoto() != null) {
					this.proveedor = new DefaultStreamedContent(
							new ByteArrayInputStream(p.getFoto()), "image/png");
				}else {
					this.proveedor = null;
				}
			}
		}
		return proveedor;
	}

	///////////////////////////////////////////////////////
	// Getter y Setters
	///////////////////////////////////////////////////////
	public FacesMessage getMensaje() {
		return mensaje;
	}

	public void setVendedor(StreamedContent vendedor) {
		this.vendedor = vendedor;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setLogo(StreamedContent logo) {
		this.logo = logo;
	}

	public void setMensaje(FacesMessage mensaje) {
		this.mensaje = mensaje;
	}

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AppBean getApp() {
		return app;
	}

	public void setProveedor(StreamedContent proveedor) {
		this.proveedor = proveedor;
	}

	public void setApp(AppBean app) {
		this.app = app;
	}

	public void setCarrousel(StreamedContent carrousel) {
		this.carrousel = carrousel;
	}
}
