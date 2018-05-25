package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the "Torneos" database table.
 * 
 */
@Entity
@Table(name="\"Torneos\"")
@NamedQuery(name="Torneo.findAll", query="SELECT t FROM Torneo t")
public class Torneo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Comienzo\"")
	private Date comienzo;

	@Column(name="\"Nombre\"")
	private String nombre;

	@Column(name="\"Tipo\"")
	private String tipo;

	//bi-directional many-to-one association to Fase
	@OneToMany(mappedBy="torneo")
	private List<Fase> fases;

	public Torneo() {
	}
	
	public Torneo(String nombre, String tipo, Date comienzo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.comienzo = comienzo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getComienzo() {
		return this.comienzo;
	}

	public void setComienzo(Date comienzo) {
		this.comienzo = comienzo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Fase> getFases() {
		return this.fases;
	}

	public void setFases(List<Fase> fases) {
		this.fases = fases;
	}

	public Fase addFase(Fase fase) {
		getFases().add(fase);
		fase.setTorneo(this);

		return fase;
	}

	public Fase removeFase(Fase fase) {
		getFases().remove(fase);
		fase.setTorneo(null);

		return fase;
	}
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }
	
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final Torneo other = (Torneo) obj;
	        if (!Objects.equals(this.id, other.id)) {
	            return false;
	        }
	        return true;
	    }

}