package model;

public class SearchHistory {

	
	private String timeStamp;
	private String description;
	private double mean;
	private MaxRate maxRate;
	private MinRate minRate;
	
	
	/**
	 * @param timeStamp
	 * @param description
	 * @param mean
	 * @param maxRate
	 * @param minRate
	 */
	public SearchHistory(String timeStamp, String description, double mean, MaxRate maxRate, MinRate minRate) {
		this.timeStamp = timeStamp;
		this.description = description;
		this.mean = mean;
		this.maxRate = maxRate;
		this.minRate = minRate;
	}

	
	@Override
	public String toString() {
		return " timeStamp=" + timeStamp + ",\n"
				+ " description=" + description + ",\n"
						+ " mean=" + mean+ ",\n"
								+ " maxRate=" + maxRate.toString() + ",\n"
										+ " minRate=" + minRate.toString() ;
	}



	

	


	
	
}
