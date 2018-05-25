package beans.interfaces;

import java.util.List;

import javax.ejb.Local;

import entidades.Fase;
import entidades.Grupo;

@Local
public interface FasePersistenceLocal {
	public boolean crearFase(String nombre, int idt);
    public Fase obtenerFase(int id);
    public boolean eliminarFase(int id);
    public List<Fase> obtenerFasesPorTorneo(int id);
    public int obtenerFasePorNombreYTorneo(int id,String nombre);
    public List<Grupo> obtenerGruposFase(int id);
}
