package com.atworksys.onelynk1.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the userhobby database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "findAllHobbies", query = "SELECT u FROM Userhobby u"),
		@NamedQuery(name = "findHobbyByUserId", query = "select up FROM User u JOIN u.userhobbies up WHERE u.userId = :userid") })
@XmlRootElement(name = "UserHobby")
@XmlAccessorType(XmlAccessType.FIELD)
public class Userhobby implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private String hobby;

	// bi-directional many-to-one association to User
	@XmlTransient
	@XmlInverseReference(mappedBy = "user")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id",referencedColumnName = "user_id")
	private User user;

	public Userhobby(int id, String createdBy, Date createdOn, String hobby, User user) {
		this.id = id;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.hobby = hobby;
		this.user = user;
	}

	public Userhobby() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}


	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}