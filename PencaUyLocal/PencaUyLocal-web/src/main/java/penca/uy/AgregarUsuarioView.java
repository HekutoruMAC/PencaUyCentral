package penca.uy;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.interfaces.UsuarioPersistenceRemote;

@ManagedBean(name = "AgregarUsuarioView")
public class AgregarUsuarioView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioPersistenceRemote usuarioBean;

	private String nombre;
	private String apellido;
	private Date nacimiento;
	private String email;
	private String nickname;
	private String password;
	private String tipo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void save() {
		Date hoy = new Date();
		if (nacimiento.after(hoy)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("El nacimiento del Usuario no puede ser posterior a hoy"));
		} else {
			if (usuarioBean.agregarUsuario(nombre, apellido, email, nickname, password, nacimiento, tipo)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Se ha agregado el usuario " + nombre + " " + apellido + " con nickname " + nickname));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ya existe un usuario con ese nickname"));
			}
		}
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

}
