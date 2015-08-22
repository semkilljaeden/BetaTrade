package market;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import entity.InstrumentHistory;

@XmlRootElement(name="query")
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryQueryResult {
    @XmlElementWrapper(name="results")
    @XmlElement(name="quote")
    private List<InstrumentHistory> quotes;

	public List<InstrumentHistory> getQuotes() {
		return quotes;
	}

	public List<InstrumentHistory> getQuotes(List<InstrumentHistory> quotes) {
		return this.quotes;
	}

}