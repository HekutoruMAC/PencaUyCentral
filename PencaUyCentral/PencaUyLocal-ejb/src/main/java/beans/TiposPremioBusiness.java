package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.interfaces.TiposPremioBusinessLocal;
import beans.interfaces.TiposPremioBusinessRemote;
import beans.interfaces.TiposPremioPersistenceRemote;
import entidades.TiposPremio;

/**
 * Session Bean implementation class TiposPremioBusiness
 */
@Stateless
@LocalBean
public class TiposPremioBusiness implements TiposPremioBusinessRemote, TiposPremioBusinessLocal {

	//private ITiposPremioPersistenceController itpc;
	
	@EJB TiposPremioPersistenceRemote bean;
	/**
     * Default constructor.
     * 
     */		
	public TiposPremioBusiness() {
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public boolean agregarTipoPremio(String nombre) {
		return bean.agregarTipoPremio(nombre);
	}
	
	@Override
	public List<TiposPremio> obtenerTiposPremio(){
		return bean.obtenerTiposPremio();
	}
	
	@Override	
	public TiposPremio obtenerTipoPremio(int id) {
		return bean.obtenerTipoPremio(id);
	}
	
	@Override
	public int obtenerTipoPremioPorNombre(String nombre) {
		return bean.obtenerTipoPremioPorNombre(nombre);
	}
	
	@Override
	public boolean eliminarTipoPremio(int id) {
		return bean.eliminarTipoPremio(id);
	}
    
    
    
    

}
