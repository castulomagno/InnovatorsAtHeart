package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the liga database table.
 * 
 */
@Entity
@Table(name="liga")
@NamedQuery(name="Liga.findAll", query="SELECT l FROM Liga l")
public class Liga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idLiga;

	@Column(nullable=false, length=50)
	private String descripcion;

	@Column(nullable=false, length=20)
	private String nombre;

	//bi-directional many-to-one association to Torneo
	@ManyToOne
	@JoinColumn(name="idtorneo", nullable=false)
	private Torneo torneo;

	public Liga() {
	}

	public Liga(int idLiga, String descripcion, String nombre, Torneo torneo) {
		super();
		this.idLiga = idLiga;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.torneo = torneo;
	}

	public int getIdLiga() {
		return this.idLiga;
	}

	public void setIdLiga(int idLiga) {
		this.idLiga = idLiga;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Torneo getTorneo() {
		return this.torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

}