package beans.interfaces;

import java.util.List;

import javax.ejb.Local;

import entidades.Equipo;
import entidades.EquiposGrupo;

@Local
public interface EquiposGrupoBusinessLocal {
	public boolean agregarEquiposGrupo(int equipo,int grupo);
	public List<Equipo> obtenerEquiposPorGrupo(int grupo);
	public boolean eliminarEquiposGrupo(int equipo,int grupo);
	//public List<EquiposGrupo> obtenerEquiposGrupo(int idg);
}
