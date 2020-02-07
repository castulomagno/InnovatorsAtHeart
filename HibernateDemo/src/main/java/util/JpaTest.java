package util;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import impl.LigaImpl;
import impl.TorneoImpl;
import model.Liga;
import model.Torneo;

public class JpaTest {
	
//	private static EntityManager em;

	public static void main(String[] args) {
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateDemo");
//		em = emf.createEntityManager();
//		createTorneo(0, "UFC");
//		createTorneo(0, "NHL");
		
		Torneo torneo = new Torneo(3, "");
		/*
		createLiga(0, "NBA 2017", "Season 2017 NBA", 1);
		createLiga(0, "MLB 2017", "Season 2017 MLB", 2);
		createLiga(0, "NFL 2017-2018", "Season 2017-2018 NFL", 3);
		*/
		/*
		createLiga(0, "UFC 125", "UFC 125 Event", t1);
		createLiga(0, "UFC 234", "Event 234 UFC", t1);
		*/
		
		TorneoImpl ti = new TorneoImpl();
		List<Torneo> tl = ti.getTorneos();
		/*
		for(Torneo t : tl)
		{
			System.out.println(" >>> "+t.getIdTorneo()+" -- "+t.getNombre());
			t.setNombre(t.getNombre()+"@");
			
			System.out.println(" ****** "+ti.updateTorneo(t).getNombre());			
		}
		*/
	//	t1 = ti.removeTorneo(ti.getTorneoByID(5));		
	//	System.out.println(" /// "+t1.getNombre());
		/*
		List<Liga> ll = t1.getLigas();
		for (Liga li : ll) {
			System.out.println(" ----- "+li.getIdLiga()+" -- "+li.getNombre()+" -- "+li.getDescripcion());
		}
		*/
		
	//	Liga liga = new Liga(0, "test liga", "NFL Test", torneo);
		LigaImpl ligaImp = new LigaImpl();
	//	ligaImp.createLiga(0, "test liga", "NFL Test", torneo);
		
		List<Liga> ligas = ligaImp.getLigas();
		for (Liga li : ligas) {
			System.out.println(" ----- "+li.getIdLiga()+" -- "+li.getNombre()+" -- "+li.getDescripcion());
		}
		
		Liga l = ligaImp.getLigaByID(4);
		System.out.println("/*/*/ "+l.getIdLiga()+" /* "+l.getNombre()+" /* "+l.getDescripcion()+" /* "+l.getTorneo().getNombre());
		/*
		l.setNombre("NFL 2018-2019");
		l.setDescripcion("Season 2018-2019 NFL");
		
		ligaImp.updateLiga(l);
		*/
		ligaImp.removeLiga(l);
	}
/*	
	private static void createTorneo(int id, String nombre) {
		em.getTransaction().begin();
		Torneo tor = new Torneo(id, nombre);
		em.persist(tor);
		em.getTransaction().commit();
	}
	
	private static void createLiga(int idLiga, String nombre, String descripcion, Torneo torneo) {
		em.getTransaction().begin();
		
		Liga lig = new Liga(idLiga, descripcion, nombre, torneo);
		em.persist(lig);
		em.getTransaction().commit();
	}
*/
}
