package entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;
//@Entity
//@Inheritance(strategy = InheritanceStrategy.SINGLE_TABLE)
@Entity
@Table(schema="betatrade", name="Strategy")
public class Strategy implements Runnable{
	/**
	 * @author kiljaeden
	 */
	
	/**
	 * 
	 */
	//@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	/**
	 * 
	 */
	//@Column userId
	protected UserAccount user;
	/**
	 * 
	 */
	//@Column
	protected String strategyName;
	/**
	 * 
	 */
	//@Column
	protected String description;
	/**
	 * 
	 */
	//@Column
	protected Date createdDate;
	/**
	 * 
	 */
	//@Column
	protected Date closedDate;
	/**
	 * 
	 */
	//@Column
	protected String status;
	/**
	 * the stock 's symbol which is managed by this strategy
	 */
	//@Column instruId
	protected Instrument instrument;
	/**
	 * quantity of the stocks that strategy is holding
	 */
	//@Column
	protected Long quantity;
	/**
	 * current cash left
	 */
	@Column(name="capital")
	protected Double cash;
	/**
	 * unit is day
	 */
	//@Column
	protected Long period;
	//@Column
	protected Double initialCapital;
	
	/**
	 * the limit of capital that can be spent per transaction 
	 */
	//@Column
	protected Double transactionLimit;
	//@Column
	protected Double profitExitPosition;
	//@Column
	protected Double lossExitPosition;
	//@Column
	protected Double multiplier;
	
	
	//on fly
	/**
	 * buying price
	 */
	protected double currentAsk; 
	/**
	 * selling price
	 */
	protected double currentBid;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="userId")
	public UserAccount getUser() {
		return user;
	}
	public void setUser(UserAccount user) {
		this.user = user;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name="symbol")
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getCapital() {
		return cash;
	}
	public void setCapital(Double capital) {
		this.cash = capital;
	}
	public Long getPeriod() {
		return period;
	}
	public void setPeriod(Long period) {
		this.period = period;
	}
	public Double getInitialCapital() {
		return initialCapital;
	}
	public void setInitialCapital(Double initialCapital) {
		this.initialCapital = initialCapital;
	}
	public Double getTransactionLimit() {
		return transactionLimit;
	}
	public void setTransactionLimit(Double transactionLimit) {
		this.transactionLimit = transactionLimit;
	}
	public Double getProfitExitPosition() {
		return profitExitPosition;
	}
	public void setProfitExitPosition(Double profitExitPosition) {
		this.profitExitPosition = profitExitPosition;
	}
	public Double getLossExitPosition() {
		return lossExitPosition;
	}
	public void setLossExitPosition(Double lossExitPosition) {
		this.lossExitPosition = lossExitPosition;
	}
	public Double getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(Double multiplier) {
		this.multiplier = multiplier;
	}
	public double getCurrentAsk() {
		return currentAsk;
	}
	public void setCurrentAsk(double currentAsk) {
		this.currentAsk = currentAsk;
	}
	public double getCurrentBid() {
		return currentBid;
	}
	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}
	
	
	/*
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
	*/
	
	
	//public abstract void ifExit();
	
	//public abstract void makeTransaction(String symbol, double capital, double price);
	
	public void setCurrentPrice(double ask, double bid) {
		this.currentAsk = ask;
		this.currentBid = bid;
	}


	protected List<Transaction> transactions;
	
	@OneToMany(mappedBy="strategy")
	@OrderBy("id DESC")
	public List<Transaction> getTransactions(){
		return transactions;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
