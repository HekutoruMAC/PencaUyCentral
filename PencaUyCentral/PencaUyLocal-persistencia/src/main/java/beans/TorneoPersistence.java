package beans;

import entidades.Fase;
import entidades.Organizacion;
import entidades.Penca;
import entidades.Torneo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.interfaces.TorneoPersistenceLocal;
import beans.interfaces.TorneoPersistenceRemote;
import controladores.OrganizacionController;
import controladores.TorneoController;

/**
 * Session Bean implementation class TorneoPersistence
 */
@Stateless

public class TorneoPersistence implements TorneoPersistenceRemote, TorneoPersistenceLocal {		

		@PersistenceContext(unitName = "PencaUyCentral-persistencia")
		private EntityManager em;
	
		/*@EJB 
		Fase fase;*/
		
	    /**
	     * Default constructor. 
	     */
	    public TorneoPersistence() {    
	    	
	        // TODO Auto-generated constructor stub
	    }
	    @Override
	    public boolean crearTorneo(String nombre, String tipo, Date comienzo) {	  
	    	
	    	if (em.createQuery( "Select d from "+ Torneo.class.getSimpleName()+" d where d.nombre = '" + nombre+"'").getResultList().isEmpty()) {
	    		Torneo t = new Torneo(nombre,tipo,comienzo);
		    	em.persist(t);
		    	return true;
	    	}else {
	    		System.out.println("Ya existe un torneo con ese nombre");
	    		return false;
	    	}
	    	
	    }
	    
	    @Override
	    public Torneo obtenerTorneo(int id) {
	    	Torneo t = em.find(Torneo.class, id);
	    	return t;
	    	
	    }
	    
	   
	    @SuppressWarnings("unchecked")
	    public int obtenerTorneoPorNombre(String nombre) {
	    	List<Torneo> list =(List<Torneo>)em.createQuery( "Select d from "+ Torneo.class.getSimpleName()+" d where d.nombre = '"+nombre + "'" ).getResultList();
	    	if (!(list.isEmpty())) {
	    		Torneo torneo = list.get(0);
	    		System.out.println("obtuve un torneo");
	    		return torneo.getId();
	    	}else {
	    		System.out.println("no obtuve nada");
	    		return -1;
	    	}
	    	
	    	
	    }
	    
	    
	    @Override
	    public boolean eliminarTorneo(int id) {   
	    	Torneo t = em.find(Torneo.class, id);
	    	em.remove(t);
	    	return true;
	    }
	    
	    @Override
	    public boolean eliminarTodosTorneo() {
	    	List<Torneo> list = this.obtenerTodos();
	    	Torneo t;

	    	Iterator<Torneo> itList = list.iterator();
	        while(itList.hasNext()) {
	            t = itList.next();
	            em.remove(t);
	            System.out.println("borre un torneo");
	        }
	        return true;
	    }
	    
	    
	    public boolean agregarFase(int id) {
	    	Fase fase = em.find(Fase.class, id);
	    	Torneo t = em.find(Torneo.class, fase.getTorneo().getId());
	    	t.addFase(fase);
	    	return true;    		    	
	    }  
	    
	    @SuppressWarnings("unchecked")
	    public List<Torneo> obtenerTodos(){
	    	
	    	List<Torneo> list = em.createQuery( "Select d from "+ Torneo.class.getSimpleName()+" d" ).getResultList();
	        System.out.println("obtuve todos los torneos");
	    	return list;
	    }
	    
	    @SuppressWarnings("unchecked")
		@Override
		public List<Fase> obtenerFasesTorneo(int id) {
			List<Fase> list = em.createQuery( "Select f from "+ Fase.class.getSimpleName()+" f where f.torneo = " + id).getResultList();
			return list;				
		}	

	}
