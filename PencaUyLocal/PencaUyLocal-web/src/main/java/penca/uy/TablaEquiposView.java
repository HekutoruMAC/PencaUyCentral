package penca.uy;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import beans.interfaces.EquipoPersistenceRemote;
import entidades.Equipo;
 
@ManagedBean(name="TablaEquiposView")
//@ViewScoped
public class TablaEquiposView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Equipo> equipos;
     
    @EJB
    EquipoPersistenceRemote equipoBean;
 
    @PostConstruct
    public void init() {
        equipos = equipoBean.obtenerEquipos();
    }
     
    public List<Equipo> getEquipos() {
        return equipos;
    }
 
}
