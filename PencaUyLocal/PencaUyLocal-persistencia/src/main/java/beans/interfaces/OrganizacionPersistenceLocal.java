package beans.interfaces;

import java.util.List;

import javax.ejb.Local;

import entidades.Organizacion;
import entidades.Participante;
import entidades.Penca;
import entidades.TiposPremio;

@Local
public interface OrganizacionPersistenceLocal {
	public boolean agregarOrganizacion(String nombre);
	public Organizacion obtenerOrganizacion(int id);	
	public int obtenerOrganizacionPorNombre(String nombre);
	public List<Organizacion> obtenerOrganizaciones();
	public List<Penca> obtenerPencasOrganizacion(int id);	
	public boolean eliminarOrganizacion(int id);
	
}
