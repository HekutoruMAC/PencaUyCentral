package beans.interfaces;

import java.util.List;

import javax.ejb.Local;

import entidades.Organizacion;
import entidades.Participante;
import entidades.Penca;

@Local
public interface PencaPersistenceLocal {	
	public boolean agregarPenca(String nombre, int ido, int ida, int estilo, int precio);	
	public Penca obtenerPenca(int id);	
	public int obtenerPencaPorNombre(String nombre);
	public int obtenerPencaPorNombreYOrganizacion(int id, String nombre);
	public List<Penca> obtenerPencas();	
	public List<Penca> obtenerPencasPorOrganizacion(int id);
	public List<Participante> obtenerParticipantesPenca(int id);
	public boolean eliminarPenca(int id);
}
