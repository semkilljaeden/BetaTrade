package strategy;

import java.util.List;

import javax.mail.Flags.Flag;

import entity.Strategy;
import market.Indicator;
import market.MarketBoard;

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
		setHistories(new MarketBoard().getHistoryMarketData(period, 
				new String[]{getSymbol()}));
		SMA = Indicator.getSMA(getHistory());
		double sd = Indicator.getStandardDeviation(getHistory());
		upperBand = SMA + multiplier * sd;
		lowerBand = SMA - multiplier * sd;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(capital == 0) return;
		if(currentBid > upperBand) {
			flag = PurchaseFlag.SHORT;
		}
		else if(currentAsk < lowerBand) {
			flag = PurchaseFlag.LONG;
		}
		ifExit();
	}
	
}
