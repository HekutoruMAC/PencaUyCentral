package controladores;

import java.util.List;

import controladores.interfaces.ITiposPremioPersistenceController;
import javax.ejb.EJB;

import beans.TiposPremioPersistence;
import beans.interfaces.TiposPremioPersistenceLocal;
import beans.interfaces.TiposPremioPersistenceRemote;
import entidades.TiposPremio;

public class TiposPremioPersistenceController implements ITiposPremioPersistenceController {

	@EJB TiposPremioPersistenceRemote beanTiposPremio; 

	public TiposPremioPersistenceController() {
	}
	
	public boolean agregarTipoPremio(String nombre) {
		//beanTiposPremio = new TiposPremioPersistence();
		System.out.println("TiposPremioPersistenceController");
		return beanTiposPremio.agregarTipoPremio(nombre);
	}
	
	public List<TiposPremio> obtenerTiposPremio(){
		return beanTiposPremio.obtenerTiposPremio();
	}
	
	public TiposPremio obtenerTipoPremio(int id) {
		return beanTiposPremio.obtenerTipoPremio(id);
	}
	
	public boolean eliminarTipoPremio(int id) {
		return beanTiposPremio.eliminarTipoPremio(id);
	}
	
	
	
}
