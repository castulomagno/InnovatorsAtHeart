package impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Torneo;

public class TorneoImpl {

	private static EntityManager em;
	private Torneo tor;
	private String allTorneos = "SELECT * FROM torneo";
	private String torneoByID = "SELECT * FROM torneo";
	
	
	public TorneoImpl() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateDemo");
		em = emf.createEntityManager();
	}

	public void createTorneo(int id, String nombre) {
		em.getTransaction().begin();
		tor = new Torneo(id, nombre);
		em.persist(tor);
		em.getTransaction().commit();
	}
	
	public List<Torneo> getTorneos(){
		//em.getTransaction().begin();
		return em.createNativeQuery(allTorneos, Torneo.class).getResultList();
	}
	
	public Torneo getTorneoByID(int idTorneo){
		return tor = em.find(Torneo.class, idTorneo);
	}	
	
	public Torneo updateTorneo(Torneo t){
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}
	
	public Torneo removeTorneo(Torneo t){
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		return t;
	}
}
