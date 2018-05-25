package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Pencas" database table.
 * 
 */
@Entity
@Table(name="\"Pencas\"")
@NamedQueries({
	@NamedQuery(name="Penca.findAll", query="SELECT p FROM Penca p"),
	@NamedQuery(name = "Penca.findByNombre", query = "SELECT p FROM Penca p WHERE p.nombre = :nombre"),
	@NamedQuery(name = "Penca.findByNombreAndOrganizacion", query = "SELECT p FROM Penca p WHERE p.nombre = :nombre AND p.organizacion = :organizacion"),
	@NamedQuery(name = "Penca.findByOrganizacion", query = "SELECT p FROM Penca p WHERE p.organizacion = :organizacion"),
	//@NamedQuery(name = "Penca.findByOrganizacionAndAdministrador", query = "SELECT p FROM Penca p WHERE p.organizacion = :organizacion AND p.ida = :ida")
})
public class Penca implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"Nombre\"")
	private String nombre;
	
	//bi-directional many-to-one association to Participante
	@OneToMany(mappedBy="penca")
	private List<Participante> participantes;

	//bi-directional many-to-one association to Organizacione
	@ManyToOne
	@JoinColumn(name="\"OrganizacionId\"")
	private Organizacion organizacion;

	@Column(name="\"AdministradorId\"")
	private int ida;
	
	@Column(name="\"EstiloId\"")
	private int estilo;
	
	@Column(name="\"Precio\"")
	private int precio;
	
	public Penca() {
	}

	public Integer getId() {
		return this.id;
	}

	/*public void setId(Integer id) {
		this.id = id;
	}*/
	
	public int getIda() {
		return this.ida;
	}
	
	public void setIda(int ida) {
		this.ida = ida;
	}
	
	public int getEstilo() {
		return this.estilo;
	}
	
	public void setEstilo(int estilo) {
		this.estilo = estilo;
	}
	
	public int getPrecio() {
		return this.precio;
	}
	
	public void setPrecio(int precio) {
		this.precio= precio;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Participante> getParticipantes() {
		return this.participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public Participante addParticipante(Participante participante) {
		getParticipantes().add(participante);
		participante.setPenca(this);
		return participante;
	}

	public Participante removeParticipante(Participante participante) {
		getParticipantes().remove(participante);
		participante.setPenca(null);
		return participante;
	}

	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

}