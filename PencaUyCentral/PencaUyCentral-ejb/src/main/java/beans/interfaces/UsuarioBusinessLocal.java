package beans.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entidades.Usuario;

@Local
public interface UsuarioBusinessLocal {
	public boolean agregarUsuario(String nombre, String apellido, String email, String nickname, String password, Date fechaNac, String tipo);
	public Usuario obtenerUsuario(int id);	
	public int obtenerUsuarioPorNickname(String nickname);
	public List<Usuario> obtenerUsuarios();	
	public boolean usuarioValido(String usuario, String password);
	public boolean eliminarUsuario(int id);
}
