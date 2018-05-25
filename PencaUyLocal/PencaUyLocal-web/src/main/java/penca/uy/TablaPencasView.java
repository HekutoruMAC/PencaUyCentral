package penca.uy;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.interfaces.PencaPersistenceRemote;
import entidades.Penca;
 
@ManagedBean(name="TablaPencasView")
@ViewScoped
public class TablaPencasView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Penca> pencas;
     
    @EJB
    PencaPersistenceRemote pencaBean;
 
    @PostConstruct
    public void init() {
        pencas = pencaBean.obtenerPencas();
    }
     
    public List<Penca> getPencas() {
        return pencas;
    }
 
}
