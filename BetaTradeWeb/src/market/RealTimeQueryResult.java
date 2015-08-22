package market;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="query")
@XmlAccessorType(XmlAccessType.FIELD)
public class RealTimeQueryResult {
    @XmlElementWrapper(name="results")
    @XmlElement(name="quote")
    private List<CurrentQuote> quotes;

	public List<CurrentQuote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<CurrentQuote> quotes) {
		this.quotes = quotes;
	}

}
