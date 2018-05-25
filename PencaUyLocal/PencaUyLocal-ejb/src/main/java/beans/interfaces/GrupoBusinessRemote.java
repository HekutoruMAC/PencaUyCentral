package beans.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entidades.EquiposGrupo;
import entidades.Grupo;
import entidades.Partido;

@Remote
public interface GrupoBusinessRemote {
	public boolean agregarGrupo(String nombre,int fase);
	public Grupo obtenerGrupo(int id);
	public int obtenerGrupoPorNombreYFase(String nombre,int id);
	public boolean eliminarGrupo(int id);
	public List<Grupo> obtenerGruposPorFase(int id);
	public List<Partido> obtenerPartidosGrupo(int id);
	public List<EquiposGrupo> obtenerEquiposGrupo(int id);
}
