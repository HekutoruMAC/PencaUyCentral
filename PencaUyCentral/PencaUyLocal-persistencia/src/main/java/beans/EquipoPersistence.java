package beans;

import beans.interfaces.EquipoPersistenceLocal;
import beans.interfaces.EquipoPersistenceRemote;
import entidades.Equipo;
import entidades.EquiposGrupo;
import entidades.Grupo;
import entidades.Organizacion;
import entidades.Torneo;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class EquipoPersistence
 */
@Stateless
@LocalBean
public class EquipoPersistence implements EquipoPersistenceRemote, EquipoPersistenceLocal {
	
	@PersistenceContext(unitName="PencaUyCentral-persistencia")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public EquipoPersistence() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarEquipo(String nombre) {
		//List<Equipo> le = em.createNamedQuery("Equipo.findByNombre", Equipo.class).setParameter("nombre", nombre).getResultList();
    	//if (em.createQuery("SELECT e FROM "+ Equipo.class.getSimpleName()+" e WHERE e.nombre = '" + nombre +"'").getResultList().isEmpty()) {
    	int ide = obtenerEquipoPorNombre(nombre);
    	if (ide == -1) {
			Equipo ne = new Equipo();
			ne.setNombre(nombre);		
			em.persist(ne);
			return true;
		} else {
			return false;
		}		
	}	
	
	@Override
	public Equipo obtenerEquipo(int id) {
		return (Equipo) em.find(Equipo.class, id);
	}	
	
	@Override
	public int obtenerEquipoPorNombre(String nombre) {
		List<Equipo> le = em.createNamedQuery("Equipo.findByNombre", Equipo.class).setParameter("nombre", nombre).getResultList();
		if (le.isEmpty()) {
			return -1;
		}
		else {
			return le.get(0).getId();
		}
	}		
	
	@Override
	public List<Equipo> obtenerEquipos(){
		return (List<Equipo>) em.createNamedQuery("Equipo.findAll", Equipo.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EquiposGrupo> obtenerGruposEquipo(int id) {
		List<EquiposGrupo> leg = em.createQuery("SELECT eg FROM " + EquiposGrupo.class.getSimpleName() + " eg WHERE eg.equipo = " + id).getResultList();
		if (leg.isEmpty()) {
			return null;
		}
		else {
			return leg;
		}
	}
	
	@Override
	public boolean eliminarEquipo(int id) {
		Equipo e = em.find(Equipo.class, id);
		if (e != null) {				
			em.remove(e);
			return true;
		} else {
			return false;
		}	
	}


}
