package entidades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the "Organizaciones" database table.
 * 
 */
@Entity
@Table(name="\"Organizaciones\"")
@NamedQueries({
	@NamedQuery(name = "Organizacion.findAll", query="SELECT o FROM Organizacion o"),
	@NamedQuery(name = "Organizacion.findByNombre",query = "SELECT o FROM Organizacion o WHERE o.nombre = :nombre")
})
public class Organizacion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"Nombre\"")
	private String nombre;

	//bi-directional many-to-one association to Penca
	@OneToMany(mappedBy="organizacion")
	private List<Penca> pencas;

	public Organizacion() {
		this.pencas = new ArrayList<Penca>(); 
	}
	
	public Organizacion(String nombre) {
		this.nombre = nombre;
		this.pencas = new ArrayList<Penca>(); 
	}

	public Integer getId() {
		return this.id;
	}

	/*public void setId(Integer id) {
		this.id = id;
	}*/

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Penca> getPencas() {
		return this.pencas;
	}

	public void setPencas(List<Penca> pencas) {
		this.pencas = pencas;
	}

	public Penca addPenca(Penca penca) {
		getPencas().add(penca);
		penca.setOrganizacion(this);
		return penca;
	}

	public Penca removePenca(Penca penca) {
		getPencas().remove(penca);
		penca.setOrganizacion(null);
		return penca;
	}

}

