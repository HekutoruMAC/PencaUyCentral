package beans.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entidades.Equipo;

@Remote
public interface EquipoBusinessRemote {
	public boolean agregarEquipo(String nombre);
	public Equipo obtenerEquipo(int id);	
	public int obtenerEquipoPorNombre(String nombre);
	public List<Equipo> obtenerEquipos();
	public boolean eliminarEquipo(int id);
}