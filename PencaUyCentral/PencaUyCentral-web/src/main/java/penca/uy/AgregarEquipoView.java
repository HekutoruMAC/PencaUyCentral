package penca.uy;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import beans.interfaces.EquipoBusinessRemote;

@ManagedBean(name = "AgregarEquipoView")
public class AgregarEquipoView implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB EquipoBusinessRemote equipoBean;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void save() {
		if (equipoBean.agregarEquipo(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Se ha agregado el equipo " + nombre));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ya existe un equipo con ese nombre"));
		}
	}
	

}
