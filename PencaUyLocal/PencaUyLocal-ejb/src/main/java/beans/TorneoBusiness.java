package beans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.interfaces.TorneoBusinessLocal;
import beans.interfaces.TorneoBusinessRemote;
import beans.interfaces.TorneoPersistenceRemote;
import entidades.Fase;
import entidades.Torneo;

/**
 * Session Bean implementation class TorneoBusiness
 */
@Stateless
@LocalBean
public class TorneoBusiness implements TorneoBusinessRemote, TorneoBusinessLocal {

	@EJB TorneoPersistenceRemote bean;
	
    /**
     * Default constructor. 
     */
    public TorneoBusiness() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarTorneo(String nombre,String tipo, Date comienzo) {
    	return bean.crearTorneo(nombre, tipo, comienzo);
    }
    
    @Override
    public Torneo obtenerTorneo(int id) {
    	return bean.obtenerTorneo(id);
    }
    
    @Override
    public boolean eliminarTorneo(int id) {
    	return bean.eliminarTorneo(id);
    }
    
    @Override
    public boolean agregarFase(int id) {
    	return bean.agregarFase(id);    	
    }
    
    @Override
    public List<Torneo> obtenerTodos(){
    	return bean.obtenerTodos();
    }
    
    @Override
    public List<Fase> obtenerFasesTorneo(int id){
    	return bean.obtenerFasesTorneo(id);
    }
    
    
    @Override
    public int obtenerTorneoPorNombre(String nombre) {
    	return bean.obtenerTorneoPorNombre(nombre);
    }
    
    @Override
    public boolean eliminarTodosTorneo() {
    	return bean.eliminarTodosTorneo();
    }

}
