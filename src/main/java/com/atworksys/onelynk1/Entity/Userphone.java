package com.atworksys.onelynk1.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;



/**
 * The persistent class for the userphone database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "findAllphones", query = "select up FROM Userphone up"),
		@NamedQuery(name = "findByuserIdAndType", query = "select up FROM User u JOIN u.userphones up WHERE u.userId = :userid and up.type=:type"),
		@NamedQuery(name = "findByuserId", query = "select up FROM User u JOIN u.userphones up WHERE u.userId = :userid") })
@XmlRootElement(name = "Userphone")
public class Userphone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private String phoneNumber;

	private String type;

	// bi-directional many-to-one association to User
	@XmlTransient
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Userphone(int id, String createdBy, Date createdOn, String phoneNumber, String type, User user) {

		this.id = id;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.user = user;
	}

	public Userphone() {
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlInverseReference(mappedBy = "user")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}