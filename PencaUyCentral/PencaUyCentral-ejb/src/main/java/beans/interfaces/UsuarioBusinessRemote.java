package beans.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entidades.Usuario;

@Remote
public interface UsuarioBusinessRemote {
	public boolean agregarUsuario(String nombre, String apellido, String email, String nickname, String password, Date fechaNac, String tipo);
	public Usuario obtenerUsuario(int id);	
	public int obtenerUsuarioPorNickname(String nickname);
	public List<Usuario> obtenerUsuarios();	
	public boolean usuarioValido(String usuario, String password);
	public boolean eliminarUsuario(int id);
}
