package beans.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entidades.Fase;
import entidades.Torneo;

@Local
public interface TorneoBusinessLocal {
	public boolean agregarTorneo(String nombre,String tipo, Date comienzo);
    public Torneo obtenerTorneo(int id);
    public boolean eliminarTorneo(int id);
    public boolean agregarFase(int id);
    public List<Torneo> obtenerTodos();
    public int obtenerTorneoPorNombre(String nombre);
    public List<Fase> obtenerFasesTorneo(int id);
    public boolean eliminarTodosTorneo();
}
