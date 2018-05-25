package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Grupos" database table.
 * 
 */
@Entity
@Table(name="\"Grupos\"")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	
	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;
/*
	@Column(name="\"FaseId\"")
	private Integer faseId;
*/
	@Column(name="\"Nombre\"")
	private String nombre;

	//bi-directional many-to-one association to Equipos_Grupo
	@OneToMany(mappedBy="grupo")
	private List<EquiposGrupo> equiposGrupo;

	//bi-directional many-to-one association to Fase
	@ManyToOne
	@JoinColumn(name="\"FaseId\"")
	private Fase fase;

	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="grupo")
	private List<Partido> partidos;

/*
	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="grupo2")
	private List<Partido> partidos2;
*/
	public Grupo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
/*
	public Integer getFaseId() {
		return this.faseId;
	}
	public void setFaseId(Integer faseId) {
		this.faseId = faseId;
	}
*/
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<EquiposGrupo> getEquiposGrupos() {
		return this.equiposGrupo;
	}

	public void setEquiposGrupos(List<EquiposGrupo> equiposGrupo) {
		this.equiposGrupo = equiposGrupo;
	}

	public EquiposGrupo addEquiposGrupo(EquiposGrupo equiposGrupo) {
		getEquiposGrupos().add(equiposGrupo);
		equiposGrupo.setGrupo(this);
		return equiposGrupo;
	}

	public EquiposGrupo removeEquiposGrupo(EquiposGrupo equiposGrupo) {
		getEquiposGrupos().remove(equiposGrupo);
		equiposGrupo.setGrupo(null);
		return equiposGrupo;
	}

	public Fase getFase() {
		return this.fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public List<Partido> getPartidos() {
		return this.partidos;
	}

	public void setPartido(List<Partido> partidos) {
		this.partidos = partidos;
	}

	public void addPartido(Partido partido) {
		getPartidos().add(partido);
		partido.setGrupo(this);
	}

	public void removePartido(Partido partido) {
		getPartidos().remove(partido);
		partido.setGrupo(null);
	}

/*
	public List<Partido> getPartidos2() {
		return this.partidos2;
	}
	public void setPartidos2(List<Partido> partidos2) {
		this.partidos2 = partidos2;
	}
	public Partido addPartidos2(Partido partidos2) {
		getPartidos2().add(partidos2);
		partidos2.setGrupo2(this);
		return partidos2;
	}
	public Partido removePartidos2(Partido partidos2) {
		getPartidos2().remove(partidos2);
		partidos2.setGrupo2(null);
		return partidos2;
	}
*/
}