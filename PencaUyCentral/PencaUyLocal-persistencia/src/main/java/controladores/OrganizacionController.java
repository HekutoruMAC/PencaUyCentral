package controladores;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Organizacion;
import entidades.Penca;

public class OrganizacionController {
	
	private static OrganizacionController instancia = null;
	
	@PersistenceContext(unitName="PencaUyCentral-persistencia")
	private static EntityManager manager;	
	
	private OrganizacionController() {
	}
	
	public static OrganizacionController getInstancia() {
		if (instancia == null) {
			instancia = new OrganizacionController();
		}
		return instancia;
	}	
	
	public boolean addOrganizacion(String nombre) {
		String query = "SELECT o FROM organizaciones o WHERE nombre = " + nombre;
		boolean existe = manager.createQuery(query).getSingleResult() != null;
		if (!existe) {		
			Organizacion org = new Organizacion(nombre);			
			manager.getTransaction().begin();
			manager.persist(org);
			manager.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}
		
	public Organizacion getOrganizacion(String nombre) {
		String query = "SELECT o FROM organizaciones o WHERE nombre = " + nombre;
		boolean existe = manager.createQuery(query).getSingleResult() != null;
		if (existe) {		
			return (Organizacion) manager.createQuery(query).getSingleResult();
		} else {
			return null;
		}
	}	
	
	public List<Penca> getPencasOrganizacion(String nombre) {
		return null;
	}
	
	public boolean addPencaEnOrganizacion(String nombre, Penca penca) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean removePencaEnOrganizacion(String nombre, Penca penca) {
		// TODO Auto-generated method stub
		return true;	
	}
	
	public boolean removeOrganizacion(String nombre) {
		// TODO Auto-generated method stub
		return true;	
	}
	

}
