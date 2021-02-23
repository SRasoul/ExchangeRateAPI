package model;

import java.util.ArrayList;
import java.util.List;

public class FileRoot
{
    private List<SearchHistory> searchHistory;
    
    

	/**
	 * @param list
	 */
	public FileRoot(List<SearchHistory> list) {
		this.searchHistory = list;
	}

	/**
	 * @return the searchHistory
	 */
	public List<SearchHistory> getSearchHistory() {
		return searchHistory;
	}

	/**
	 * @param searchHistory the searchHistory to set
	 */
	public void setSearchHistory(ArrayList<SearchHistory> searchHistory) {
		this.searchHistory = searchHistory;
	}

	@Override
	public String toString() {
		return "FileRoot [searchHistory=" + searchHistory + "]";
	}
    
	

}