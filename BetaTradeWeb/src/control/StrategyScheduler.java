package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Strategy;
import market.CurrentQuote;
import market.MarketBoard;

public class StrategyScheduler implements Runnable, StrategySchedulerInterface{
	/**
	 * @author kiljaeden
	 */
	private List<Strategy> strategyList;
	HashMap<String, Strategy> strategyAccesser;
	MarketBoard crawler;
	
	public StrategyScheduler() {
		// TODO Auto-generated constructor stub
		crawler = new MarketBoard();
	}
	/**
	 * run the strategy
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public List<Strategy> getStrategyList() {
		return strategyList;
	}
	/**
	 * set strategyList and produce inverse strategyList aka accesser
	 * @param strategyList
	 */
	public void setStrategyList(List<Strategy> strategyList) {
		
	}
	@Override
	public String appendStrategyToList(Strategy s) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String emptyStrategyList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String appendAllStrategiesToList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String killStrategy(String strategyId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
