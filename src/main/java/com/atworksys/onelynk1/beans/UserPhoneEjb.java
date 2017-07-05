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

import com.atworksys.onelynk1.Entity.Userphone;;

@Stateless
public class UserPhoneEjb {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("AtWorkSysDB");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = this.em.getTransaction();

	// private List User;
	public UserPhoneEjb() {
	}

	public List<Userphone> getAllUserPhone() {
		Query qryObj = em.createNamedQuery("findAllphones", Userphone.class);
		return (List<Userphone>) qryObj.getResultList();
	}

	public List<Userphone> getUserPhones(Long userId) {

		List<Userphone> userList = new ArrayList<Userphone>();
		try {
			System.out.println("UserID for getting phone is " + userId);
			Query qryObj = this.em.createNamedQuery("findByuserId", Userphone.class).setParameter("userid", userId);
			userList = (List<Userphone>) qryObj.getResultList();
			System.out.println("Size of the userList is" + userList.size());
			for (Userphone uh : userList) {
				// UserPhone uh = (UserPhone) userList.get(i);
				System.out.println("UserPhone Returned is" + uh.getPhoneNumber());
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return userList;
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveUserphone(Userphone userphone) {
		try {

			tx.begin();
			this.em.persist(userphone);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updatePhone(Long userId, String type, String phoneNo) {
		try {
			Query qryObj = this.em.createNamedQuery("findByuserId", Userphone.class).setParameter("userid", userId);

			System.out.println("Done querying now reading results");
			List<Userphone> resultphones = (List<Userphone>) qryObj.getResultList();
			System.out.println("Result size is" + resultphones.size());
			for (Userphone updatePhone : resultphones) {
				System.out.println("Updating record for id " + updatePhone.getId());
				EntityTransaction tx = this.em.getTransaction();
				tx.begin();
				updatePhone.setPhoneNumber(phoneNo);
				updatePhone.setType(type);
				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deletephone(Long userId, String type) throws Exception {
		try {
			// Userphone userPh = new Userphone();
			Query qryObj = this.em.createNamedQuery("findByuserIdAndType", Userphone.class)
					.setParameter("userid", userId).setParameter("type", type);

			System.out.println("Done querying now reading results");
			List<Userphone> resultphones = (List<Userphone>) qryObj.getResultList();
			System.out.println("Result size is" + resultphones.size());
			for (Userphone delPhone : resultphones) {
				System.out.println("deleteing record for id" + delPhone.getId());
				EntityTransaction tx = this.em.getTransaction();
				tx.begin();
				this.em.remove(delPhone);
				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Userphone editUsers(Userphone userPhone) throws Exception {
		try {
			tx.begin();
			em.merge(userPhone);
			tx.commit();
			return userPhone;
		} catch (Exception e) {

			System.out.println(e);
			return null;
		}
	}

}
