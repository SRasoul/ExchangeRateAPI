package model;

public class MaxRate{
	private double rate;
	private String date;
	/**
	 * @param rate
	 * @param date
	 */
	public MaxRate(double rate, String date) {
		this.rate = rate;
		this.date = date;
	}
	@Override
	public String toString() {
		return " [rate=" + rate + ", date=" + date + "]";
	}
	
	
}