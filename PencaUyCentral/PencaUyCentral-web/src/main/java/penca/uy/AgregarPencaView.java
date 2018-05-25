package penca.uy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import beans.interfaces.OrganizacionPersistenceRemote;
import beans.interfaces.PencaPersistenceRemote;
import entidades.Organizacion;

@ManagedBean(name = "AgregarPencaView")
public class AgregarPencaView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PencaPersistenceRemote pencaBean;
	
	
	@EJB
	OrganizacionPersistenceRemote organizacionBean;
	
	private String nombre;
	private String organizacion;
	private List<String> organizaciones;
	private Integer ida;
	private Integer estilo;
	private Integer precio;

	@PostConstruct
	public void init() {
		List<Organizacion> lista = organizacionBean.obtenerOrganizaciones();
		int i = lista.size();
		organizaciones = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			organizaciones.add(lista.get(j).getNombre());
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}
	
	public List<String> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<String> organizaciones) {
		this.organizaciones = organizaciones;
	}	

	public Integer getIda() {
		return this.ida;
	}
	
	public void setIda(Integer ida) {
		this.ida = ida;
	}
	
	public Integer getEstilo() {
		return this.estilo;
	}
	
	public void setEstilo(Integer estilo) {
		this.estilo = estilo;
	}
	
	public Integer getPrecio() {
		return this.precio;
	}
	
	public void setPrecio(Integer precio) {
		this.precio= precio;
	}
	
	public void save() {
		int ido = organizacionBean.obtenerOrganizacionPorNombre(organizacion);
		if (pencaBean.agregarPenca(nombre, ido, ida, estilo, precio)){				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Se ha agregado la Penca " + nombre + " al la organizacion " + organizacion));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("La Penca " + nombre + " ya existe en la organizacion " + organizacion));
			}
	}

}
