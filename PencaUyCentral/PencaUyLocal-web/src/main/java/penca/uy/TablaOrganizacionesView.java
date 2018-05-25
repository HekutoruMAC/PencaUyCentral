package penca.uy;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import beans.interfaces.OrganizacionPersistenceRemote;
import entidades.Organizacion;
 
@ManagedBean(name="TablaOrganizacionesView")
//@ViewScoped
public class TablaOrganizacionesView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Organizacion> organizaciones;
     
    @EJB
    OrganizacionPersistenceRemote organizacionBean;
 
    @PostConstruct
    public void init() {
        organizaciones = organizacionBean.obtenerOrganizaciones();
    }
     
    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }
 
}
