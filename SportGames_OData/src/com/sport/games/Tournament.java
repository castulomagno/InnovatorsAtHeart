package com.sport.games;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the tournament database table.
 * 
 */
@Entity
@Table(name="tournament")
@NamedQuery(name="Tournament.findAll", query="SELECT t FROM Tournament t")
public class Tournament implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idTournament;

	@Column(nullable=false, length=50)
	private String name;

	//bi-directional many-to-one association to League
	@OneToMany(mappedBy="tournament")
	private List<League> leagues;

	public Tournament() {
	}

	public int getIdTournament() {
		return this.idTournament;
	}

	public void setIdTournament(int idTournament) {
		this.idTournament = idTournament;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<League> getLeagues() {
		return this.leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	public League addLeague(League league) {
		getLeagues().add(league);
		league.setTournament(this);

		return league;
	}

	public League removeLeague(League league) {
		getLeagues().remove(league);
		league.setTournament(null);

		return league;
	}

}