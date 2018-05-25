package penca.uy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import beans.interfaces.FasePersistenceRemote;
import beans.interfaces.TorneoPersistenceRemote;
import entidades.Torneo;


@ManagedBean(name = "AgregarFaseView")
public class AgregarFaseView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	FasePersistenceRemote faseBean;
		
	@EJB
	TorneoPersistenceRemote torneoBean;

	@PostConstruct
	public void init() {
		List<Torneo> lista = torneoBean.obtenerTodos();
		int i = lista.size();
		torneos = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			torneos.add(lista.get(j).getNombre());
		}
	}

	private String nombre;
	private String torneo;
	private List<String> torneos;


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTorneo() {
		return torneo;
	}

	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}
	
	

	public List<String> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<String> torneos) {
		this.torneos = torneos;
	}

	public void save() {
		int idt = torneoBean.obtenerTorneoPorNombre(torneo);
		int idf = faseBean.obtenerFasePorNombreYTorneo(idt, nombre);
		if (idf == -1) {
			if (faseBean.crearFase(nombre, idt)) {				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Se ha agregado la fase " + nombre + " al torneo " + torneo));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("El torneo no existe"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ya existe la fase " + nombre + " en el torneo " + torneo));
		}			
	}

}