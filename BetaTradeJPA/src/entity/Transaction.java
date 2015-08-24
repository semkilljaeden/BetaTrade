package entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(schema="betatrade", name="Transaction")
public class Transaction {

	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	 
	private Instrument instrument;
	private Strategy strategy;
	private String tradeType;
	private Double tradePrice;
	private Long tradeSide;
	private String mode;
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name="symbol")
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	public Strategy getStrategy() {
		return strategy;
	}
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public Double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}
	public Long getTradeSide() {
		return tradeSide;
	}
	public void setTradeSide(Long tradeSide) {
		this.tradeSide = tradeSide;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
