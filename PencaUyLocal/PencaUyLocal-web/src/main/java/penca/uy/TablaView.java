package penca.uy;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import beans.interfaces.TorneoPersistenceRemote;
import entidades.Torneo;
 
@ManagedBean(name="TablaView")
public class TablaView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Torneo> torneos;
     
    @EJB
    TorneoPersistenceRemote torneoBean;
 
    @PostConstruct
    public void init() {
        torneos = torneoBean.obtenerTodos();
    }
     
    public List<Torneo> getTorneos() {
        return torneos;
    }
 
}