package beans.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entidades.Equipo;
import entidades.Partido;

@Local
public interface PartidoPersistenceLocal {	
	public boolean agregarPartido(int elocal, int evisita, int grupo, Date fecha);
	public Partido obtenerPartido(int id);
	public boolean eliminarPartido(int id);
	public int obtenerPartidoPorGrupoEquipoLocalYEquipoVisitante(int idg, int idel, int idev);
	public List<Equipo> obtenerEquiposVisitantesPartidosConEquipoLocal(int idel, int idg);
	public boolean actualizarPartido(int idg, int idel, int idev, int golesel, int golesev, int idgana);
	public Date obtenerFechaPartido(int idp);
}
