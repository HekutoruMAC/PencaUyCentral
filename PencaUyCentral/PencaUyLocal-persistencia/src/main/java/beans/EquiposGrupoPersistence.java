package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.interfaces.EquiposGrupoPersistenceLocal;
import beans.interfaces.EquiposGrupoPersistenceRemote;
import entidades.Equipo;
import entidades.EquiposGrupo;
import entidades.Grupo;

/**
 * Session Bean implementation class EquipoPersistence
 */
@Stateless
@LocalBean
public class EquiposGrupoPersistence implements EquiposGrupoPersistenceRemote, EquiposGrupoPersistenceLocal {
	
	@PersistenceContext(unitName="PencaUyCentral-persistencia")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public EquiposGrupoPersistence() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarEquiposGrupo(int equipo,int grupo) {
		//List<Equipo> le = em.createNamedQuery("Equipo.findByNombre", Equipo.class).setParameter("nombre", nombre).getResultList();
    	if (em.createQuery("SELECT e FROM "+ EquiposGrupo.class.getSimpleName()+" e WHERE e.equipo = " + equipo +" and e.grupo = "+grupo).getResultList().isEmpty()) {	
			EquiposGrupo eg = new EquiposGrupo();
			
			eg.setEquipo(em.find(Equipo.class, equipo));
			eg.setGrupo(em.find(Grupo.class, grupo));
			eg.setGolenContra(0);
			eg.setGolesFavor(0);
			eg.setPartidosEmpatados(0);
			eg.setPartidosGanados(0);
			eg.setPartidosJugados(0);
			eg.setPartidosPerdidos(0);
			eg.setPosicion(0);
			eg.setPuntos(0);
			
			em.persist(eg);
			return true;
		} else {
			return false;
		}		
	}
    
	@Override
	@SuppressWarnings("unchecked")
	public List<Equipo> obtenerEquiposPorGrupo(int grupo){
		List<EquiposGrupo> list = em.createQuery( "Select eg from "+ EquiposGrupo.class.getSimpleName()+" eg where eg.grupo = "+grupo).getResultList();
		if (!(list.isEmpty())) {
			EquiposGrupo eg;
			List<Equipo> listEquipo = new ArrayList<Equipo>();
			Iterator<EquiposGrupo> itList = list.iterator();
	        while(itList.hasNext()) {
	            eg = itList.next();
	            listEquipo.add(eg.getEquipo());
	            System.out.println("Agregué el equipo "+eg.getEquipo().getNombre()+" a la lista");
	        }
	        return listEquipo;
		}			
		else return null ;
	}
	
	/*@Override
	@SuppressWarnings("unchecked")
	public List<EquiposGrupo> obtenerEquiposGrupo(int idg){
		List<EquiposGrupo> list = em.createQuery( "Select eg from "+ EquiposGrupo.class.getSimpleName()+" eg where eg.grupo = "+ idg).getResultList();
		return list;
	}*/
	
	@SuppressWarnings("unchecked")
	public boolean eliminarEquiposGrupo(int equipo,int grupo) {
		List<EquiposGrupo> listEG = em.createQuery("SELECT e FROM "+ EquiposGrupo.class.getSimpleName()+" e WHERE e.equipo = " + equipo +" and e.grupo = "+grupo).getResultList();
		if(!(listEG.isEmpty())) {
			Equipo e = em.find(Equipo.class, equipo);
			e.removeEquiposGrupo(listEG.get(0));
			Grupo g = em.find(Grupo.class, grupo);
			g.removeEquiposGrupo(listEG.get(0));
			EquiposGrupo eg = em.find(EquiposGrupo.class, listEG.get(0).getId());
			em.remove(eg);
			return true;
		}
		else return false;
	 }
	

}
