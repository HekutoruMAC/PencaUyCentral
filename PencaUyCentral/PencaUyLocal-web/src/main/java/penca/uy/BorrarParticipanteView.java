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

import beans.interfaces.ParticipantePersistenceRemote;
import entidades.Participante;

@ManagedBean(name="BorrarParticipanteView")
@ViewScoped
public class BorrarParticipanteView implements Serializable {

	private static final long serialVersionUID = 1L;
	//private String penca;
	//private List<String> pencas;
	private String participante;
	private List<String> participantes;

	//@EJB
	//PencaPersistenceRemote pencaBean;
	
	@EJB
	ParticipantePersistenceRemote participanteBean;

	/*@PostConstruct
	public void init() {
		List<Penca> lista = pencaBean.obtenerPencas();
		int i = lista.size();
		pencas = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			pencas.add(lista.get(j).getNombre());
		}
	}*/
	
	@PostConstruct
	public void init() {
		List<Participante> lista = participanteBean.obtenerParticipantes();
		int i = lista.size();
		participantes = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			participantes.add(lista.get(j).getUsuario());
		}
	}

	/*public List<String> getPencas() {
		return pencas;
	}

	public void setPencas(List<String> pencas) {
		this.pencas = pencas;
	}

	public String getPenca() {
		return penca;
	}

	public void setPenca(String penca) {
		this.penca = penca;
	}*/
	

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

	public List<String> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<String> participantes) {
		this.participantes = participantes;
	}
	
	/*public void onPencaChange() {
	    if ((penca != null) && (!penca.equals(""))) {
	     	System.out.println("Esta es la Penca "+ penca);
	       	Penca p = pencaBean.obtenerPencaPorNombre(penca);
	       	List<Participante> listaParticipantes = participanteBean.obtenerParticipantesPorPenca(p.getId());
	       	int x = listaParticipantes.size();
	       	System.out.println("Participantes de la Penca: " + x);
	    	participantes = new ArrayList<String>();
	    	for (int j = 0; j < x; j++) {
	    		participantes.add(listaParticipantes.get(j).getUsuario());
	    		System.out.println("Participante: " + participantes.get(j));
	    	}
	    	x = participantes.size();
	    	System.out.println("Participantes de la Penca: " + x);
	    }
	   }*/

	public void borrar() {
		FacesMessage msg;
		if (participante != null) {
			System.out.println("la Participante no es null, es " + participante);
			int idp = participanteBean.obtenerParticipantePorUsuario(participante);
			participanteBean.eliminarParticipante(idp);			
			msg = new FacesMessage("Se borró el Participante " + participante);			
		} else {
			System.out.println("la Penca es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "El Participante no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
