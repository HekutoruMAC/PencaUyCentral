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

import beans.interfaces.EquiposGrupoPersistenceRemote;
import beans.interfaces.FasePersistenceRemote;
import beans.interfaces.GrupoPersistenceRemote;
import beans.interfaces.TorneoPersistenceRemote;
import entidades.EquiposGrupo;
import entidades.Fase;
import entidades.Grupo;
import entidades.Partido;
import entidades.Torneo;

@ManagedBean(name="BorrarGrupoView")
@ViewScoped
public class BorrarGrupoView implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String torneo;
	private List<String> torneos;
	private String fase;
	private List<String> fases;
	private String grupo;
	private List<String> grupos;

	@EJB
	TorneoPersistenceRemote torneoBean;
	
	@EJB
	FasePersistenceRemote faseBean;
	
	@EJB
	GrupoPersistenceRemote grupoBean;
	
	@EJB
	EquiposGrupoPersistenceRemote equiposgrupoBean;

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public List<String> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<String> grupos) {
		this.grupos = grupos;
	}

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
	 
	 public void onFaseChange() {
	        if(fase !=null && !fase.equals("")) {
	        	int idt = torneoBean.obtenerTorneoPorNombre(torneo);
	        	int idf = faseBean.obtenerFasePorNombreYTorneo(idt, fase);
	        	List<Grupo> listaGrupos = grupoBean.obtenerGruposPorFase(idf);
	        	int z = listaGrupos.size();
	    		grupos = new ArrayList<String>();
	    		for (int y = 0; y < z; y++) {
	    			grupos.add(listaGrupos.get(y).getNombre());
	    		}
	        }
	   }

	public void borrar() {
		FacesMessage msg;
		if (grupo != null) {
			int idt = torneoBean.obtenerTorneoPorNombre(torneo);
			int idf = faseBean.obtenerFasePorNombreYTorneo(idt, fase);
			int idg = grupoBean.obtenerGrupoPorNombreYFase(grupo, idf);
			List<Partido> lpg = grupoBean.obtenerPartidosGrupo(idg);
			if (lpg.isEmpty()) {
				List<EquiposGrupo> leg = grupoBean.obtenerEquiposGrupo(idg);				
				if (leg.isEmpty()) {
					grupoBean.eliminarGrupo(idg);	
					msg = new FacesMessage("Se borró el Grupo  " + grupo + " de la fase "+ fase);
				}
				else {
					msg = new FacesMessage("No es posible borrar el grupo " + grupo + " dado que tiene equipos asociados");
				}
			}
			else {
				msg = new FacesMessage("No es posible borrar el grupo " + grupo + " dado que tiene partidos asociados");
			}					
		} else {
			System.out.println("el torneo es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "El Grupo no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
		

}
