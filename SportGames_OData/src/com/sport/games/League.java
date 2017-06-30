package com.sport.games;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the league database table.
 * 
 */
@Entity
@Table(name="league")
@NamedQuery(name="League.findAll", query="SELECT l FROM League l")
public class League implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idLeague;

	@Column(nullable=false, length=50)
	private String description;

	@Column(nullable=false, length=50)
	private String name;

	//bi-directional many-to-one association to Tournament
	@ManyToOne
	@JoinColumn(name="idTournament", nullable=false)
	private Tournament tournament;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="league")
	private List<Team> teams;

	public League() {
	}

	public int getIdLeague() {
		return this.idLeague;
	}

	public void setIdLeague(int idLeague) {
		this.idLeague = idLeague;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tournament getTournament() {
		return this.tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {
		getTeams().add(team);
		team.setLeague(this);

		return team;
	}

	public Team removeTeam(Team team) {
		getTeams().remove(team);
		team.setLeague(null);

		return team;
	}

}