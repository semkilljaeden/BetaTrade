package betatrade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.UserAccount;





@Stateless
public class DBManager implements DBManagerLocal {

	@PersistenceContext(unitName = "BetaTradeJPA")
	private EntityManager em;

	@Override
	public UserAccount createUserAccount(String username, String password,
			String email, String phone) {
		UserAccount userAccount=null;
		
		userAccount = this.getUserAccount(username);
		if(userAccount!=null){
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
			if(userAccount!=null){
				System.out.println("This username object already created");
				userAccount=null;
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

}
