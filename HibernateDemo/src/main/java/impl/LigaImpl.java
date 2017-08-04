package impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Liga;
import model.Torneo;

public class LigaImpl {

	private static EntityManager em;
	private Liga liga;
	private String allLigas = "SELECT * FROM liga";
	
	public LigaImpl() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateDemo");
		em = emf.createEntityManager();
	}
	
	public void createLiga(int id, String descripcion, String nombre, Torneo torneo) {
		em.getTransaction().begin();
		liga = new Liga(id, descripcion, nombre, torneo);
		em.persist(liga);
		em.getTransaction().commit();
	}
	
	public List<Liga> getLigas(){
		
		return em.createNativeQuery(allLigas, Liga.class).getResultList();
	}
	
	public Liga getLigaByID(int idLiga){
		return liga = em.find(Liga.class, idLiga);
	}
	
	public Liga updateLiga(Liga liga){
		em.getTransaction().begin();
		em.merge(liga);
		em.getTransaction().commit();
		return liga;
	}
	
	public Liga removeLiga(Liga liga){
		em.getTransaction().begin();
		em.remove(liga);
		em.getTransaction().commit();
		return liga;
	}
}
