package market;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;

import entity.InstrumentHistory;

public class MarketBoard {
	/**
	 * @author kiljaeden
	 */
	public static final String[] STOCKLIST = {"YHOO", "AAPL", "GOOG", "MSFT"};
	private static final String BASEURL = "http://query.yahooapis.com/v1/public/yql?q=";
	private static final String ENVURL = "&env=store://datatables.org/alltableswithkeys";
	private static final String COMMA = "%2C";
	private static String URL;
	private static final String QUERY = "select%20%2a%20from%20yahoo.finance.quotes%20where%20symbol%20in%20%28";
	private static final String HIST_QUERY = "select%20%2a%20from%20yahoo.finance.historicaldata%20where%20symbol%20in%20%28";
	private static final String END = "%29";
	/**
	 * The frequency of pulling data from the Yahoo! service in millsecond
	 */
	public static final long PULL_FREQUENCY = 1000 * 60 * 5;
	/**
	 * The time to hold the current price info
	 */
	public static final long HOLDING_PERIOD = 1000 * 60 * 30;
	/**
	 * recent quotes list
	 */
	private HashMap<String, RecentQuoteList> quoteList;
	/**
	 * build current query URL
	 */
	{
		String query = MarketBoard.QUERY;
		for(String symbol : MarketBoard.STOCKLIST) {
			query += "%22" + symbol + "%22" + COMMA;
		}
		URL = MarketBoard.BASEURL + query.substring(0, query.length() - 1) + "9" + MarketBoard.ENVURL;
	}
	/**
	 * 
	 * @param days how many days to look back daily based price
	 * @param symbols how many stocks to get
	 * @return list of instrument history of an instrument
	 */
	public List<InstrumentHistory> getHistoryMarketData(long days, String[] symbols) {
		String query = MarketBoard.HIST_QUERY;
		if(symbols == null) symbols = MarketBoard.STOCKLIST;
		for(String symbol : symbols) {
			query += "%22" + symbol + "%22" + COMMA;
		}
		String hist_URL = MarketBoard.BASEURL + query.substring(0, query.length() - 1) + "9";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String currentDate = dateFormat.format(c.getTime());
		c.add(Calendar.DATE, (int) -days);
		String prevDate = dateFormat.format(c.getTime());
		hist_URL += "%20and%20startDate%20=%20%27" + prevDate 
				+ "%27%20and%20endDate%20=%20%27" + currentDate + "%27";
		hist_URL += MarketBoard.ENVURL;
		StringBuilder xml = null;
		HistoryQueryResult stock = null;
		try {
	        JAXBContext jc = JAXBContext.newInstance(HistoryQueryResult.class);

	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        URL url = new URL(hist_URL);
	        System.out.println(url);
	        InputStream xmlStream = url.openStream();
	        stock =  (HistoryQueryResult) unmarshaller.unmarshal(xmlStream);
	        Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(stock, System.out);
		} catch (IOException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stock.getQuotes();
	}
	/**
	 * 
	 * @return list of current quote of multiple stock
	 */
	
	public List<CurrentQuote> getNewMarketData() {
		URL fullUrl;
		StringBuilder xml = null;
		RealTimeQueryResult stock = null;
		try {
			fullUrl = new URL(URL);
			InputStream is = fullUrl.openStream();
	        JAXBContext jc = JAXBContext.newInstance(RealTimeQueryResult.class);

	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        URL url = new URL(URL);
	        InputStream xmlStream = url.openStream();
	        stock = (RealTimeQueryResult) unmarshaller.unmarshal(xmlStream);
	        Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(stock, System.out);
		} catch (IOException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<CurrentQuote> list = stock.getQuotes();
		for(CurrentQuote q : list) {
			quoteList.get(q.getSymbol()).addQuote(q);
		}
		return list;
	}
	
	public List<CurrentQuote> getCurrentQuote(String symbol, long period) {
		int size = (int) (period / MarketBoard.PULL_FREQUENCY);
		return quoteList.get(symbol).getQuotes(size);
	}
	
	public List<CurrentQuote> getCurrentQuote(String symbol, int size) {
		return quoteList.get(symbol).getQuotes(size);
	}
	public static void main(String[] args) {
		new MarketBoard().getNewMarketData();
	}
}

