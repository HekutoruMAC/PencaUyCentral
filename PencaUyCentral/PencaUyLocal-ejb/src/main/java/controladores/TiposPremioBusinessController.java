package controladores;

import javax.ejb.EJB;

import beans.interfaces.TiposPremioBusinessRemote;
import controladores.interfaces.ITiposPremioBusinessController;

public class TiposPremioBusinessController implements ITiposPremioBusinessController {
	
	@EJB
	private TiposPremioBusinessRemote bean;
	
	public TiposPremioBusinessController() {		
	}
	
	public boolean agregarTipoPremio(String nombre) {
		return true;
	}

}
