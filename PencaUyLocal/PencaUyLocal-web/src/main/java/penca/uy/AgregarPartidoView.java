package penca.uy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.interfaces.EquipoPersistenceRemote;
import beans.interfaces.EquiposGrupoPersistenceRemote;
import beans.interfaces.FasePersistenceRemote;
import beans.interfaces.GrupoPersistenceRemote;
import beans.interfaces.PartidoPersistenceRemote;
import beans.interfaces.TorneoPersistenceRemote;
import entidades.Equipo;
import entidades.Fase;
import entidades.Grupo;
import entidades.Torneo;

@ManagedBean(name="AgregarPartidoView")
@ViewScoped
public class AgregarPartidoView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	

	private String torneo;
	private List<String> torneos;
	private String fase;
	private List<String> fases;
	private String grupo;
	private List<String> grupos;
	private String equipoLocal;
	private String equipoVisita;
	
	
	public String getEquipoVisita() {
		return equipoVisita;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setEquipoVisita(String equipoVisita) {
		this.equipoVisita = equipoVisita;
	}

	public List<String> getEquiposVisita() {
		return equiposVisita;
	}

	public void setEquiposVisita(List<String> equiposVisita) {
		this.equiposVisita = equiposVisita;
	}

	private List<String> equiposLocal;
	private List<String> equiposVisita;
	

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public List<String> getEquiposLocal() {
		return equiposLocal;
	}

	public void setEquiposLocal(List<String> equiposLocal) {
		this.equiposLocal = equiposLocal;
	}

	@EJB
	TorneoPersistenceRemote torneoBean;
	
	@EJB
	FasePersistenceRemote faseBean;
	
	@EJB
	GrupoPersistenceRemote grupoBean;
	
	@EJB 
	EquipoPersistenceRemote equipoBean;
	
	@EJB
	EquiposGrupoPersistenceRemote equiposGrupoBean;
	
	@EJB
	PartidoPersistenceRemote partidoBean;

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
		 System.out.println("entre al onFaseChange");
		 if(fase !=null && !fase.equals("")) {
	        	System.out.println("Esta es la fase "+ fase);
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
	 
	 
	 public void onGrupoChange() {
		 if(grupo !=null && !grupo.equals("")) {
	        	System.out.println("Esta es el grupo "+ grupo);
	        	int idt = torneoBean.obtenerTorneoPorNombre(torneo);
	        	int idf = faseBean.obtenerFasePorNombreYTorneo(idt, fase);
	        	int idg = grupoBean.obtenerGrupoPorNombreYFase(grupo,idf);
	        	List<Equipo> listaEquipos = equiposGrupoBean.obtenerEquiposPorGrupo(idg);
	        	int z = listaEquipos.size();
	        	equiposLocal = new ArrayList<String>();
	        	equiposVisita = new ArrayList<String>();
	    		for (int y = 0; y < z; y++) {
	    			equiposLocal.add(listaEquipos.get(y).getNombre());
	    			equiposVisita.add(listaEquipos.get(y).getNombre());
	    		}
	        }
	 }

	public void save() {
		FacesMessage msg;
		if ((grupo != null) && (equipoLocal != null) && (equipoVisita != null)) {
			Date hoy = new Date();
			if (fecha.before(hoy)) {
				msg = new FacesMessage("La fecha del partido no puede ser anterior a hoy");
			} else {
				if (equipoLocal.equals(equipoVisita)) {	
					msg = new FacesMessage("El Equipo Visitante debe ser distinto del Equipo Local");
				} else {
					  System.out.println("el grupo "+grupo+" no es null, la fase "+fase+ "no es null");
					  int idt = torneoBean.obtenerTorneoPorNombre(torneo);
					  int idf = faseBean.obtenerFasePorNombreYTorneo(idt, fase);
					  int idg = grupoBean.obtenerGrupoPorNombreYFase(grupo, idf);
					  int idel = equipoBean.obtenerEquipoPorNombre(equipoLocal);
					  int idev = equipoBean.obtenerEquipoPorNombre(equipoVisita);	
					  int idp1 = partidoBean.obtenerPartidoPorGrupoEquipoLocalYEquipoVisitante(idg, idel, idev);
					  int idp2 = partidoBean.obtenerPartidoPorGrupoEquipoLocalYEquipoVisitante(idg, idev, idel);
					  if (idp1 == -1 && idp2 == -1) {
						  partidoBean.agregarPartido(idel, idev, idg, fecha);
						  msg = new FacesMessage("Se añadió el partido  " + equipoLocal + " vs "+ equipoVisita + " en el  grupo "+grupo);
					  } else {
						  msg = new FacesMessage("Ya existe el partido " + equipoLocal + " vs "+ equipoVisita + " en el  grupo "+grupo);
					  }
				}
			}
		}
		else {
			System.out.println("el torneo es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "El Torneo no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
