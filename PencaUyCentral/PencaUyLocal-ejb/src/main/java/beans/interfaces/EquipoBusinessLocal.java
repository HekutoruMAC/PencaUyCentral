package beans.interfaces;

import java.util.List;

import javax.ejb.Local;

import entidades.Equipo;
import entidades.EquiposGrupo;

@Local
public interface EquipoBusinessLocal {
	public boolean agregarEquipo(String nombre);
	public Equipo obtenerEquipo(int id);	
	public int obtenerEquipoPorNombre(String nombre);
	public List<Equipo> obtenerEquipos();
	public boolean eliminarEquipo(int id);
	public List<EquiposGrupo> obtenerGruposEquipo(int id);
}
