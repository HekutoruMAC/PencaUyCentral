package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.interfaces.FaseBusinessLocal;
import beans.interfaces.FaseBusinessRemote;
import beans.interfaces.FasePersistenceRemote;
import entidades.Fase;
import entidades.Grupo;

/**
 * Session Bean implementation class FaseBusiness
 */
@Stateless
@LocalBean
public class FaseBusiness implements FaseBusinessRemote, FaseBusinessLocal {

	@EJB FasePersistenceRemote bean;
	
    /**
     * Default constructor. 
     */
    public FaseBusiness() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarFase(String nombre, int id) {
    	return bean.crearFase(nombre, id);
    }
    
    @Override
    public Fase obtenerFase(int id) {
    	return bean.obtenerFase(id);
    }
    
    @Override
    public boolean eliminarFase(int id) {
    	return bean.eliminarFase(id);
    }
    
    @Override
    public List<Fase> obtenerFasesPorTorneo(int id){
    	return bean.obtenerFasesPorTorneo(id);
    }
    
    @Override
    public int obtenerFasePorNombreYTorneo(int id,String nombre) {
    	return bean.obtenerFasePorNombreYTorneo(id, nombre);
    }
    
    public List<Grupo> obtenerGruposFase(int id) {
    	return bean.obtenerGruposFase(id);
    }

}
