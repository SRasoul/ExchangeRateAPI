package model;

public class MinRate{
	private double rate;
	private String date;
	/**
	 * @param rate
	 * @param date
	 */
	public MinRate(double rate, String date) {
		this.rate = rate;
		this.date = date;
	}
	@Override
	public String toString() {
		return " [rate=" + rate + ", date=" + date + "]";
	}
	
	
}