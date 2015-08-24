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

import entity.Strategy;
import entity.UserAccount;
import betatrade.DBManagerLocal;

@Path("/betatrade")
public class ControlManager {

	@SuppressWarnings("unused")
	@Context
	private UriInfo context;

	@EJB
	private DBManagerLocal dbMg;
	
	private StrategySchedulerInterface scheduler;

	/**
	 * Default constructor.
	 */
	public ControlManager() {
		// TODO Auto-generated constructor stub
		
		//instantiate scheduler
		scheduler = new StrategyScheduler();
	}

	@Path("/getUserAccount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserAccount getUserAccount(
			@QueryParam("username") @DefaultValue("") String username) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		System.out.println("yes");
		UserAccount result = null;
		result = dbMg.getUserAccount(username);
		System.out.print("success");
		return result;

	}
	
	@Path("/validateUserAccount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserAccount validateUserAccount(
			@QueryParam("username") @DefaultValue("") String username,
			@QueryParam("password") @DefaultValue("") String password ) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		System.out.println("yes");
		UserAccount result = null;
		result = dbMg.getUserAccount(username);
		if(result == null){
			return null;
		}
		if(password.equals(result.getUsername())){
			System.out.print("Login success");
			return result;
		}
		else{
			System.out.print("Login failed");
			return null;
		}
		

	}

	@Path("/createUserAccount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserAccount addUserAccount(
			@QueryParam("username") @DefaultValue("") String username,
			@QueryParam("password") @DefaultValue("") String password,
			@QueryParam("email") @DefaultValue("") String email,
			@QueryParam("phone") @DefaultValue("") String phone) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		System.out.println("yes");
		String cashBalance = "0";
		UserAccount userAccount = dbMg.createUserAccount(username, password,
				email, phone, cashBalance);

		return userAccount;

	}

	@Path("/deleteUserAccount")
	@GET
	public String deleteUserAccount(
			@QueryParam("username") @DefaultValue("") String username) {
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
			@QueryParam("username") @DefaultValue("") String username,
			@QueryParam("password") @DefaultValue("") String password,
			@QueryParam("email") @DefaultValue("") String email,
			@QueryParam("phone") @DefaultValue("") String phone,
			@QueryParam("cashBalance") @DefaultValue("0") String cashBalance) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		System.out.println("yes");

		UserAccount userAccount = dbMg.updateUserAccount(username, password,
				email, phone, cashBalance);

		return userAccount;

	}

	// Strategy
	@Path("/createStrategy")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// status might not be put,
	// quantity & period: int,
	// capital, intialCapital, transactionLimit, profitExitPosition,
	// lossExitPosition, multiplier: double,
	public Strategy createStrategy(
			@QueryParam("username") @DefaultValue("") String username,
			@QueryParam("strategyName") @DefaultValue("") String strategyName,
			@QueryParam("status") @DefaultValue("live") String status,
			@QueryParam("symbol") @DefaultValue("") String symbol,
			@QueryParam("quantity") @DefaultValue("") String quantity,
			@QueryParam("period") @DefaultValue("") String period,
			@QueryParam("initialCapital") @DefaultValue("") String initialCapital,
			@QueryParam("transactionLimit") @DefaultValue("") String transactionLimit,
			@QueryParam("profitExitPosition") @DefaultValue("") String profitExitPosition,
			@QueryParam("lossExitPosition") @DefaultValue("") String lossExitPosition,
			@QueryParam("multiplier") @DefaultValue("") String multiplier) {

		Strategy strategy = null;

		strategy = dbMg.createStrategy(username, strategyName, status, symbol,
				quantity,  period, initialCapital, transactionLimit,
				profitExitPosition, lossExitPosition, multiplier);
		

		scheduler.appendStrategyToList(strategy);

		return strategy;

	}

	@Path("/deleteStrategy")
	@GET
	//set status to be death
	//status might not be put,
	// strategyId, quantity & period: int,
	// capital, intialCapital, transactionLimit, profitExitPosition,
	// lossExitPosition, multiplier: double,
	public String deleteStrategy(@QueryParam("strategyId") String strategyId,
			@QueryParam("username") @DefaultValue("") String username){
		String result = null;
		
		result = dbMg.deleteStrategy(strategyId, username);
		return result;
		
	}
	
	@Path("/getLiveStrategyListByUsername")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Strategy> getLiveStrategyListByUsername(
			@QueryParam("username") @DefaultValue("") String username){
		List<Strategy> l = null;
		l = dbMg.getLiveStrategyListByUsername(username);
		return l;
	}
	
	@Path("/getDeathStrategyListByUsername")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Strategy> getDeathStrategyListByUsername(
			@QueryParam("username") @DefaultValue("") String username){
		List<Strategy> l = null;
		l = dbMg.getDeathStrategyListByUsername(username);
		return l;
	}
	
	@Path("/getAllStrategyListByUsername")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Strategy> getAllStrategyListByUsername(
			@QueryParam("username") @DefaultValue("") String username){
		List<Strategy> l = null;
		l = dbMg.getAllStrategyListByUsername(username);
		return l;
	}

	
}
