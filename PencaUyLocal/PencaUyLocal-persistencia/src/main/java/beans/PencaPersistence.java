package beans;

import beans.interfaces.PencaPersistenceLocal;
import beans.interfaces.PencaPersistenceRemote;
import entidades.Organizacion;
import entidades.Participante;
import entidades.Penca;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class PencaPersistence
 */
@Stateless
@LocalBean
public class PencaPersistence implements PencaPersistenceRemote, PencaPersistenceLocal {
	
	@PersistenceContext(unitName="PencaUyLocal-persistencia")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public PencaPersistence() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarPenca(String nombre, int ido, int ida, int estilo, int precio) {
		Organizacion o = em.find(Organizacion.class, ido);
		if (o != null) {	
			int idpe = obtenerPencaPorNombre(nombre);
			if (idpe == -1) {
				Penca p = new Penca();
				p.setNombre(nombre);
				p.setOrganizacion(o);
				p.setIda(ida);
				p.setEstilo(estilo);
				p.setPrecio(precio);
				em.persist(p);
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}		
	}	
	
	@Override
	public Penca obtenerPenca(int id) {
		return (Penca) em.find(Penca.class, id);
	}	
	
	@Override
	public int obtenerPencaPorNombre(String nombre) {
		List<Penca> lp = em.createNamedQuery("Penca.findByNombre", Penca.class).setParameter("nombre", nombre).getResultList();
		if (lp.isEmpty()) {
			return -1;
		}
		else {
			return lp.get(0).getId();
		}
	}	
	
	@Override
	public List<Penca> obtenerPencas(){
		return (List<Penca>) em.createNamedQuery("Penca.findAll", Penca.class).getResultList();
	}
	
	@Override
	public List<Penca> obtenerPencasPorOrganizacion(int id){
		Organizacion o = em.find(Organizacion.class, id);
		return (List<Penca>) em.createNamedQuery("Penca.findByOrganizacion", Penca.class).setParameter("organizacion", o).getResultList();
	}
	
	@Override
	public int obtenerPencaPorNombreYOrganizacion(int id, String nombre) {
		Organizacion o = em.find(Organizacion.class, id);
		List<Penca> lp = em.createNamedQuery("Penca.findByNombreAndOrganizacion", Penca.class).setParameter("nombre", nombre).setParameter("organizacion", o).getResultList();
		if (lp.isEmpty()) {
			return -1;
		}
		else {
			return lp.get(0).getId();
		}
	}
	
	
	@Override
	public List<Participante> obtenerParticipantesPenca(int id){
		Penca p = em.find(Penca.class, id);
		if (p != null) {
			List<Participante> lp = (List<Participante>) em.createNamedQuery("Participante.findByPenca", Participante.class).setParameter("penca", p).getResultList();
			return lp;
		} else {
			return null;
		}	
	}
	
	@Override
	public boolean eliminarPenca(int id) {
		Penca po = em.find(Penca.class, id);
		if (po != null) {				
			em.remove(po);
			return true;
		} else {
			return false;
		}	
	}

}
