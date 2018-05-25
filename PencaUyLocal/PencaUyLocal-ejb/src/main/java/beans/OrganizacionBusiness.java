package beans;

import beans.interfaces.OrganizacionBusinessLocal;
import beans.interfaces.OrganizacionBusinessRemote;
import beans.interfaces.OrganizacionPersistenceRemote;
import entidades.Organizacion;
import entidades.Penca;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class OrganizacionBusiness
 */
@Stateless
@LocalBean
public class OrganizacionBusiness implements OrganizacionBusinessRemote, OrganizacionBusinessLocal {
	
	@EJB OrganizacionPersistenceRemote bean;
    /**
     * Default constructor. 
     */
    public OrganizacionBusiness() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarOrganizacion(String nombre) {
		return bean.agregarOrganizacion(nombre);
	}
	
    @Override
	public Organizacion obtenerOrganizacion(int id) {
    	return bean.obtenerOrganizacion(id);
    }
    
    @Override
	public int obtenerOrganizacionPorNombre(String nombre) {
    	return bean.obtenerOrganizacionPorNombre(nombre);
    }
    
    @Override
	public List<Organizacion> obtenerOrganizaciones(){
    	return bean.obtenerOrganizaciones();
    }
    
    @Override
	public List<Penca> obtenerPencasOrganizacion(int id){
    	return bean.obtenerPencasOrganizacion(id);
    }
    
    @Override
	public boolean eliminarOrganizacion(int id) {
    	return bean.eliminarOrganizacion(id);
    }
	
}
