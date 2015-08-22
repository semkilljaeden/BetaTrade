package market;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class RecentQuoteList {
	/**
	 * @author kiljaeden
	 */
	/**
	 * stock name
	 */
	private String symbol;
	private LinkedList<CurrentQuote> quoteQueue;
	private long holdingPeriod;
	private static int queueLength;
	public RecentQuoteList() {
		quoteQueue = new LinkedList<CurrentQuote>();
		queueLength =(int) (holdingPeriod / MarketBoard.PULL_FREQUENCY);
	}
	
	public void addQuote(CurrentQuote q) {
		quoteQueue.add(q);
		if(quoteQueue.size() > queueLength) quoteQueue.poll();
	}
	
	public List<CurrentQuote> getQuotes(int size) {
		return quoteQueue.subList(quoteQueue.size()- 1 - size, quoteQueue.size() - 1);
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Queue getQuoteQueue() {
		return quoteQueue;
	}

	public void setQuoteQueue(LinkedList<CurrentQuote> quoteQueue) {
		this.quoteQueue = quoteQueue;
	}
}
