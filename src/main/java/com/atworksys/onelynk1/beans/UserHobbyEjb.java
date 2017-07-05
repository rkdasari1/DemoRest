package com.atworksys.onelynk1.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.atworksys.onelynk1.Entity.Userhobby;

@Stateless
public class UserHobbyEjb {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("AtWorkSysDB");

	// @PersistenceContext(unitName="AtWorkSysDB")
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = this.em.getTransaction();

	// private List User;
	public UserHobbyEjb() {
	}

	public List<Userhobby> getAllUserHobby() {
		Query qryObj = em.createNamedQuery("findAllHobbies", Userhobby.class);
		return (List<Userhobby>) qryObj.getResultList();
	}

	public List<Userhobby> getUserHobbies(Long userId) {

		List<Userhobby> userList = new ArrayList<Userhobby>();
		try {
			System.out.println("UserID for getting Hobby is " + userId);
			Query qryObj = this.em.createNamedQuery("findHobbyByUserId", Userhobby.class).setParameter("userid",
					userId);
			userList = (List<Userhobby>) qryObj.getResultList();
			System.out.println("Size of the userList is" + userList.size());
			for (Userhobby uh : userList) {
				System.out.println("UserHobby Returned is " + uh.getHobby() + " for Id" + uh.getUser().getPassword());
			}
			return userList;
		} catch (Exception e) {

			e.printStackTrace();
			return userList;
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveUser(Userhobby userHobby) {
		try {

			tx.begin();
			this.em.persist(userHobby);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Userhobby mergeUser(Userhobby userHobby) {

		em.merge(em.find(Userhobby.class, userHobby.getId()));
		return userHobby;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteUsers(Userhobby userHobby) throws Exception {
		try {
			tx.begin();
			em.remove(userHobby);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Userhobby editUsers(Userhobby userHobby) throws Exception {
		try {
			tx.begin();
			em.merge(userHobby);
			tx.commit();
			return userHobby;
		} catch (Exception e) {

			System.out.println(e);
			return null;
		}
	}
}
