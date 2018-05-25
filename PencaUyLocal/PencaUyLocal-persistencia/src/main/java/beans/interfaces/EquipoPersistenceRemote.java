package beans.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entidades.Equipo;
import entidades.EquiposGrupo;
import entidades.Grupo;

@Remote
public interface EquipoPersistenceRemote {	
	public boolean agregarEquipo(String nombre);
	public Equipo obtenerEquipo(int id);
	public int obtenerEquipoPorNombre(String nombre);
	public List<Equipo> obtenerEquipos();
	public boolean eliminarEquipo(int id);
	public List<EquiposGrupo> obtenerGruposEquipo(int id);
}
