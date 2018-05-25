package beans.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entidades.Fase;
import entidades.Grupo;

@Remote
public interface FasePersistenceRemote {
	public boolean crearFase(String nombre, int idt);
    public Fase obtenerFase(int id);
    public boolean eliminarFase(int id);
    public List<Fase> obtenerFasesPorTorneo(int id);
    public int obtenerFasePorNombreYTorneo(int id,String nombre);
    public List<Grupo> obtenerGruposFase(int id);
}
