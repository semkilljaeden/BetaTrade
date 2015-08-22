package entity;

import java.util.List;
//@Entity
//@Inheritance(strategy = InheritanceStrategy.SINGLE_TABLE)
public abstract class Strategy implements Runnable{
	/**
	 * @author kiljaeden
	 */
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
	 * max capital for this strategy to use
	 */
	//@Column
	protected double capital;
	/**
	 * unit is day
	 */
	//@Column
	protected int period;
	//@Column
	protected double initialCapital;
	//@ManyToOne
	protected DummyProfile p;
	/**
	 * the limit of capital that can be spent per transaction 
	 */
	//@Column
	protected double transactionLimit;
	//@Column
	protected double profitExitPosition;
	//@Column
	protected double lossExitPosition;
	//@Column
	protected double multiplier;
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
	
	public abstract void ifExit();
	public abstract void makeTransaction(String symbol, double capital, double price);
	public void setCurrentPrice(double ask, double bid) {
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

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
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
