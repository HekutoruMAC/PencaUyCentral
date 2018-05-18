package beans.interfaces;

import java.util.List;

import javax.ejb.Local;

import entidades.TiposPremio;

@Local
public interface TiposPremioBusinessLocal {
	public boolean agregarTipoPremio(String nombre);
	public List<TiposPremio> obtenerTiposPremio();
	public TiposPremio obtenerTipoPremio(int id);
	public TiposPremio obtenerTipoPremioPorNombre(String nombre);
	public boolean eliminarTipoPremio(int id);
}
