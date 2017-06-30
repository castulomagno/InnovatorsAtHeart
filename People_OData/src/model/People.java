package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the people database table.
 * 
 */
@Entity
@Table(name="people")
@NamedQuery(name="People.findAll", query="SELECT p FROM People p")
public class People implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String phone;

	@Column(nullable=false, length=50)
	private String email;

	@Column(nullable=false, length=20)
	private String lastName;

	@Column(nullable=false, length=20)
	private String name;

	public People() {
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}