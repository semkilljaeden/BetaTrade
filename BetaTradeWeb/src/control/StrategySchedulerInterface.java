package control;

import entity.Strategy;

public interface StrategySchedulerInterface {
	
	public String appendStrategyToList(Strategy s); //please add this to your list
	public String killStrategy(String strategyId); //sell stock of this strategy, return cash, update to UserAccount table, Transaction, Strategy
	public String emptyStrategyList(); //just set your list as empty
	public String appendAllStrategiesToList();// call the DBManager to get all strategy and append to list
	
}
