package entity;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
@XmlAccessorType(XmlAccessType.FIELD)
public class InstrumentHistory {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	@ManyToOne
//	private Stock team;
	
    @XmlElement(name="Date")
    @XmlSchemaType(name="date")
    private Date date;

    @XmlAttribute(name="Symbol")
    private String symbol;
    
    @XmlElement(name="Open")
    private double open;

    @XmlElement(name="High")
    private double high;

    @XmlElement(name="Low")
    private double low;

    @XmlElement(name="Close")
    private double close;

    @XmlElement(name="Volume")
    private long volume;

    @XmlElement(name="Adj_Close")
    private double adjClose;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}
}
