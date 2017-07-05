package com.atworksys.onelynk1.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the userrole database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "findAllRoles", query = "SELECT u FROM Userrole u"),
		@NamedQuery(name = "getUserByRoles", query = "SELECT u FROM Userrole u WHERE u.rolename= :rolename"),
		@NamedQuery(name = "getroleByUserId", query = "select ur FROM User u JOIN u.userroles ur WHERE u.userId =:userid") })
@XmlRootElement(name = "UserRole")
public class Userrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	private int roleId;

	private String rolename;

	// bi-directional many-to-one association to User
	@XmlTransient
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Userrole(int roleId, String rolename, User user) {

		this.roleId = roleId;
		this.rolename = rolename;
		this.user = user;
	}

	public Userrole() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@XmlInverseReference(mappedBy = "user")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}