package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the torneo database table.
 * 
 */
@Entity
@Table(name="torneo")
@NamedQuery(name="Torneo.findAll", query="SELECT t FROM Torneo t")
public class Torneo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idTorneo;

	@Column(nullable=false, length=20)
	private String nombre;

	//bi-directional many-to-one association to Liga
	@OneToMany(mappedBy="torneo")
	private List<Liga> ligas;

	public Torneo() {
	}

	public Torneo(int idTorneo, String nombre) {
		super();
		this.idTorneo = idTorneo;
		this.nombre = nombre;
	}

	public int getIdTorneo() {
		return this.idTorneo;
	}

	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Liga> getLigas() {
		return this.ligas;
	}

	public void setLigas(List<Liga> ligas) {
		this.ligas = ligas;
	}

	public Liga addLiga(Liga liga) {
		getLigas().add(liga);
		liga.setTorneo(this);

		return liga;
	}

	public Liga removeLiga(Liga liga) {
		getLigas().remove(liga);
		liga.setTorneo(null);

		return liga;
	}
	/*
	public List<Torneo> getTorneos(){
		Query q = em.createNamedQuery("Torneo.findAll");
		List<AuthorValue> authors = q.getResultList();
	}
*/
}