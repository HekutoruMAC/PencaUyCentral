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

import beans.interfaces.TiposPremioPersistenceRemote;
import entidades.TiposPremio;

@ManagedBean(name="BorrarTipoPremioView")
@ViewScoped
public class BorrarTipoPremioView implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tipopremio;
	private List<String> tiposdepremio;

	@EJB
	TiposPremioPersistenceRemote tipopremioBean;

	@PostConstruct
	public void init() {
		List<TiposPremio> lista = tipopremioBean.obtenerTiposPremio();
		int i = lista.size();
		tiposdepremio = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			tiposdepremio.add(lista.get(j).getNombre());
		}
	}
	
	public String getTipopremio() {
		return tipopremio;
	}


	public void setTipopremio(String tipopremio) {
		this.tipopremio = tipopremio;
	}


	public List<String> getTiposdepremio() {
		return tiposdepremio;
	}


	public void setTiposdepremio(List<String> tiposdepremio) {
		this.tiposdepremio = tiposdepremio;
	}

	public void borrar() {
		FacesMessage msg;
		if (tipopremio != null) {
			System.out.println("el tipo de premio no es null, es " + tipopremio);
			int idtp = tipopremioBean.obtenerTipoPremioPorNombre(tipopremio);
			tipopremioBean.eliminarTipoPremio(idtp);			
			msg = new FacesMessage("Se borró el tipo de premio " + tipopremio);
			
		} else {
			System.out.println("el tipo de premio es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "El Tipo de Premio no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}


