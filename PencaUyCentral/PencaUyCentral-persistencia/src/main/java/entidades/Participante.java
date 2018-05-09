package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Participantes" database table.
 * 
 */
@Entity
@Table(name="\"Participantes\"")
@NamedQuery(name="Participante.findAll", query="SELECT p FROM Participante p")
public class Participante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;
/*
	@Column(name="\"PencaId\"")
	private Integer pencaId;
*/
	@Column(name="\"Puntos\"")
	private Integer puntos;

	@Column(name="\"Usuario\"")
	private String usuario;

	//bi-directional many-to-one association to Penca
	@ManyToOne
	@JoinColumn(name="\"PencaId\"")
	private Penca penca;

	public Participante() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
/*
	public Integer getPencaId() {
		return this.pencaId;
	}

	public void setPencaId(Integer pencaId) {
		this.pencaId = pencaId;
	}
*/
	public Integer getPuntos() {
		return this.puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Penca getPenca() {
		return this.penca;
	}

	public void setPenca(Penca penca) {
		this.penca = penca;
	}

}