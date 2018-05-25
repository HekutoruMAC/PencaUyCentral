package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "TiposPremio" database table.
 * 
 */
@Entity
@Table(name="\"TiposPremio\"")
@NamedQueries({
	@NamedQuery(name = "TiposPremio.findAll", query="SELECT t FROM TiposPremio t"),
	@NamedQuery(name = "TiposPremio.findByNombre",query = "SELECT t FROM TiposPremio t WHERE t.nombre = :nombre")
})
public class TiposPremio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"Nombre\"")
	private String nombre;


	public TiposPremio() {
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

}