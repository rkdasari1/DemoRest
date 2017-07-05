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

import com.atworksys.onelynk1.Entity.Userrole;;

@Stateless
public class UserRoleEjb {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("AtWorkSysDB");

	// @PersistenceContext(unitName="AtWorkSysDB")
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = this.em.getTransaction();

	// private List User;
	public UserRoleEjb() {
	}

	public List<Userrole> getAllUserRole() {
		Query qryObj = em.createNamedQuery("findAllRoles", Userrole.class);
		return (List<Userrole>) qryObj.getResultList();
	}

	public List<Userrole> getUserRoles(Long userId) {

		List<Userrole> userList = new ArrayList<Userrole>();
		try {
			System.out.println("UserID for getting Role is " + userId);
			Query qryObj = this.em.createQuery("getroleByUserId", Userrole.class).setParameter("userid", userId);
			userList = (List<Userrole>) qryObj.getResultList();
			System.out.println("Size of the userList is" + userList.size());
			for (Userrole uh : userList) {
				System.out.println("UserRole Returned is " + uh.getRolename() + " for Id" + uh.getRoleId());
			}
			return userList;
		} catch (Exception e) {

			e.printStackTrace();
			return userList;
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveUser(Userrole userRole) {
		try {

			tx.begin();
			this.em.persist(userRole);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Userrole mergeUser(Userrole userRole) {

		em.merge(em.find(Userrole.class, userRole.getRoleId()));
		return userRole;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteUsers(Userrole userRole) throws Exception {
		try {
			tx.begin();
			em.remove(userRole);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Userrole editUsers(Userrole userRole) throws Exception {
		try {
			tx.begin();
			em.merge(userRole);
			tx.commit();
			return userRole;
		} catch (Exception e) {

			System.out.println(e);
			return null;
		}
	}
}
