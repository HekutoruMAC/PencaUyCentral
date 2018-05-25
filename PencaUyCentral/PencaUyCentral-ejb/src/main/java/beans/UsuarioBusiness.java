package beans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.interfaces.UsuarioBusinessLocal;
import beans.interfaces.UsuarioBusinessRemote;
import beans.interfaces.UsuarioPersistenceRemote;
import entidades.Usuario;

/**
 * Session Bean implementation class UsuariosBusiness
 */
@Stateless
@LocalBean
public class UsuarioBusiness implements UsuarioBusinessRemote, UsuarioBusinessLocal {

	@EJB UsuarioPersistenceRemote bean;
	
    /**
     * Default constructor. 
     */
    public UsuarioBusiness() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarUsuario(String nombre, String apellido, String email, String nickname, String password, Date fechaNac, String tipo) {
    	return bean.agregarUsuario(nombre, apellido, email, nickname, password, fechaNac, tipo);
    }
    
    @Override
	public Usuario obtenerUsuario(int id) {
    	return bean.obtenerUsuario(id);
    }
    
    @Override
	public int obtenerUsuarioPorNickname(String nickname) {
    	return bean.obtenerUsuarioPorNickname(nickname);
    }
    
    @Override
	public List<Usuario> obtenerUsuarios() {
    	return bean.obtenerUsuarios();
    }
    
    @Override
	public boolean usuarioValido(String usuario, String password) {
    	/*Usuario u = obtenerUsuarioPorNickname(usuario);
    	System.out.println("Entro a usuario valido");
    	if (u != null) {
    		if (u.getPassword().equals(password)) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	} else {
    		return false;
    	}*/
    	return bean.usuarioValido(usuario, password);
    }
    
    @Override
	public boolean eliminarUsuario(int id) {
    	return bean.eliminarUsuario(id);
    }

}
