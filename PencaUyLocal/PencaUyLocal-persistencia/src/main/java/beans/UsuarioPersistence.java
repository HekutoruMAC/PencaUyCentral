package beans;

import beans.interfaces.UsuarioPersistenceLocal;
import beans.interfaces.UsuarioPersistenceRemote;
import entidades.Organizacion;
import entidades.Usuario;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UsuarioPersistence
 */
@Stateless
@LocalBean
public class UsuarioPersistence implements UsuarioPersistenceRemote, UsuarioPersistenceLocal {

	@PersistenceContext(unitName="PencaUyLocal-persistencia")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UsuarioPersistence() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean agregarUsuario(String nombre, String apellido, String email, String nickname, String password, Date fechaNac, String tipo) {		
		if (em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nickname).getResultList().isEmpty()) {		
			Usuario nu = new Usuario();
			nu.setNombre(nombre);
			nu.setApellido(apellido);
			nu.setEmail(email);
			nu.setNickname(nickname);
			nu.setPassword(password);
			nu.setNacimiento(fechaNac);
			nu.setTipo(tipo);
			//nu.setPuntos(puntos);
			em.persist(nu);
			return true;
		} else {
			return false;
		}		
	}	
	
	@Override
	public Usuario obtenerUsuario(int id) {
		return (Usuario) em.find(Usuario.class, id);
	}
	
	@Override
	public int obtenerUsuarioPorNickname(String nickname) {
		List<Usuario> lu = em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nickname).getResultList();
		if (lu.isEmpty()) {
			return -1;
		}
		else {
			return lu.get(0).getId();
		}
	}
	
	@Override
	public List<Usuario> obtenerUsuarios(){
		return (List<Usuario>) em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
	}
	
	@Override
	public boolean usuarioValido(String usuario, String password) {
		List<Usuario> lu = em.createNamedQuery("Usuario.findByCredentials", Usuario.class).setParameter("nickname", usuario).setParameter("password", password).getResultList();
		if (lu.isEmpty()) {
			return false;
		}
		else {
			return true;
		}		
	}   
	
	@Override
	public boolean eliminarUsuario(int id) {
		Usuario u = em.find(Usuario.class, id);
		if (u != null) {				
			em.remove(u);
			return true;
		} else {
			return false;
		}	
	}

}
