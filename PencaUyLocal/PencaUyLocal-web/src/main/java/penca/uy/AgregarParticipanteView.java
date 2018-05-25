package penca.uy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import beans.interfaces.ParticipantePersistenceRemote;
import beans.interfaces.PencaPersistenceRemote;
import entidades.Penca;

@ManagedBean(name = "AgregarParticipanteView")
public class AgregarParticipanteView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ParticipantePersistenceRemote participanteBean;
	
	
	@EJB
	PencaPersistenceRemote pencaBean;
	
	private String usuario;
	private String penca;
	private List<String> pencas;

	@PostConstruct
	public void init() {
		List<Penca> lista = pencaBean.obtenerPencas();
		int i = lista.size();
		pencas = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			pencas.add(lista.get(j).getNombre());
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPenca() {
		return penca;
	}

	public void setPenca(String penca) {
		this.penca = penca;
	}
	
	public List<String> getPencas() {
		return pencas;
	}

	public void setPencas(List<String> pencas) {
		this.pencas = pencas;
	}	

	public void save() {
		int idp = pencaBean.obtenerPencaPorNombre(penca);
		if (participanteBean.agregarParticipante(usuario, idp)){				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Se ha agregado el Participante " + usuario + " al la Penca " + penca));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("El Participante " + usuario + " ya existe."));
			}
	}

}

