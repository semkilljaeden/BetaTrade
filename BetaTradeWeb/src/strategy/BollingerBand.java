import java.util.List;

public class BollingerBand extends Strategy {
	/**
	 * @author kiljaeden
	 *
	 */
	
	//calculate on the run
	private double SMA;
	private double upperBand;
	private double lowerBand;
	
	public BollingerBand() {
		setHistory(new MarketBoard().getHistoryMarketData(period, 
				new String[]{symbol}));
		SMA = Indicator.getSMA(getHistory());
		double sd = Indicator.getStandardDeviation(getHistory());
		upperBand = SMA + multiplier * sd;
		lowerBand = SMA - multiplier * sd;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(capital == 0) return;
		if(currentAsk > upperBand) {
			makeTransaction(this.symbol, -(capital >= getTransactionLimit()? capital : getTransactionLimit()), currentAsk); 
		}
		else if(currentBid < lowerBand) {
			makeTransaction(this.symbol, capital >= getTransactionLimit()? capital : getTransactionLimit(), currentBid);
		}
		ifExit();
	}
	/**
	 * make an order for the stock
	 * @param symbol
	 * @param capital
	 * @param price
	 */
	@Override
	public void makeTransaction(String symbol, double capital, double price) {
		int quantity = (int) (capital / price);
		this.quantity += quantity;
		this.capital += quantity * price;
	}
	/**
	 * if exit position reached, return the capital to the strategy owner
	 */
	@Override
	public void ifExit() {
		if((capital - initialCapital) / initialCapital < getLossExitPosition()) {
			p.incrementCapt(capital);
			this.capital = 0;
			//exit thread
		}
		if((capital - initialCapital) / initialCapital > getProfitExitPosition()) {
			p.incrementCapt(capital);
			this.capital = 0;
		}
	}
	
}
