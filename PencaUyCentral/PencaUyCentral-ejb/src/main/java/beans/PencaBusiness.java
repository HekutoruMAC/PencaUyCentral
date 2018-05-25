package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.interfaces.PencaBusinessLocal;
import beans.interfaces.PencaBusinessRemote;
import beans.interfaces.PencaPersistenceRemote;
import entidades.Organizacion;
import entidades.Participante;
import entidades.Penca;

/**
 * Session Bean implementation class PencaBusiness
 */
@Stateless
@LocalBean
public class PencaBusiness implements PencaBusinessRemote, PencaBusinessLocal {
	
	@EJB PencaPersistenceRemote bean;

    /**
     * Default constructor. 
     */
    public PencaBusiness() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarPenca(String nombre, int ido, int ida, int estilo, int precio) {
    	return bean.agregarPenca(nombre, ido, ida, estilo, precio);
    }
    
    @Override
	public Penca obtenerPenca(int id) {
    	return bean.obtenerPenca(id);    	
    }
    
    @Override
	public int obtenerPencaPorNombre(String nombre) {
    	return bean.obtenerPencaPorNombre(nombre);
    }
    
    @Override
	public int obtenerPencaPorNombreYOrganizacion(int id, String nombre) {
    	return bean.obtenerPencaPorNombreYOrganizacion(id, nombre);
    }
    
    @Override
	public List<Penca> obtenerPencas(){
    	return bean.obtenerPencas();
    }
    
    @Override
	public List<Penca> obtenerPencasPorOrganizacion(int id){
    	return bean.obtenerPencasPorOrganizacion(id);
    }
    
    @Override
	public List<Participante> obtenerParticipantesPenca(int id){
    	return bean.obtenerParticipantesPenca(id);
    }
    
    @Override
	public boolean eliminarPenca(int id) {
    	return bean.eliminarPenca(id);
    }

}
