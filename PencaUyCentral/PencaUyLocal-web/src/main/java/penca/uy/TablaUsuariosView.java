package penca.uy;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import beans.interfaces.UsuarioPersistenceRemote;
import entidades.Usuario;
 
@ManagedBean(name="TablaUsuariosView")
//@ViewScoped
public class TablaUsuariosView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;
     
    @EJB
    UsuarioPersistenceRemote usuarioBean;
 
    @PostConstruct
    public void init() {
        usuarios = usuarioBean.obtenerUsuarios();
    }
     
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
 
}
