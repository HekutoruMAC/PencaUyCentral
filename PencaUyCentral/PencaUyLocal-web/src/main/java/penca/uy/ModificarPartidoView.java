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
import beans.interfaces.FasePersistenceRemote;
import beans.interfaces.GrupoPersistenceRemote;
import beans.interfaces.PartidoPersistenceRemote;
import beans.interfaces.TorneoPersistenceRemote;
import entidades.Equipo;
import entidades.Fase;
import entidades.Grupo;
import entidades.Partido;
import entidades.Torneo;

@ManagedBean(name="ModificarPartidoView")
@ViewScoped
public class ModificarPartidoView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String torneo;
	private List<String> torneos;
	private String fase;
	private List<String> fases;
	private String grupo;
	private List<String> grupos;
	private String equipolocal;
	private List<String> equiposdelocal;
	private String equipovisita;
	private List<String> equiposdevisita;
	private int goleslocal;
	private int golesvisita;
	
	@EJB
	TorneoPersistenceRemote torneoBean;
	
	@EJB
	FasePersistenceRemote faseBean;
	
	@EJB
	GrupoPersistenceRemote grupoBean;
	
	@EJB
	PartidoPersistenceRemote partidoBean;
	
	@EJB
	EquipoPersistenceRemote equipoBean;
	
	
	@PostConstruct
	public void init() {
		List<Torneo> lista = torneoBean.obtenerTodos();
		int i = lista.size();
		torneos = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			torneos.add(lista.get(j).getNombre());
		}
	}

	public int getGoleslocal() {
		return goleslocal;
	}

	public void setGoleslocal(int goleslocal) {
		this.goleslocal = goleslocal;
	}

	public int getGolesvisita() {
		return golesvisita;
	}

	public void setGolesvisita(int golesvisita) {
		this.golesvisita = golesvisita;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getEquipolocal() {
		return equipolocal;
	}

	public void setEquipolocal(String equipolocal) {
		this.equipolocal = equipolocal;
	}

	public List<String> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<String> grupos) {
		this.grupos = grupos;
	}

	public List<String> getEquiposdelocal() {
		return equiposdelocal;
	}

	public void setEquiposdelocal(List<String> equiposdelocal) {
		this.equiposdelocal = equiposdelocal;
	}

	public String getEquipovisita() {
		return equipovisita;
	}

	public void setEquipovisita(String equipovisita) {
		this.equipovisita = equipovisita;
	}

	public List<String> getEquiposdevisita() {
		return equiposdevisita;
	}

	public void setEquiposdevisita(List<String> equiposdevisita) {
		this.equiposdevisita = equiposdevisita;
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
	        if (torneo !=null && !torneo.equals("")) {
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
	        if (fase !=null && !fase.equals("")) {
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
	        if (grupo !=null && !grupo.equals("")) {
	        	int idt = torneoBean.obtenerTorneoPorNombre(torneo);
	        	int idf = faseBean.obtenerFasePorNombreYTorneo(idt, fase);
	        	int idg = grupoBean.obtenerGrupoPorNombreYFase(grupo, idf);
	        	List<Partido> listaPartidos = grupoBean.obtenerPartidosGrupo(idg);
	        	int z = listaPartidos.size();
	    		equiposdelocal = new ArrayList<String>();
	    		for (int y = 0; y < z; y++) {
	    			equiposdelocal.add(listaPartidos.get(y).getEquipLocal().getNombre());
	    		}
	        }
	   }
	 
	 public void onEquipoLocalChange() {
	        if (equipolocal != null && !equipolocal.equals("")) {
	        	int idt = torneoBean.obtenerTorneoPorNombre(torneo);
	        	int idf = faseBean.obtenerFasePorNombreYTorneo(idt, fase);
	        	int idg = grupoBean.obtenerGrupoPorNombreYFase(grupo, idf);
	        	int idel = equipoBean.obtenerEquipoPorNombre(equipolocal);
	        	List<Equipo> lev = partidoBean.obtenerEquiposVisitantesPartidosConEquipoLocal(idel, idg);
	        	equiposdevisita = new ArrayList<String>();
	        	int z = lev.size();
	    		for (int y = 0; y < z; y++) {
	    			if (!equiposdevisita.contains(lev.get(y).getNombre())) {
	    				equiposdevisita.add(lev.get(y).getNombre());
	    			}
	    		}
	        }
	   }

	public void actualizar() {
		FacesMessage msg;
		if (grupo != null) {
			int idt = torneoBean.obtenerTorneoPorNombre(torneo);
			int idf = faseBean.obtenerFasePorNombreYTorneo(idt, fase);
			int idg = grupoBean.obtenerGrupoPorNombreYFase(grupo, idf);
			int idel = equipoBean.obtenerEquipoPorNombre(equipolocal);
			int idev = equipoBean.obtenerEquipoPorNombre(equipovisita);
			int idp = partidoBean.obtenerPartidoPorGrupoEquipoLocalYEquipoVisitante(idg, idel, idev);
			if (idp != -1) {
				Date hoy = new Date();
				Date fecha = partidoBean.obtenerFechaPartido(idp);
				if (hoy.before(fecha)) {
					msg = new FacesMessage("No se pueden actualizar resultados antes de que se juegue el partido en la fecha " + fecha.toString());
				}
				else {
					int ideg;
					if (golesvisita < goleslocal) {
						ideg = idel;
					}
					else {
						ideg = idev;
					}
					partidoBean.actualizarPartido(idg, idel, idev, goleslocal, golesvisita, ideg);	
					msg = new FacesMessage("Se actualizó el partido");
				}
			}
			else {
				msg = new FacesMessage("No existe el partido");
			}						
		} else {
			System.out.println("el torneo es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "El Partido no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
}