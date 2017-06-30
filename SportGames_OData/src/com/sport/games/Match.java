package com.sport.games;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the matchs database table.
 * 
 */
@Entity
@Table(name="matchs")
@NamedQuery(name="Match.findAll", query="SELECT m FROM Match m")
public class Match implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idMatch;

	@Column(name="cod_match", nullable=false, length=20)
	private String codMatch;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	@Column(name="team_a_points", nullable=false)
	private int teamAPoints;

	@Column(name="team_b_points", nullable=false)
	private int teamBPoints;

	@Column(nullable=false)
	private Time time;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="team_a", nullable=false)
	private Team team1;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="team_b", nullable=false)
	private Team team2;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="idLeague", nullable=false)
	private Team team3;

	public Match() {
	}

	public int getIdMatch() {
		return this.idMatch;
	}

	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}

	public String getCodMatch() {
		return this.codMatch;
	}

	public void setCodMatch(String codMatch) {
		this.codMatch = codMatch;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTeamAPoints() {
		return this.teamAPoints;
	}

	public void setTeamAPoints(int teamAPoints) {
		this.teamAPoints = teamAPoints;
	}

	public int getTeamBPoints() {
		return this.teamBPoints;
	}

	public void setTeamBPoints(int teamBPoints) {
		this.teamBPoints = teamBPoints;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Team getTeam1() {
		return this.team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return this.team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public Team getTeam3() {
		return this.team3;
	}

	public void setTeam3(Team team3) {
		this.team3 = team3;
	}

}