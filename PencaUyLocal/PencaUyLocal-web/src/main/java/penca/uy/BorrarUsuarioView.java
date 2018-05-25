package penca.uy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.interfaces.UsuarioPersistenceRemote;
import entidades.Usuario;

@ManagedBean(name="BorrarUsuarioView")

@ViewScoped
public class BorrarUsuarioView implements Serializable {

	private static final long serialVersionUID = 1L;
	private String usuario;
	private List<String> usuarios;

	@EJB
	UsuarioPersistenceRemote usuarioBean;

	@PostConstruct
	public void init() {
		List<Usuario> lista = usuarioBean.obtenerUsuarios();
		int i = lista.size();
		usuarios = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			usuarios.add(lista.get(j).getNickname());
		}
	}

	public List<String> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void borrar() {
		FacesMessage msg;
		if (usuario != null) {
			System.out.println("el Usuario no es null, es " + usuario);
			int idu = usuarioBean.obtenerUsuarioPorNickname(usuario);
			usuarioBean.eliminarUsuario(idu);			
			msg = new FacesMessage("Se borró el Usuario " + usuario);
			
		} else {
			System.out.println("el Usuario es null");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "El Usuario no es válido.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}

