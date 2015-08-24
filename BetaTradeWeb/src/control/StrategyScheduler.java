package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Strategy;
import market.CurrentQuote;
import market.MarketBoard;

public class StrategyScheduler implements Runnable{
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
		List<CurrentQuote> quoteList;
		for(Strategy s : getStrategyList()) {
			s.setHistories(crawler.getHistoryMarketData(s.getPeriod(), 
					new String[]{s.getSymbol()}));
		}
		while(true) {
			quoteList = crawler.getNewMarketData();
			for(CurrentQuote q : quoteList) {
				Strategy s = strategyAccesser.get(q.getSymbol());
				s.setCurrentPrice(q.getAsk(), q.getBid());
			}
			for(Strategy s : getStrategyList()) {
				Thread t = new Thread(s);
				t.start();
			}
			try {
				Thread.sleep(MarketBoard.PULL_FREQUENCY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public List<Strategy> getStrategyList() {
		return strategyList;
	}
	/**
	 * set strategyList and produce inverse strategyList aka accesser
	 * @param strategyList
	 */
	public void setStrategyList(List<Strategy> strategyList) {
		this.strategyList = strategyList;
		strategyAccesser = new HashMap<>();
		for(Strategy s : strategyList) {
			strategyAccesser.put(s.getSymbol(), s);
		}
	}
	
	
	
}
