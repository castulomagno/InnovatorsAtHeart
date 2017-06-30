package com.sport.games;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the standing database table.
 * 
 */
@Entity
@Table(name="standing")
@NamedQuery(name="Standing.findAll", query="SELECT s FROM Standing s")
public class Standing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idStanding;

	@Column(nullable=false)
	private int lose;

	@Column(nullable=false)
	private int tied;

	@Column(nullable=false)
	private int win;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="idLeague", nullable=false)
	private Team team1;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="idTeam", nullable=false)
	private Team team2;

	public Standing() {
	}

	public int getIdStanding() {
		return this.idStanding;
	}

	public void setIdStanding(int idStanding) {
		this.idStanding = idStanding;
	}

	public int getLose() {
		return this.lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getTied() {
		return this.tied;
	}

	public void setTied(int tied) {
		this.tied = tied;
	}

	public int getWin() {
		return this.win;
	}

	public void setWin(int win) {
		this.win = win;
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

}