package model;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;



public class ExchangeRateSearch {
	

	private Map<String, Map<String, Double>> rates;
	private String start_at;
	private String base;
	private String end_at;

	public Map<String, Map<String, Double>> getrates() {
	    return this.rates;
	}

	public void setBuys(Map<String, Map<String, Double>> rates) {
		this.rates = rates;
	}

	
	/**
	 * @param rates
	 * @param start_at
	 * @param base
	 * @param end_at
	 */
	public ExchangeRateSearch(Map<String, Map<String, Double>> rates, String start_at, String base, String end_at) {
		this.rates = rates;
		this.start_at = start_at;
		this.base = base;
		this.end_at = end_at;
	}
	
	public String[] getMaxRate() {
		double rate = -1;
		String [] max = new String [2];
		Iterator<Entry<String, Map<String, Double>>> it = this.rates.entrySet().iterator();
		while (it.hasNext()) {
			 Entry<String, Map<String, Double>> pairs = it.next(); // <- pairs.getValue() is a map
			 Map<String, Double> tempMap = (Map<String, Double>) pairs.getValue();
			 Entry<String, Double> entry = tempMap.entrySet().iterator().next();
			 Double value = entry.getValue();
			 //System.out.println(value);
			 if(value > rate) {
				max[0] = Double. toString(value); 
				max[1] = (String) pairs.getKey();
				rate = value;
			 }
			 }
		return max;	
	}
	
	
	
	public String[] getMinxRate() {
		double rate = 100000.;
		String [] min = new String [2];
		Iterator<Entry<String, Map<String, Double>>> it = this.rates.entrySet().iterator();
		while (it.hasNext()) {
			 Entry<String, Map<String, Double>> pairs = it.next(); // <- pairs.getValue() is a map
			 Map<String, Double> tempMap = (Map<String, Double>) pairs.getValue();
			 Entry<String, Double> entry = tempMap.entrySet().iterator().next();
			 Double value = entry.getValue();
			 //System.out.println(value);
			 if(value < rate) {
				min[0] = Double. toString(value); 
				min[1] = (String) pairs.getKey();
				rate = value;
			 }
			 }
		return min;	
	}
	
	public double getAverageRate() {
		double total = 0;
		int count = 0;
		Iterator<Entry<String, Map<String, Double>>> it = this.rates.entrySet().iterator();
		while (it.hasNext()) {
			 Entry<String, Map<String, Double>> pairs = it.next(); // <- pairs.getValue() is a map
			 Map<String, Double> tempMap = (Map<String, Double>) pairs.getValue();
			 Entry<String, Double> entry = tempMap.entrySet().iterator().next();
			 Double value = entry.getValue();
			 total += value;
			 count++;
			 //System.out.println("total :" + total +" count :" + count );
			 }
		return total/count;	
	}

	/**
	 * 
	 */
	public ExchangeRateSearch() {
	}

	@Override
	public String toString() {
		return "rates=" + this.rates + ", start_at=" + this.start_at + ", base=" + this.base + ", end_at=" + this.end_at ;
	}

}
