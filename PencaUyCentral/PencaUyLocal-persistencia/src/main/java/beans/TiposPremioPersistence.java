package beans;

import beans.interfaces.TiposPremioPersistenceLocal;
import beans.interfaces.TiposPremioPersistenceRemote;
import entidades.Equipo;
import entidades.TiposPremio;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class TiposPremioPersistence
 */
@Stateless
@LocalBean
public class TiposPremioPersistence implements TiposPremioPersistenceRemote, TiposPremioPersistenceLocal {
	
	@PersistenceContext(unitName="PencaUyCentral-persistencia")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public TiposPremioPersistence() {
        // TODO Auto-generated constructor stub
    }
    
    

	public boolean agregarTipoPremio(String nombre) {
		System.out.println("TiposPremioPersistenceBeanAgregar");
		int idtp = obtenerTipoPremioPorNombre(nombre);
		if (idtp == -1) {	
			TiposPremio ntp = new TiposPremio();
			ntp.setNombre(nombre);
			em.persist(ntp);
			return true;
		} else {
			return false;
		}		
	}	
	
	@Override
	public TiposPremio obtenerTipoPremio(int id) {
		return (TiposPremio) em.find(TiposPremio.class, id);
	}	
	
	

	public int obtenerTipoPremioPorNombre(String nombre) {
		System.out.println("TiposPremioPersistenceBeanObtener");
		List<TiposPremio> ltp = em.createNamedQuery("TiposPremio.findByNombre", TiposPremio.class).setParameter("nombre", nombre).getResultList();
		if (ltp.isEmpty()){
			return -1;
		}
		else {
			return ltp.get(0).getId();
		}
	}		
	
	@Override
	public List<TiposPremio> obtenerTiposPremio(){
		return (List<TiposPremio>) em.createNamedQuery("TiposPremio.findAll", TiposPremio.class).getResultList();
	}	
	
	@Override
	public boolean eliminarTipoPremio(int id) {
		TiposPremio tpe = em.find(TiposPremio.class, id);
		if (tpe != null) {				
			em.remove(tpe);
			return true;
		} else {
			return false;
		}	
	}


}
