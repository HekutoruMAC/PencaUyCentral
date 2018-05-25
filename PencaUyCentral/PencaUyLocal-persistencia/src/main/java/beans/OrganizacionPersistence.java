package beans;

import beans.interfaces.OrganizacionPersistenceLocal;
import beans.interfaces.OrganizacionPersistenceRemote;
import entidades.Organizacion;
import entidades.Penca;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class OrganizacionPersistence
 */
@Stateless
@LocalBean
public class OrganizacionPersistence implements OrganizacionPersistenceRemote, OrganizacionPersistenceLocal {
	
	@PersistenceContext(unitName="PencaUyCentral-persistencia")
	private EntityManager em;
	
	/**
     * Default constructor. 
     */
    public OrganizacionPersistence() {
        // TODO Auto-generated constructor stub    	
    }     	
    
	@Override
	public boolean agregarOrganizacion(String nombre) {
		int ido = obtenerOrganizacionPorNombre(nombre);
		if (ido == -1) {		
			Organizacion no = new Organizacion();
			no.setNombre(nombre);
			em.persist(no);
			return true;
		} else {
			return false;
		}		
	}	
	
	@Override
	public Organizacion obtenerOrganizacion(int id) {
		return (Organizacion) em.find(Organizacion.class, id);
	}	
	
	@Override
	public List<Organizacion> obtenerOrganizaciones(){
		return (List<Organizacion>) em.createNamedQuery("Organizacion.findAll", Organizacion.class).getResultList();
	}
	
	@Override
	public int obtenerOrganizacionPorNombre(String nombre) {
		List<Organizacion> lo = em.createNamedQuery("Organizacion.findByNombre", Organizacion.class).setParameter("nombre", nombre).getResultList();
		if (lo.isEmpty()) {
			return -1;
		}
		else {
			return lo.get(0).getId();
		}
	}	
	
	@Override
	public List<Penca> obtenerPencasOrganizacion(int id) {
		Organizacion o = obtenerOrganizacion(id);
		if (o != null) {
			List<Penca> lp = (List<Penca>) em.createNamedQuery("Penca.findByOrganizacion", Penca.class).setParameter("organizacion", o).getResultList();
			return lp;
		} else {
			return null;
		}	
	}	
	
	/*@Override
	public boolean agregarPencaEnOrganizacion(int id, Penca penca) {
		Organizacion o = obtenerOrganizacion(id);
		if (o != null) {
			em.getTransaction().begin();
			penca.setOrganizacion(o);			
			em.getTransaction().commit();
			return true;
		} else {
			return false;
		}		
	}
	
	@Override
	public boolean eliminarPencaEnOrganizacion(int id, Penca penca) {
		Organizacion o = obtenerOrganizacion(id);
		if (o != null) {	
			em.getTransaction().begin();
			penca.setOrganizacion(null);			
			em.getTransaction().commit();
			return true;
		} else {
			return false;
		}	
	}*/
	
	@Override
	public boolean eliminarOrganizacion(int id) {
		Organizacion o = obtenerOrganizacion(id);
		if (o != null) {				
			em.remove(o);
			return true;
		} else {
			return false;
		}
	}		

}
