package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.interfaces.EquiposGrupoBusinessLocal;
import beans.interfaces.EquiposGrupoBusinessRemote;
import beans.interfaces.EquiposGrupoPersistenceRemote;
import entidades.Equipo;
import entidades.EquiposGrupo;

/**
 * Session Bean implementation class EquiposGrupoBusiness
 */
@Stateless
@LocalBean
public class EquiposGrupoBusiness implements EquiposGrupoBusinessRemote, EquiposGrupoBusinessLocal {

	@EJB EquiposGrupoPersistenceRemote bean;
	
    /**
     * Default constructor. 
     */
    public EquiposGrupoBusiness() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarEquiposGrupo(int equipo,int grupo) {
    	return bean.agregarEquiposGrupo(equipo, grupo);
    }
    
    @Override
	public List<Equipo> obtenerEquiposPorGrupo(int grupo){
    	return bean.obtenerEquiposPorGrupo(grupo);
    }
    
    @Override
	public boolean eliminarEquiposGrupo(int equipo,int grupo) {
    	return bean.eliminarEquiposGrupo(equipo, grupo);
    }
    
    /*@Override
    public List<EquiposGrupo> obtenerEquiposGrupo(int idg){
    	return bean.obtenerEquiposGrupo(idg);
    }*/

}
