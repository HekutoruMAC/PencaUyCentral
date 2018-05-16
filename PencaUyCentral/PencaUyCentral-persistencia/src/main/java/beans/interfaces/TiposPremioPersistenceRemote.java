package beans.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entidades.TiposPremio;

@Remote
public interface TiposPremioPersistenceRemote {	
	public boolean agregarTipoPremio(String nombre);
	public List<TiposPremio> obtenerTiposPremio();
	public TiposPremio obtenerTipoPremio(int id);
	public TiposPremio obtenerTipoPremioPorNombre(String nombre);
	public boolean eliminarTipoPremio(int id);
}
