package control;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import entity.UserAccount;
import betatrade.DBManagerLocal;

@Path("/betatrade")
public class ControlManager{

	@SuppressWarnings("unused")
	@Context
	private UriInfo context;
	
	

	@EJB
	private DBManagerLocal dbMg;

	/**
	 * Default constructor.
	 */
	public ControlManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Path("/getUserAccount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserAccount getUserAccount(@QueryParam("username")@DefaultValue("") String username) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		System.out.println("yes");
		UserAccount result = null;
		result = dbMg.getUserAccount(username);
		System.out.print("success");
		return result;

	}
	
	@Path("/createUserAccount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserAccount addUserAccount(
			@QueryParam("username")@DefaultValue("") String username,
			@QueryParam("password")@DefaultValue("") String password,
			@QueryParam("email")@DefaultValue("") String email,
			@QueryParam("phone")@DefaultValue("") String phone,
			@QueryParam("cashBalance")@DefaultValue("") String cashBalance
			) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		System.out.println("yes");
		
		UserAccount userAccount = dbMg.createUserAccount(username, password, email, phone, cashBalance);
		
		return userAccount;

	}
	
	@Path("/deleteUserAccount")
	@GET
	public String deleteUserAccount(@QueryParam("username")@DefaultValue("") String username) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		System.out.println("yes");
		String result = dbMg.deleteUserAccount(username);
		return result;
	}
	
	@Path("/updateUserAccount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserAccount updateUserAccount(
			@QueryParam("username")@DefaultValue("") String username,
			@QueryParam("password")@DefaultValue("") String password,
			@QueryParam("email")@DefaultValue("") String email,
			@QueryParam("phone")@DefaultValue("") String phone,
			@QueryParam("cashBalance")@DefaultValue("") String cashBalance
			) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		System.out.println("yes");
		
		UserAccount userAccount = dbMg.updateUserAccount(username, password, email, phone, cashBalance);
		
		return userAccount;

	}
	
}
