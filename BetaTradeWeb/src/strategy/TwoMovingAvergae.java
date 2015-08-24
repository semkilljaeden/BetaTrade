package strategy;

import java.util.List;

import entity.InstrumentHistory;
import entity.Strategy;
import market.Indicator;
import market.MarketBoard;

public class TwoMovingAvergae extends Strategy {
	
	double shortMA;
	double longMA;
	private void Initialization(List<InstrumentHistory> list) {
		history = list;
		int size = (int)(shortMAperiod / MarketBoard.PULL_FREQUENCY);
		shortMA = Indicator.getSMA(history.subList(history.size() - 1 - size, history.size() - 1));
		size = (int)(longMAperiod / MarketBoard.PULL_FREQUENCY);
		longMA = Indicator.getSMA(history.subList(history.size() - 1 - size, history.size() - 1));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ifExit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeTransaction(String symbol, double capital, double price) {
		// TODO Auto-generated method stub

	}

}
