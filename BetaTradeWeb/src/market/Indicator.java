package market;

import java.util.List;

import entity.InstrumentHistory;

public class Indicator {
	/**
	 * get SMA indicator
	 * @param list
	 * @return
	 */
	
	public static double getSMA(List<InstrumentHistory> list) {
		double sma = 0.0;
		for(InstrumentHistory i : list) {
			sma += i.getClose();
		}
		return sma / list.size();
	}
	/**
	 * get Standard deviation of a given period
	 * @param list
	 * @return
	 */
	
	public static double getStandardDeviation(List<InstrumentHistory> list) {
		double sma = getSMA(list);
		double deviation = 0.0;
		for(InstrumentHistory i : list) {
			deviation += Math.pow(i.getClose() - sma , 2); 
		}
		
		return Math.sqrt(deviation / list.size());
		
	}
}
