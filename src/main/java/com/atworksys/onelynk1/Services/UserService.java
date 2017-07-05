package com.atworksys.onelynk1.Services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.atworksys.onelynk1.Entity.User;
import com.atworksys.onelynk1.Entity.Userhobby;
import com.atworksys.onelynk1.beans.UserHobbyEjb;
import com.atworksys.onelynk1.beans.UserPhoneEjb;
import com.atworksys.onelynk1.beans.UsersEJB;;

@Path("/Users")
@Stateless
public class UserService {

	@EJB
	UsersEJB uet;
	@EJB
	UserHobbyEjb uhe;
	@EJB
	UserPhoneEjb upe;

	public UserService() {
		this.uet = new UsersEJB();
		this.uhe = new UserHobbyEjb();
		this.upe = new UserPhoneEjb();
	}

	@GET
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		List<User> allUserList = new ArrayList<User>();
		try {
			allUserList = uet.getAllUsers();
			for (User checkUser : allUserList) {
				System.out
						.println("User ID is " + checkUser.getUserId() + " and His name is " + checkUser.getUsername());
			}
			return allUserList;

		} catch (Exception e) {
			e.printStackTrace();
			return allUserList;

		}
	}

	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("application/json")
	public void addUser(User user) {

		try {
			// user.setEmail("test@testmail.com)");
			// uadd.setPassword("123456");
			// user.setUsername("testperson");

			System.out.println("User Got with ID " + user.getEmail());
			System.out.println("User Got with ID " + user.getPassword());
			System.out.println("User Got with ID " + user.getUsername());
			uet.saveUser(user);
			System.out.println("User Added to the table ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@GET
	@Path("/{id}/UserHobby")
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Userhobby> getHobbies(@PathParam("id") Long userId) {
		System.out.println("Getting Hobbies for userID " + userId);
		List<Userhobby> retList = uhe.getUserHobbies(userId);
		for (Userhobby checkList : retList) {
			System.out.println(
					"UserHobby Returned is " + checkList.getHobby() + " for userID " + checkList.getUser().getUserId());

		}
		return retList;

	}

	@DELETE
	@Path("/{id}/UserPhone")
	// @Produces("application/json")
	// @Consumes("application/json")
	public void deletePhone(@PathParam("id") Long userId, @QueryParam("type") String type) {
		try {
			// Userphone up = new Userphone();
			// user.setEmail("test@testmail.com)");
			// uadd.setPassword("123456");
			// user.setUsername("testperson");

			System.out.println("User ID from request is" + userId);
			System.out.println("Phone Type from request is" + type);
			upe.deletephone(userId, type);
			System.out.println("Done deleting");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PUT
	@Path("/{id}/UserPhone")
	// @Produces("application/json")
	// @Consumes("application/json")
	public void updateUserPhone(@PathParam("id") Long userId, @QueryParam("type") String type,
			@QueryParam("phoneNumber") String phoneNumber) {
		try {
			// Userphone up = new Userphone();
			// user.setEmail("test@testmail.com)");
			// uadd.setPassword("123456");
			// user.setUsername("testperson");

			System.out.println("User ID from request is " + userId);
			System.out.println("Phone Type from request is " + type);
			System.out.println("Phone number from request is " + phoneNumber);

			upe.updatePhone(userId, type, phoneNumber);
			System.out.println("update is performed");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
