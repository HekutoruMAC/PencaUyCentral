package penca.uy;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import beans.interfaces.UsuarioPersistenceRemote;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB UsuarioPersistenceRemote bean;
	
	private String usuario;
	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		if (bean.usuarioValido(usuario, password)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
			return "usuarioValido"; 
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Verifique usuario y/o password."));
			return "usuarioInvalido";			
		}
	}
	
	public void verificarSesion() {
		try {
			ExternalContext extctx = FacesContext.getCurrentInstance().getExternalContext();
			String user = (String) extctx.getSessionMap().get("user");
			if (user == null) {
				extctx.redirect("./../errorlogin.xhtml");
			}
		} catch (Exception e) {
			
		}
	}
	
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		/*try {
			ExternalContext extctx = FacesContext.getCurrentInstance().getExternalContext();
			extctx.invalidateSession();
			extctx.redirect("./../login.xhtml");
		} catch (Exception e) {
			
		}*/		
	}
	
}
