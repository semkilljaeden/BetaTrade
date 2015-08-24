package entity;

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
	protected String createdDate;
	/**
	 * 
	 */
	//@Column
	protected String closedDate;
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
	 * max capital for this strategy to use
	 */
	//@Column
	protected Double capital;
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
	
	
	
	protected long shortMAperiod; // TMA parameter second basis
	protected long longMAperiod; // TMA parameter second basis

	
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
	public enum PurchaseFlag {LONG, SHORT, STAY}; // the flag set to long short or stay
	protected PurchaseFlag flag;
	
	public void makeTransaction(String symbol, double capital, double price) {
		int quantity = (int) (capital / price);
		this.quantity += quantity;
		this.capital += quantity * price;
	}
	
	/**
	 * make an order for the stock
	 * @param symbol
	 * @param capital
	 * @param price
	 */
	/**
	 * if exit position reached, return the capital to the strategy owner
	 */
	public void ifExit() {
		if((capital - initialCapital) / initialCapital < getLossExitPosition()) {
			user.setCashBalance(capital + user.getCashBalance());
			this.capital = 0.0;
			//exit thread
		}
		if((capital - initialCapital) / initialCapital > getProfitExitPosition()) {
			user.setCashBalance(capital + user.getCashBalance());;
			this.capital = 0.0;
		}
	}

	
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
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
	public String getSymbol() {
		return instrument.getSymbol();
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getCapital() {
		return capital;
	}
	public void setCapital(Double capital) {
		this.capital = capital;
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
	
	private List<InstrumentHistory> histories;
	
	public void setCurrentPrice(double ask, double bid) {
		this.currentAsk = ask;
		this.currentBid = bid;
	}

	public List<InstrumentHistory> getHistory() {
		return instrument.getHistories();
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

	public List<InstrumentHistory> getHistories() {
		return histories;
	}

	public void setHistories(List<InstrumentHistory> histories) {
		this.histories = histories;
	}
	
}
