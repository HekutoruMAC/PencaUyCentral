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

import beans.interfaces.OrganizacionPersistenceRemote;
import beans.interfaces.PencaPersistenceRemote;
import entidades.Organizacion;
import entidades.Participante;
import entidades.Penca;

@ManagedBean(name="BorrarPencaView")
@ViewScoped
public class BorrarPencaView implements Serializable {

	private static final long serialVersionUID = 1L;
	private String organizacion;
	private List<String> organizaciones;
	private String penca;
	private List<String> pencas;

	@EJB
	OrganizacionPersistenceRemote organizacionBean;
	
	@EJB
	PencaPersistenceRemote pencaBean;

	@PostConstruct
	public void init() {
		List<Organizacion> lista = organizacionBean.obtenerOrganizaciones();
		int i = lista.size();
		organizaciones = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			organizaciones.add(lista.get(j).getNombre());
		}
	}

	public List<String> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<String> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
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
	
	public void onOrganizacionChange() {
        if ((organizacion != null) && (!organizacion.equals(""))) {
        	System.out.println("Esta es la Organizacion "+ organizacion);
        	int ido = organizacionBean.obtenerOrganizacionPorNombre(organizacion);
        	List<Penca> listaPencas = pencaBean.obtenerPencasPorOrganizacion(ido);
        	int x = listaPencas.size();
        	System.out.println("Pencas de la organizacion: " + x);
    		pencas = new ArrayList<String>();
    		for (int j = 0; j < x; j++) {
    			pencas.add(listaPencas.get(j).getNombre());
    			System.out.println("Penca: " + pencas.get(j));
    		}
    		x = pencas.size();
    		System.out.println("Pencas de la organizacion: " + x);
        }
    }

	public void borrar() {
		FacesMessage msg;
		if (penca != null) {
			System.out.println("la Penca no es null, es " + penca);
			int ido = organizacionBean.obtenerOrganizacionPorNombre(organizacion);
			int idp = pencaBean.obtenerPencaPorNombreYOrganizacion(ido, penca);
			List<Participante> lpp = pencaBean.obtenerParticipantesPenca(idp);
			if (lpp == null) {
				pencaBean.eliminarPenca(idp);			
				msg = new FacesMessage("Se borró la Penca " + penca);
			}
			else {
				msg = new FacesMessage("No es posible borrar la penca " + penca + " dado que tiene participantes asociados");
			}
		} else {
			System.out.println("la Organizacion es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "La Organizacion no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
