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
 * The persistent class for the player database table.
 * 
 */
@Entity
@Table(name="player")
@NamedQuery(name="Player.findAll", query="SELECT p FROM Player p")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idPlayer;

	@Column(name="last_name", nullable=false, length=50)
	private String lastName;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false)
	private int number;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="idTeam", nullable=false)
	private Team team;

	public Player() {
	}

	public int getIdPlayer() {
		return this.idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}