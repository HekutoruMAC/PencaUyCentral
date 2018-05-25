package penca.uy;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import beans.interfaces.TiposPremioPersistenceRemote;

@ManagedBean(name = "AgregarTipoPremioView")
public class AgregarTipoPremioView implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB TiposPremioPersistenceRemote tipopremioBean;
	
	//private ITiposPremioPersistenceController itpc;
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void save() {
		System.out.println("save");
		//itpc = new TiposPremioPersistenceController();
		if (tipopremioBean.agregarTipoPremio(nombre)) {
			System.out.println("Agrego Tipo de Premio");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Se ha agregado el tipo de premio " + nombre));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ya existe un tipo de premio con ese nombre"));
		}
	}
	

}

