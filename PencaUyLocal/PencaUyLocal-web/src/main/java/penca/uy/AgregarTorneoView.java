package penca.uy;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.interfaces.TorneoPersistenceRemote;

@ManagedBean(name = "AgregarTorneoView")
public class AgregarTorneoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	TorneoPersistenceRemote torneoBean;

	private String nombre;
	private String tipo;
	private Date comienzo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getComienzo() {
		return comienzo;
	}

	public void setComienzo(Date comienzo) {
		this.comienzo = comienzo;
	}

	public void save() {
		Date hoy = new Date();
		if (comienzo.before(hoy)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("El comienzo del Torneo no puede ser anterior a hoy"));
		} else {
			if (torneoBean.crearTorneo(nombre, tipo, comienzo)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Se ha agregado el torneo " + nombre + " del tipo " + tipo));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ya existe un torneo con ese nombre"));
			}
		}
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

}