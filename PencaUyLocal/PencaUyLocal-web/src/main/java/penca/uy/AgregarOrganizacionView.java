package penca.uy;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import beans.interfaces.OrganizacionPersistenceRemote;

@ManagedBean(name = "AgregarOrganizacionView")
public class AgregarOrganizacionView implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB OrganizacionPersistenceRemote organizacionBean;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void save() {
		if (organizacionBean.agregarOrganizacion(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Se ha agregado la organización " + nombre));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ya existe una organización con ese nombre"));
		}
	}
	

}
