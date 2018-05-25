package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="\"Usuarios\"")
@NamedQueries({
	@NamedQuery(name = "Usuario.findAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findByNickname",query = "SELECT u FROM Usuario u WHERE u.nickname = :nickname"),
	@NamedQuery(name = "Usuario.findByCredentials",query = "SELECT u FROM Usuario u WHERE u.nickname = :nickname AND u.password = :password")
})
public class Usuario  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"Nombre\"")
	private String nombre;
	
	@Column(name="\"Apellido\"")
	private String apellido;
	
	@Column(name="\"Email\"")
	private String email;
	
	@Column(name="\"Nickname\"")
	private String nickname;
	
	@Column(name="\"Password\"")
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Nacimiento\"")
	private Date nacimiento;
	
	@Column(name="\"Tipo\"")
	private String tipo;
	
	@Column(name="\"Puntos\"")
	private Integer puntos;
		
	public Usuario(){		
	}
	
	public Integer getId() {
		return id;
	}

	/*public void setId(Integer id) {
		this.id = id;
	}*/

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

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	
}
