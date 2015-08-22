package entity;

import java.util.List;
//@Entity
//@Inheritance(strategy = InheritanceStrategy.SINGLE_TABLE)
public abstract class Strategy implements Runnable{
	/**
	 * @author kiljaeden
	 */
	
	/**
	 * id
	 */
	
	//@Column("id")
	/**
	 * the stock 's symbol which is managed by this strategy
	 */
	//@Column
	protected String symbol;
	/**
	 * quantity of the stocks that strategy is holding
	 */
	//@Column
	protected int quantity;
	/**
	 * current capital (money) for this strategy to use
	 */
	//@Column
	protected double cash;
	
	/**
	 * 
	 */
	
	//@Column
	protected int period;
	//@Column
	protected double initialCapital;
	
	//@Column
	protected String status;
	
	
	//@ManyToOne
	
	
	//change back later
	
	protected UserAccount p;
	/**
	 * the limit of capital that can be spent per transaction 
	 */
	//@Column
	private double transactionLimit;
	//@Column
	private double profitExitPosition;
	//@Column
	private double lossExitPosition;
	
	//on fly
	/**
	 * buying price
	 */
	protected double currentAsk; 
	/**
	 * selling price
	 */
	protected double currentBid;
	
	
	protected List<InstrumentHistory> history;
	
	/**
	 * get SMA indicator
	 * @param list
	 * @return
	 */
	
	protected static double getSMA(List<InstrumentHistory> list) {
		double sma = 0.0;
		for(InstrumentHistory i : list) {
			sma += i.getClose();
		}
		return sma / list.size();
	}
	/**
	 * get Standard deviation of a given period
	 * @param list
	 * @return
	 */
	
	protected static double getStandardDeviation(List<InstrumentHistory> list) {
		double sma = getSMA(list);
		double deviation = 0.0;
		for(InstrumentHistory i : list) {
			deviation += Math.pow(i.getClose() - sma , 2); 
		}
		
		return Math.sqrt(deviation / list.size());
		
	}
	protected void setCurrentPrice(double ask, double bid) {
		this.currentAsk = ask;
		this.currentBid = bid;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		symbol = symbol;
	}

	public List<InstrumentHistory> getHistory() {
		return history;
	}

	public void setHistory(List<InstrumentHistory> history) {
		this.history = history;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double capital) {
		this.cash = capital;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getInitialCapital() {
		return initialCapital;
	}

	public void setInitialCapital(double initialCapital) {
		this.initialCapital = initialCapital;
	}
	public double getTransactionLimit() {
		return transactionLimit;
	}
	public void setTransactionLimit(double transactionLimit) {
		this.transactionLimit = transactionLimit;
	}
	public double getProfitExitPosition() {
		return profitExitPosition;
	}
	public void setProfitExitPosition(double profitExitPosition) {
		this.profitExitPosition = profitExitPosition;
	}
	public double getLossExitPosition() {
		return lossExitPosition;
	}
	public void setLossExitPosition(double lossExitPosition) {
		this.lossExitPosition = lossExitPosition;
	}
}
