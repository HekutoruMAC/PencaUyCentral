package penca.uy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.interfaces.FasePersistenceRemote;
import beans.interfaces.TorneoPersistenceRemote;
import entidades.Fase;
import entidades.Grupo;
import entidades.Torneo;

@ManagedBean(name="BorrarFaseView")
@ViewScoped
public class BorrarFaseView implements Serializable {

	private static final long serialVersionUID = 1L;
	private String torneo;
	private List<String> torneos;
	private String fase;
	private List<String> fases;

	@EJB
	TorneoPersistenceRemote torneoBean;
	
	@EJB
	FasePersistenceRemote faseBean;

	@PostConstruct
	public void init() {
		List<Torneo> lista = torneoBean.obtenerTodos();
		int i = lista.size();
		torneos = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			torneos.add(lista.get(j).getNombre());
		}
	}

	public List<String> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<String> torneos) {
		this.torneos = torneos;
	}

	public String getTorneo() {
		return torneo;
	}

	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}
	

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public List<String> getFases() {
		return fases;
	}

	public void setFases(List<String> fases) {
		this.fases = fases;
	}
	
	 public void onTorneoChange() {
	        if(torneo !=null && !torneo.equals("")) {
	        	System.out.println("Este es el torneo "+ torneo);
	        	int idt = torneoBean.obtenerTorneoPorNombre(torneo);
	        	List<Fase> listaFases = faseBean.obtenerFasesPorTorneo(idt);
	        	int x = listaFases.size();
	    		fases = new ArrayList<String>();
	    		for (int j = 0; j < x; j++) {
	    			fases.add(listaFases.get(j).getNombre());
	    		}
	        }
	   }

	public void borrar() {
		FacesMessage msg;
		if (fase != null) {
			System.out.println("la fase no es null, es " + fase);
			int idt = torneoBean.obtenerTorneoPorNombre(torneo);
			int idf = faseBean.obtenerFasePorNombreYTorneo(idt, fase);
			List<Grupo> lgf = faseBean.obtenerGruposFase(idf);
			if (lgf.isEmpty()) {
				faseBean.eliminarFase(idf);			
				msg = new FacesMessage("Se borró la fase " + fase);	
			}
			else {
				msg = new FacesMessage("No es posible borrar la fase " + fase + " dado que tiene grupos asociados");
			}			
		} else {
			System.out.println("el torneo es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "El Torneo no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
