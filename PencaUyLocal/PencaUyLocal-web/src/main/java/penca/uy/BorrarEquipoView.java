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

import beans.interfaces.EquipoPersistenceRemote;
import entidades.Equipo;
import entidades.EquiposGrupo;

@ManagedBean(name="BorrarEquipoView")
@ViewScoped
public class BorrarEquipoView implements Serializable {

	private static final long serialVersionUID = 1L;
	private String equipo;
	private List<String> equipos;

	@EJB
	EquipoPersistenceRemote equipoBean;

	@PostConstruct
	public void init() {
		List<Equipo> lista = equipoBean.obtenerEquipos();
		int i = lista.size();
		equipos = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			equipos.add(lista.get(j).getNombre());
		}
	}

	public List<String> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<String> equipos) {
		this.equipos = equipos;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public void borrar() {
		FacesMessage msg;
		if (equipo != null) {
			System.out.println("el equipo no es null, es " + equipo);
			int ide = equipoBean.obtenerEquipoPorNombre(equipo);
			List<EquiposGrupo> leg = equipoBean.obtenerGruposEquipo(ide);
			if (leg == null) {
				equipoBean.eliminarEquipo(ide);			
				msg = new FacesMessage("Se borró el equipo " + equipo);
			} else {
				msg = new FacesMessage("No es posible borrar el equipo " + equipo + " dado que tiene grupos asociados");
			}			
		} else {
			System.out.println("el equipo es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "El Equipo no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}

