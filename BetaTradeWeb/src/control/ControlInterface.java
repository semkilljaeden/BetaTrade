package control;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import entity.Instrument;
import entity.Strategy;
import entity.Transaction;
import entity.UserAccount;

public interface ControlInterface {

	// @Path("/getUserAccount")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public UserAccount getUserAccount(
			@QueryParam("username") @DefaultValue("") String username);

	// @Path("/createUserAccount")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public UserAccount addUserAccount(
			@QueryParam("username") @DefaultValue("") String username,
			@QueryParam("password") @DefaultValue("") String password,
			@QueryParam("email") @DefaultValue("") String email,
			@QueryParam("phone") @DefaultValue("") String phone,
			@QueryParam("cashBalance") @DefaultValue("") String cashBalance);

	// @Path("/deleteUserAccount")
	// @GET
	public String deleteUserAccount(
			@QueryParam("username") @DefaultValue("") String username);

	// @Path("/updateUserAccount")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public UserAccount updateUserAccount(
			@QueryParam("username") @DefaultValue("") String username,
			@QueryParam("password") @DefaultValue("") String password,
			@QueryParam("email") @DefaultValue("") String email,
			@QueryParam("phone") @DefaultValue("") String phone,
			@QueryParam("cashBalance") @DefaultValue("") String cashBalance);

	// Not Implemented Yet

	// Strategy//
	// @Path("/createStrategy")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
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
			@QueryParam("capital") @DefaultValue("") String capital,
			@QueryParam("period") @DefaultValue("") String period,
			@QueryParam("initialCapital") @DefaultValue("") String initialCapital,
			@QueryParam("transactionLimit") @DefaultValue("") String transactionLimit,
			@QueryParam("profitExitPosition") @DefaultValue("") String profitExitPosition,
			@QueryParam("lossExitPosition") @DefaultValue("") String lossExitPosition,
			@QueryParam("multiplier") @DefaultValue("") String multiplier);

	// @Path("/updateStrategy")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// status might not be put,
	// strategyId, quantity & period: int,
	// capital, intialCapital, transactionLimit, profitExitPosition,
	// lossExitPosition, multiplier: double,
	public Strategy updateStrategy(
			@QueryParam("strategyId") String strategyId,
			@QueryParam("username") @DefaultValue("") String username,
			@QueryParam("strategyName") @DefaultValue("") String strategyName,
			@QueryParam("status") @DefaultValue("live") String status,
			@QueryParam("symbol") @DefaultValue("") String symbol,
			@QueryParam("quantity") @DefaultValue("") String quantity,
			@QueryParam("capital") @DefaultValue("") String capital,
			@QueryParam("period") @DefaultValue("") String period,
			@QueryParam("initialCapital") @DefaultValue("") String initialCapital,
			@QueryParam("transactionLimit") @DefaultValue("") String transactionLimit,
			@QueryParam("profitExitPosition") @DefaultValue("") String profitExitPosition,
			@QueryParam("lossExitPosition") @DefaultValue("") String lossExitPosition,
			@QueryParam("multiplier") @DefaultValue("") String multiplier);

	// @Path("/deleteStrategy")
	// @GET
	// status might not be put,
	// strategyId, quantity & period: int,
	// capital, intialCapital, transactionLimit, profitExitPosition,
	// lossExitPosition, multiplier: double,
	public String deleteStrategy(@QueryParam("strategyId") String strategyId,
			@QueryParam("username") @DefaultValue("") String username);

	// @Path("/getStrategy")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public Strategy getStrategy(@QueryParam("strategyId") String strategyId,
			@QueryParam("username") @DefaultValue("") String username);

	// @Path("/getStrategyListByUsername")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Strategy> getStrategyListByUsername(
			@QueryParam("username") @DefaultValue("") String username);

	// Instrument//
	// @Path("/getAllInstrument")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Instrument> getAllInstrument();

	// @Path("/getInstrumentByUsername")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Instrument> getInstrumentByUsername(
			@QueryParam("username") @DefaultValue("") String username);

	// @Path("/createInstrument")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public Instrument createInstrument(
			@QueryParam("symbol") @DefaultValue("") String symbol,
			@QueryParam("instruName") @DefaultValue("") String instruName,
			@QueryParam("instruType") @DefaultValue("Equity") String instruType,
			@QueryParam("company") @DefaultValue("") String company,
			@QueryParam("description") @DefaultValue("") String description);

	// @Path("/deleteInstrument")
	// @GET
	public String deleteInstrument(
			@QueryParam("symbol") @DefaultValue("") String symbol);

	// @Path("/getTransactionListByStrategy")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getTransactionListByStrategy(
			@QueryParam("strategyId") @DefaultValue("") String strategyId);



	// @Path("/getTradePosition")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Object> getTradePositionList(
			@QueryParam("username") @DefaultValue("") String username);

	// @Path("/getTransactionListByUserName")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Object> getProfitTrendByStrategy(
			@QueryParam("strategyId") @DefaultValue("") String username);

}
