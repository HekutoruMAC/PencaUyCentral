package beans;

import beans.interfaces.PartidoPersistenceLocal;
import beans.interfaces.PartidoPersistenceRemote;
import entidades.Equipo;
import entidades.Fase;
import entidades.Grupo;
import entidades.Organizacion;
import entidades.Partido;
import entidades.Torneo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class PartidoPersistence
 */
@Stateless
@LocalBean
public class PartidoPersistence implements PartidoPersistenceRemote, PartidoPersistenceLocal {
	
	@PersistenceContext(unitName="PencaUyCentral-persistencia")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public PartidoPersistence() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarPartido(int elocal, int evisita, int grupo, Date fecha) {
		//List<Equipo> le = em.createNamedQuery("Equipo.findByNombre", Equipo.class).setParameter("nombre", nombre).getResultList();
    	if (em.createQuery("SELECT p FROM "+ Partido.class.getSimpleName()+" p WHERE p.equipoLocal = " + elocal +" and p.equipoVisita = " + evisita+ " and p.grupo = "+ grupo).getResultList().isEmpty()) {	
			Partido p = new Partido();
			p.setEstado("N/JUGADO");
			Equipo el = em.find(Equipo.class, elocal);
			Equipo ev = em.find(Equipo.class, evisita);
			p.setEquipLocal(el);
			p.setEquipoVisitante(ev);
			Grupo g = em.find(Grupo.class, grupo);
			p.setGrupo(g);
			p.setFecha(fecha);
			em.persist(p);
			return true;
		} else {
			return false;
		}		
	}	
	
	@Override
	public Partido obtenerPartido(int id) {
		return (Partido) em.find(Partido.class, id);
	}	
		
	@Override
	@SuppressWarnings("unchecked")
    public int obtenerPartidoPorGrupoEquipoLocalYEquipoVisitante(int idg, int idel, int idev){
    	List<Partido> list = em.createQuery( "Select p from "+ Partido.class.getSimpleName()+" p where p.grupo = " + idg +" and p.equipoLocal = " + idel + " and p.equipoVisita = " + idev).getResultList();
    	if (!(list.isEmpty())) {
    		return list.get(0).getId();
    	}else return -1;
    }
	
	@SuppressWarnings("unchecked")
	@Override	
	public List<Equipo> obtenerEquiposVisitantesPartidosConEquipoLocal(int idel, int idg) {
		List<Partido> lp = em.createQuery("Select p from "+ Partido.class.getSimpleName() + " p where p.grupo = " + idg + " and p.equipoLocal = " + idel).getResultList();
		List<Equipo> lev = new ArrayList<Equipo>();
		for (int i = 0; i < lp.size(); i++) {
			lev.add(lp.get(i).getEquipoVisitante());
		}		
		return lev;	 
	}
	
	@Override
	public boolean eliminarPartido(int id) {
		Partido p = em.find(Partido.class, id);
		if (p != null) {				
			em.remove(p);
			return true;
		} else {
			return false;
		}	
	}
	
	@Override
	public boolean actualizarPartido(int idg, int idel, int idev, int golesel, int golesev, int idgana) {
		int idp = obtenerPartidoPorGrupoEquipoLocalYEquipoVisitante(idg, idel, idev);
		Partido p = obtenerPartido(idp);
		p.setGolesEquipoLocal(golesel);
		p.setGolesEquipoVisita(golesev);
		Equipo eg = em.find(Equipo.class, idgana);
		p.setEquipoGanador(eg);
		p.setEstado("FINALIZADO");
		em.merge(p);
		return true;
	}

	@Override
	public Date obtenerFechaPartido(int idp) {
		Partido p = obtenerPartido(idp);
		return p.getFecha();
	}
}
