package com.atworksys.onelynk1.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.atworksys.onelynk1.Entity.User;

@Stateless
public class UsersEJB {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("AtWorkSysDB");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = this.em.getTransaction();

	// private List User;
	public UsersEJB() {
	}

	public List<User> getAllUsers() {
		Query qryObj = em.createNamedQuery("findAll", User.class);
		return (List<User>) qryObj.getResultList();
	}

	public User getXUser(String username) {
		try {
			System.out.println("Username is " + username);
			System.out.println(this.em.toString());
			Query qryObj = this.em.createNamedQuery("getUserNyName", User.class);
			User userList = (User) qryObj.getResultList();
			return userList;
		} catch (Exception e) {

			User u = new User();
			e.printStackTrace();
			return u;

		}
	}

	public User getXEmail(String email) {
		Query qryObj = em.createNamedQuery("getuserByEmail", User.class);
		qryObj.setParameter("email", email);
		return (User) qryObj.getSingleResult();

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveUser(User user) {
		try {

			tx.begin();
			this.em.persist(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User mergeUser(User user) {

		em.merge(em.find(User.class, user.getUserId()));
		return user;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteUsers(User user) throws Exception {
		try {
			tx.begin();
			em.remove(user);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User editUsers(User user) throws Exception {
		try {
			tx.begin();
			em.merge(user);
			tx.commit();
			return user;
		} catch (Exception e) {

			System.out.println(e);
			return null;
		}
	}

}
