package betatrade;

import java.util.List;

import javax.ejb.Local;

import entity.Strategy;
import entity.Transaction;
import entity.UserAccount;

@Local
public interface DBManagerLocal {

	public UserAccount createUserAccount(String username, String password, String email, String phone);
	public UserAccount createUserAccount(String username, String password, String email, String phone, String cashBalance);
	public UserAccount updateUserAccount(String username, String password, String email, String phone, String cashBalance);
	public UserAccount getUserAccount(String username);
	public String deleteUserAccount(String username);
	
	public Strategy createStrategy(String username ,String strategyName ,String status ,String symbol ,String quantity ,
			String period ,String initialCapital ,String transactionLimit ,
			String profitExitPosition ,String lossExitPosition ,String multiplier);
	
	public String deleteStrategy(String strategyId, String username);
	public List<Strategy> getAllLiveStrategies();
	public Transaction createTransaction(String symbol, int strategyId, String tradeType,
			double tradePrice, int tradeSize, String mode);
	
	public List<Strategy> getLiveStrategyListByUsername(String username);
	public List<Strategy> getAllStrategyListByUsername(String username);
	public List<Strategy> getDeathStrategyListByUsername(String username);
}
