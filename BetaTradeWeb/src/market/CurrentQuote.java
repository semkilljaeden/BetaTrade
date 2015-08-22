import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CurrentQuote {
	
    @XmlElement(name="Symbol")
    private String symbol;
    @XmlElement(name="Ask")
    private double ask;
    @XmlElement(name="Bid")
    private double bid;
    @XmlElement(name="Currency")
    private String currency;
    @XmlElement(name="LastTradePriceOnly")
    private double lastTradePriceOnly;
    @XmlElement(name="Open")
    private double open;
    @XmlElement(name="PreviousClose")
    private double previousClose;


    //<DaysRange>111.63 - 114.35</DaysRange>
    
    
    
    /**statistic Data
     * 
     */
    
    @XmlElement(name="DaysLow")
    private String daysLow;
    @XmlElement(name="DaysHigh")
    private String YearHigh;
    @XmlElement(name="YearLow")
    private String yearsLow;
    @XmlElement(name="YearHigh")
    private String yearHigh;
    @XmlElement(name="Change_PercentChange")
    private String change_PercentChange;
    @XmlElement(name="Change")
    private String change;
    @XmlElement(name="MarketCapitalization")
    private String marketCapitalization;
    @XmlElement(name="EBITDA")
    private double EBITDA;
    @XmlElement(name="ChangeFromYearLow")
    private double cFromYearLow;
    @XmlElement(name="PercentChangeFromYearLow")
    private String pcFromYearLow;
    @XmlElement(name="ChangeFromYearHigh")
    private double cFromYearHigh;
    @XmlElement(name="PercentChangeFromYearHigh")
    private String pcFromYearHigh;
    @XmlElement(name="FiftydayMovingAverage")
    private double fiftydayMovingAvg;
    @XmlElement(name="TwoHundreddayMovingAverage")
    private double twoHundreddayMovingAvg;
    @XmlElement(name="ChangeFromFiftydayMovingAverage")
    private double cFromFiftydayMovingAvg;
    @XmlElement(name="ChangeFromTwoHundreddayMovingAverage")
    private double cFromTwoHundreddayMovingAvg;
    @XmlElement(name="PercentChangeFromFiftydayMovingAverage")
    private double pcFromFiftydayMovingAvg;
    @XmlElement(name="PercentChangeFromTwoHundreddayMovingAverage")
    private double pcFromTwoHundreddayMovingAvg;
    @XmlElement(name="PERatio")
    private double PERatio;
    @XmlElement(name="PEGRatio")
    private double PEGRatio;
    @XmlElement(name="PriceEPSEstimateCurrentYear")
    private double priceEPSEstimateCurrentYear;
    @XmlElement(name="PriceEPSEstimateNextYear")
    private double priceEPSEstimateNextYear;
    @XmlElement(name="Volume")
    private double volume;
    @XmlElement(name="ShortRatio")
    private double shortRatio;
    

/*


    <ChangeinPercent>-2.05%</ChangeinPercent>

    <PriceSales>2.92</PriceSales>

    <PriceBook>5.22</PriceBook>

    <ExDividendDate>8/6/2015</ExDividendDate>



    <LastTradeTime>4:00pm</LastTradeTime>

    <TickerTrend/>

    <OneyrTargetPrice>146.88</OneyrTargetPrice>

*/
	public double getAsk() {
		return ask;
	}
	public void setAsk(double ask) {
		this.ask = ask;
	}
	public double getBid() {
		return bid;
	}
	public void setBid(double bid) {
		this.bid = bid;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDaysLow() {
		return daysLow;
	}
	public void setDaysLow(String daysLow) {
		this.daysLow = daysLow;
	}
	public String getYearsLow() {
		return yearsLow;
	}
	public void setYearsLow(String yearsLow) {
		this.yearsLow = yearsLow;
	}
	public String getYearHigh() {
		return yearHigh;
	}
	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}
	public String getChange_PercentChange() {
		return change_PercentChange;
	}
	public void setChange_PercentChange(String change_PercentChange) {
		this.change_PercentChange = change_PercentChange;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public double getLastTradePriceOnly() {
		return lastTradePriceOnly;
	}
	public void setLastTradePriceOnly(double lastTradePriceOnly) {
		this.lastTradePriceOnly = lastTradePriceOnly;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getPreviousClose() {
		return previousClose;
	}
	public void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}
	public String getMarketCapitalization() {
		return marketCapitalization;
	}
	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}
	public double getEBITDA() {
		return EBITDA;
	}
	public void setEBITDA(double eBITDA) {
		EBITDA = eBITDA;
	}
	public double getcFromYearLow() {
		return cFromYearLow;
	}
	public void setcFromYearLow(double cFromYearLow) {
		this.cFromYearLow = cFromYearLow;
	}
	public String getPcFromYearLow() {
		return pcFromYearLow;
	}
	public void setPcFromYearLow(String pcFromYearLow) {
		this.pcFromYearLow = pcFromYearLow;
	}
	public double getcFromYearHigh() {
		return cFromYearHigh;
	}
	public void setcFromYearHigh(double cFromYearHigh) {
		this.cFromYearHigh = cFromYearHigh;
	}
	public String getPcFromYearHigh() {
		return pcFromYearHigh;
	}
	public void setPcFromYearHigh(String pcFromYearHigh) {
		this.pcFromYearHigh = pcFromYearHigh;
	}
	public double getFiftydayMovingAvg() {
		return fiftydayMovingAvg;
	}
	public void setFiftydayMovingAvg(double fiftydayMovingAvg) {
		this.fiftydayMovingAvg = fiftydayMovingAvg;
	}
	public double getTwoHundreddayMovingAvg() {
		return twoHundreddayMovingAvg;
	}
	public void setTwoHundreddayMovingAvg(double twoHundreddayMovingAvg) {
		this.twoHundreddayMovingAvg = twoHundreddayMovingAvg;
	}
	public double getcFromFiftydayMovingAvg() {
		return cFromFiftydayMovingAvg;
	}
	public void setcFromFiftydayMovingAvg(double cFromFiftydayMovingAvg) {
		this.cFromFiftydayMovingAvg = cFromFiftydayMovingAvg;
	}
	public double getcFromTwoHundreddayMovingAvg() {
		return cFromTwoHundreddayMovingAvg;
	}
	public void setcFromTwoHundreddayMovingAvg(double cFromTwoHundreddayMovingAvg) {
		this.cFromTwoHundreddayMovingAvg = cFromTwoHundreddayMovingAvg;
	}
	public double getPcFromFiftydayMovingAvg() {
		return pcFromFiftydayMovingAvg;
	}
	public void setPcFromFiftydayMovingAvg(double pcFromFiftydayMovingAvg) {
		this.pcFromFiftydayMovingAvg = pcFromFiftydayMovingAvg;
	}
	public double getPcFromTwoHundreddayMovingAvg() {
		return pcFromTwoHundreddayMovingAvg;
	}
	public void setPcFromTwoHundreddayMovingAvg(double pcFromTwoHundreddayMovingAvg) {
		this.pcFromTwoHundreddayMovingAvg = pcFromTwoHundreddayMovingAvg;
	}
	public double getPERatio() {
		return PERatio;
	}
	public void setPERatio(double pERatio) {
		PERatio = pERatio;
	}
	public double getPEGRatio() {
		return PEGRatio;
	}
	public void setPEGRatio(double pEGRatio) {
		PEGRatio = pEGRatio;
	}
	public double getPriceEPSEstimateCurrentYear() {
		return priceEPSEstimateCurrentYear;
	}
	public void setPriceEPSEstimateCurrentYear(double priceEPSEstimateCurrentYear) {
		this.priceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
	}
	public double getPriceEPSEstimateNextYear() {
		return priceEPSEstimateNextYear;
	}
	public void setPriceEPSEstimateNextYear(double priceEPSEstimateNextYear) {
		this.priceEPSEstimateNextYear = priceEPSEstimateNextYear;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getShortRatio() {
		return shortRatio;
	}
	public void setShortRatio(double shortRatio) {
		this.shortRatio = shortRatio;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}