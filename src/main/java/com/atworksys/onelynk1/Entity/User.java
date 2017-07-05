package com.atworksys.onelynk1.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "getUserNyName", query = "SELECT u FROM User u WHERE u.username = :username"),
		@NamedQuery(name = "getuserByEmail", query = "SELECT u FROM User u WHERE u.email = :email") })
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)

public class User implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "user_id")
	private int userId;

	private String email;

	private String password;

	private String username;

	// bi-directional many-to-one association to Userhobby
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Userhobby> userhobbies;

	// bi-directional many-to-one association to Userphone
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)

	private List<Userphone> userphones;

	// bi-directional many-to-one association to Userrole
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)

	private List<Userrole> userroles;

	public User() {
	}

	public User(int userId, String email, String password, String username, List<Userhobby> userhobbies,
			List<Userphone> userphones, List<Userrole> userroles) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.username = username;
		this.userhobbies = userhobbies;
		this.userphones = userphones;
		this.userroles = userroles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Userhobby> getUserhobbies() {
		return userhobbies;
	}

	public void setUserhobbies(List<Userhobby> userhobbies) {
		this.userhobbies = userhobbies;
	}

	public List<Userphone> getUserphones() {
		return userphones;
	}

	public void setUserphones(List<Userphone> userphones) {
		this.userphones = userphones;
	}

	public List<Userrole> getUserroles() {
		return userroles;
	}

	public void setUserroles(List<Userrole> userroles) {
		this.userroles = userroles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}