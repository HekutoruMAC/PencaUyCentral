package beans.interfaces;

import java.util.List;

import javax.ejb.Local;

import entidades.Participante;
import entidades.Penca;

@Local
public interface ParticipantePersistenceLocal {	
	public boolean agregarParticipante(String usuario, int idp);	
	public List<Participante> obtenerParticipantes();
	public int obtenerParticipantePorUsuario(String usuario);
	public List<Participante> obtenerParticipantesPorPenca(int id);
	public Participante obtenerParticipante(int id);	
	public boolean eliminarParticipante(int id);
}
