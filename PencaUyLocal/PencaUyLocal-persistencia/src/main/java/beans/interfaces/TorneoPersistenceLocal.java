package beans.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entidades.Fase;
import entidades.Torneo;

@Local
public interface TorneoPersistenceLocal {
	public boolean crearTorneo(String nombre,String tipo, Date comienzo);
    public Torneo obtenerTorneo(int id);
    public boolean eliminarTorneo(int id);
    public boolean agregarFase(int id);
    public List<Torneo> obtenerTodos();
    public List<Fase> obtenerFasesTorneo(int id);
    public int obtenerTorneoPorNombre(String nombre);
    public boolean eliminarTodosTorneo();
}
