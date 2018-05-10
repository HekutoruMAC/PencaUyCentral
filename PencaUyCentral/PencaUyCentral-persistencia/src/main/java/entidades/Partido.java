package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "Partidos" database table.
 * 
 */
@Entity
@Table(name="\"Partidos\"")
@NamedQuery(name="Partido.findAll", query="SELECT p FROM Partido p")
public class Partido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;
/*
	@Column(name="\"EquipoGanadorId\"")
	private Integer equipoGanadorId;

	@Column(name="\"EquipoVisitaId\"")
	private Integer equipoVisitaId;

	@Column(name="\"EqupoLocalId\"")
	private Integer equpoLocalId;
*/
	@Column(name="\"Estado\"")
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Fecha\"")
	private Date fecha;

	@Column(name="\"GolesEquipoLocal\"")
	private Integer golesEquipoLocal;

	@Column(name="\"GolesEquipoVisita\"")
	private Integer golesEquipoVisita;

	@Column(name="\"GrupoId\"")
	private Integer grupoId;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="\"EquipoGanadorId\"")
	private Equipo equipoGanador;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="\"EquipoVisitaId\"")
	private Equipo equipoVisitante;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="\"EqupoLocalId\"")
	private Equipo equipoLocal;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="\"GrupoId\"")
	private Grupo grupoId_de_Partidos;

/*
	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="\"GrupoId2\"")
	private Grupo grupos2;
*/
	public Partido() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
/*
	public Integer getEquipoGanadorId() {
		return this.equipoGanadorId;
	}

	public void setEquipoGanadorId(Integer equipoGanadorId) {
		this.equipoGanadorId = equipoGanadorId;
	}

	public Integer getEquipoVisitaId() {
		return this.equipoVisitaId;
	}

	public void setEquipoVisitaId(Integer equipoVisitaId) {
		this.equipoVisitaId = equipoVisitaId;
	}

	public Integer getEqupoLocalId() {
		return this.equpoLocalId;
	}

	public void setEqupoLocalId(Integer equpoLocalId) {
		this.equpoLocalId = equpoLocalId;
	}
*/
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getGolesEquipoLocal() {
		return this.golesEquipoLocal;
	}

	public void setGolesEquipoLocal(Integer golesEquipoLocal) {
		this.golesEquipoLocal = golesEquipoLocal;
	}

	public Integer getGolesEquipoVisita() {
		return this.golesEquipoVisita;
	}

	public void setGolesEquipoVisita(Integer golesEquipoVisita) {
		this.golesEquipoVisita = golesEquipoVisita;
	}

	public Integer getGrupoId() {
		return this.grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}

	public Equipo getEquipoGanador() {
		return this.equipoGanador;
	}

	public void setEquipoGanador(Equipo equipoGanador) {
		this.equipoGanador = equipoGanador;
	}

	public Equipo getEquipoVisitante() {
		return this.equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public Equipo getEquipLocal() {
		return this.equipoLocal;
	}

	public void setEquipLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Grupo getGrupoId_de_Partidos() {
		return this.grupoId_de_Partidos;
	}

	public void setGrupoId_de_Partidos(Grupo grupoId_de_Partidos) {
		this.grupoId_de_Partidos = grupoId_de_Partidos;
	}
/*
	public Grupo getGrupoId2() {
		return this.grupos2;
	}

	public void setGrupoId2(Grupo grupos2) {
		this.grupos2 = grupos2;
	}
*/
}