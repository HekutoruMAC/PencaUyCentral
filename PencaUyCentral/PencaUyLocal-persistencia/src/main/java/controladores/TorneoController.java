package controladores;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import entidades.Organizacion;
import entidades.Torneo;

public class TorneoController {

	private static TorneoController instancia = null;

	@PersistenceContext(unitName = "PencaUyCentral-persistencia")
	private static EntityManagerFactory factory;
	private static EntityManager manager;

	private TorneoController() {
	}

	public static TorneoController getInstancia() {
		if (instancia == null) {
			instancia = new TorneoController();
		}
		return instancia;
	}

}
