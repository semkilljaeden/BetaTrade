package betatrade;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Instrument;
import entity.Strategy;
import entity.Transaction;
import entity.UserAccount;

@Stateless
public class DBManager implements DBManagerLocal {

	@PersistenceContext(unitName = "BetaTradeJPA")
	private EntityManager em;

	@Override
	public UserAccount createUserAccount(String username, String password,
			String email, String phone) {
		UserAccount userAccount = null;

		userAccount = this.getUserAccount(username);
		if (userAccount != null) {
			System.out.println("This username object already created");
			return null;
		}

		userAccount = new UserAccount();

		userAccount.setUsername(username);
		userAccount.setPassword(password);
		userAccount.setEmail(email);
		userAccount.setPhone(phone);
		em.persist(userAccount);
		return userAccount;
	}

	@Override
	public UserAccount createUserAccount(String username, String password,
			String email, String phone, String cashBalance) {

		UserAccount userAccount = null;
		try {
			Double cashBalanceDouble = Double.parseDouble(cashBalance);
			userAccount = this.getUserAccount(username);
			if (userAccount != null) {
				System.out.println("This username object already created");
				userAccount = null;
				return null;
			}

			userAccount = new UserAccount();

			userAccount.setUsername(username);
			userAccount.setPassword(password);
			userAccount.setEmail(email);
			userAccount.setPhone(phone);
			userAccount.setCashBalance(cashBalanceDouble);
			em.persist(userAccount);
			return userAccount;
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			return userAccount;
		}
	}

	@Override
	public UserAccount updateUserAccount(String username, String password,
			String email, String phone, String cashBalance) {

		UserAccount userAccount = null;
		try {
			Double cashBalanceDouble = Double.parseDouble(cashBalance);

			userAccount = this.getUserAccount(username);
			if (userAccount == null) {
				return userAccount;
			}
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			userAccount.setEmail(email);
			userAccount.setPhone(phone);
			userAccount.setCashBalance(cashBalanceDouble);
			em.persist(userAccount);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			return userAccount;
		}
	}

	public UserAccount getUserAccount(String username) {

		UserAccount userAccount = null;
		String str = "SELECT t FROM UserAccount AS t where t.username = :username";
		Query query = em.createQuery(str).setParameter("username", username);

		// Execute the query, and get a collection of beans back.
		List<UserAccount> l = query.getResultList();

		for (UserAccount u : l) {
			if (u == null) {
				System.out.print("There is no username in DB");
			} else {
				userAccount = u;
				break;
			}
		}
		return userAccount;
	}

	public String deleteUserAccount(String username) {
		String result = null;
		UserAccount userAccount = this.getUserAccount(username);
		if (userAccount != null) {
			em.remove(userAccount);
			result = "succeed";
		}
		return result;
	}

	// Strategy
	public Strategy createStrategy(String username, String strategyName,
			String status, String symbol, String quantity,
			String period, String initialCapital, String transactionLimit,
			String profitExitPosition, String lossExitPosition,
			String multiplier) {
		Strategy s = null;
		try {
			s = new Strategy();
			UserAccount user = this.getUserAccount(username);
			if(user.getCashBalance().doubleValue()>= Double.parseDouble(initialCapital)){
				double newCashBalance = user.getCashBalance() - Double.parseDouble(initialCapital);
				user.setCashBalance(newCashBalance);
				em.merge(user);
			}else{
				throw new Exception("Not enough cashBalance in UserAccount");
			}
			s.setUser(user);
			s.setStrategyName(strategyName);
			s.setStatus(status);
			Instrument instrument = em.find(Instrument.class, symbol);
			s.setInstrument(instrument);
			s.setQuantity(Long.parseLong(quantity));
			s.setCapital(Double.parseDouble(initialCapital));
			s.setPeriod(Long.parseLong(period));
			s.setInitialCapital(Double.parseDouble(initialCapital));
			s.setTransactionLimit(Double.parseDouble(transactionLimit));
			s.setProfitExitPosition(Double.parseDouble(profitExitPosition));
			s.setLossExitPosition(Double.parseDouble(lossExitPosition));
			s.setMultiplier(Double.parseDouble(multiplier));
			em.persist(s);

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			s = null;
			return s;
		}

	}

	public String deleteStrategy(String strategyId, String username) {
		String result = null;
		try {
			Strategy s = null;
			s = em.find(Strategy.class, strategyId);
			if (username.equals(s.getUser().getUsername())) {
				Date closedDate = new Date(Calendar.getInstance().getTime()
						.getTime());
				s.setClosedDate(closedDate);
				s.setStatus("death");
				result = "succeed";
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			return result;
		}
	}

	
	public List<Strategy> getAllLiveStrategies(){
		List<Strategy> l = null;
		String str = "SELECT s FROM Strategy AS s where s.closedDate is not null";
		Query query = em.createQuery(str);

		// Execute the query, and get a collection of beans back.
		l = query.getResultList();

		
		return l;
	}

	public Transaction createTransaction(String symbol, int strategyId, String tradeType,
			double tradePrice, int tradeSize, String mode){
		Transaction t = null;
		try {
			t = new Transaction();
			Strategy s = em.find(Strategy.class, strategyId);
			t.setStrategy(s);
			Instrument i = em.find(Instrument.class, symbol);
			t.setInstrument(i);
			t.setTradePrice(tradePrice);
			t.setTradeSide(new Long(tradeSize));
			t.setTradeType(tradeType);
			t.setMode(mode);
			em.persist(t);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			t = null;
		} finally {
			return t;
		}
	}
	
	public List<Strategy> getLiveStrategyListByUsername(String username){
		List<Strategy> l = null;
		
		try {
			UserAccount u = this.getUserAccount(username);
			String str = "SELECT s FROM Strategy AS s where s.closedDate is not null and s.userId = :userId";
			Query query = em.createQuery(str).setParameter("userId", u.getUserId());

			// Execute the query, and get a collection of beans back.
			l = query.getResultList();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			l = null;
		} finally {
			return l;
		}
	}
	
	public List<Strategy> getDeathStrategyListByUsername(String username){
		List<Strategy> l = null;
		
		try {
			UserAccount u = this.getUserAccount(username);
			String str = "SELECT s FROM Strategy AS s where s.closedDate is not null and s.userId = :userId";
			Query query = em.createQuery(str).setParameter("userId", u.getUserId());

			// Execute the query, and get a collection of beans back.
			l = query.getResultList();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			l = null;
		} finally {
			return l;
		}
	}
	public List<Strategy> getAllStrategyListByUsername(String username){
		List<Strategy> l = null;
		
		try {
			UserAccount u = this.getUserAccount(username);
			String str = "SELECT s FROM Strategy AS s where s.userId = :userId";
			Query query = em.createQuery(str).setParameter("userId", u.getUserId());

			// Execute the query, and get a collection of beans back.
			l = query.getResultList();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			l = null;
		} finally {
			return l;
		}
	}
	
	
	/*
	 * public Strategy creaateStrategy(String username ,String strategyName
	 * ,String status ,String symbol ,String quantity , String capital ,String
	 * period ,String initialCapital ,String transactionLimit , String
	 * profitExitPosition ,String lossExitPosition ,String multiplier){ Strategy
	 * s = null; try{
	 * 
	 * }catch(Exception e){ System.out.println(e.toString()); }finally{ return
	 * s; }
	 * 
	 * }
	 */
}
