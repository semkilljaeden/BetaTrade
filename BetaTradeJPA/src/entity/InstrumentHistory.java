package entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: InstrumentHistory
 *
 */
@Entity
@Table(schema="betatrade", name="InstrumentHistory")
public class InstrumentHistory implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public InstrumentHistory() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String date;
	
	
	private Instrument instrument;
   
	private Double open;
	
	private Double high;
	
	private Double low;
	
	private Double close;
	
	private Double adjClose;
	
	private Long volume;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name="symbol")
	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(Double adjClose) {
		this.adjClose = adjClose;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}
	
	
}
