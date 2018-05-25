package beans;

import beans.interfaces.EquipoBusinessLocal;
import beans.interfaces.EquipoBusinessRemote;
import beans.interfaces.EquipoPersistenceRemote;
import entidades.Equipo;
import entidades.EquiposGrupo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class EquipoBusiness
 */
@Stateless
@LocalBean
public class EquipoBusiness implements EquipoBusinessRemote, EquipoBusinessLocal {

	@EJB EquipoPersistenceRemote bean;
	
    /**
     * Default constructor. 
     */
    public EquipoBusiness() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public boolean agregarEquipo(String nombre) {
    	return bean.agregarEquipo(nombre);
    }
    
    @Override
	public Equipo obtenerEquipo(int id) {
    	return bean.obtenerEquipo(id);
    }
    
    @Override
	public int obtenerEquipoPorNombre(String nombre) {
    	return bean.obtenerEquipoPorNombre(nombre);
    }
    
    @Override
	public List<Equipo> obtenerEquipos(){
    	return bean.obtenerEquipos();
    }
    
    @Override
    public List<EquiposGrupo> obtenerGruposEquipo(int id){
    	return bean.obtenerGruposEquipo(id);
    }
    
    @Override
	public boolean eliminarEquipo(int id) {
    	return bean.eliminarEquipo(id);
    }
    
}
