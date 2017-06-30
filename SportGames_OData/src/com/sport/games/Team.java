package com.sport.games;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@Table(name="team")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idTeam;

	@Column(nullable=false, length=50)
	private String name;

	//bi-directional many-to-one association to Match
	@OneToMany(mappedBy="team1")
	private List<Match> matchs1;

	//bi-directional many-to-one association to Match
	@OneToMany(mappedBy="team2")
	private List<Match> matchs2;

	//bi-directional many-to-one association to Match
	@OneToMany(mappedBy="team3")
	private List<Match> matchs3;

	//bi-directional many-to-one association to Player
	@OneToMany(mappedBy="team")
	private List<Player> players;

	//bi-directional many-to-one association to Standing
	@OneToMany(mappedBy="team1")
	private List<Standing> standings1;

	//bi-directional many-to-one association to Standing
	@OneToMany(mappedBy="team2")
	private List<Standing> standings2;

	//bi-directional many-to-one association to League
	@ManyToOne
	@JoinColumn(name="idLeague", unique=true, nullable=false)
	private League league;

	public Team() {
	}

	public int getIdTeam() {
		return this.idTeam;
	}

	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Match> getMatchs1() {
		return this.matchs1;
	}

	public void setMatchs1(List<Match> matchs1) {
		this.matchs1 = matchs1;
	}

	public Match addMatchs1(Match matchs1) {
		getMatchs1().add(matchs1);
		matchs1.setTeam1(this);

		return matchs1;
	}

	public Match removeMatchs1(Match matchs1) {
		getMatchs1().remove(matchs1);
		matchs1.setTeam1(null);

		return matchs1;
	}

	public List<Match> getMatchs2() {
		return this.matchs2;
	}

	public void setMatchs2(List<Match> matchs2) {
		this.matchs2 = matchs2;
	}

	public Match addMatchs2(Match matchs2) {
		getMatchs2().add(matchs2);
		matchs2.setTeam2(this);

		return matchs2;
	}

	public Match removeMatchs2(Match matchs2) {
		getMatchs2().remove(matchs2);
		matchs2.setTeam2(null);

		return matchs2;
	}

	public List<Match> getMatchs3() {
		return this.matchs3;
	}

	public void setMatchs3(List<Match> matchs3) {
		this.matchs3 = matchs3;
	}

	public Match addMatchs3(Match matchs3) {
		getMatchs3().add(matchs3);
		matchs3.setTeam3(this);

		return matchs3;
	}

	public Match removeMatchs3(Match matchs3) {
		getMatchs3().remove(matchs3);
		matchs3.setTeam3(null);

		return matchs3;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player addPlayer(Player player) {
		getPlayers().add(player);
		player.setTeam(this);

		return player;
	}

	public Player removePlayer(Player player) {
		getPlayers().remove(player);
		player.setTeam(null);

		return player;
	}

	public List<Standing> getStandings1() {
		return this.standings1;
	}

	public void setStandings1(List<Standing> standings1) {
		this.standings1 = standings1;
	}

	public Standing addStandings1(Standing standings1) {
		getStandings1().add(standings1);
		standings1.setTeam1(this);

		return standings1;
	}

	public Standing removeStandings1(Standing standings1) {
		getStandings1().remove(standings1);
		standings1.setTeam1(null);

		return standings1;
	}

	public List<Standing> getStandings2() {
		return this.standings2;
	}

	public void setStandings2(List<Standing> standings2) {
		this.standings2 = standings2;
	}

	public Standing addStandings2(Standing standings2) {
		getStandings2().add(standings2);
		standings2.setTeam2(this);

		return standings2;
	}

	public Standing removeStandings2(Standing standings2) {
		getStandings2().remove(standings2);
		standings2.setTeam2(null);

		return standings2;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

}