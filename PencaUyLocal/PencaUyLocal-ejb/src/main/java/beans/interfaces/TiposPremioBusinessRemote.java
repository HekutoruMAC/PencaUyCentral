package beans.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entidades.TiposPremio;

@Remote
public interface TiposPremioBusinessRemote {
	public boolean agregarTipoPremio(String nombre);
	public List<TiposPremio> obtenerTiposPremio();
	public TiposPremio obtenerTipoPremio(int id);
	public int obtenerTipoPremioPorNombre(String nombre);
	public boolean eliminarTipoPremio(int id);
}
