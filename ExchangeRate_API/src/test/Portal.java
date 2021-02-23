package test;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.ExchangeRateSearch;
import model.FileRoot;
import model.MaxRate;
import model.MinRate;
import model.SearchHistory;


public class Portal {
	
	private static String startDate;
	private static String endDate;
	private static String baseCurrency;
	private static String comparedCurrency;
	
	private static String ULR; 
	private static Scanner myObj;
	
	
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		
		return startDate;
	}

	/**
	 * @return the uLR
	 */
	public static String getULR() {
		return ULR;
	}

	/**
	 * @param uLR the uLR to set
	 */
	public static void setULR(String uLR) {
		ULR = uLR;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public static void setStartDate(String stDate) {
	     startDate = stDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public static void setEndDate(String endD) {
		endDate = endD;
	}

	/**
	 * @return the baseCurrency
	 */
	public String getBaseCurrency() {
		return baseCurrency;
	}

	/**
	 * @param baseCurrency the baseCurrency to set
	 */
	public static void setBaseCurrency(String baseCur) {
		baseCurrency = baseCur;
	}

	/**
	 * @return the comparedCurrency
	 */
	public String getComparedCurrency() {
		return comparedCurrency;
	}

	
	/**
	 * @param comparedCurrency the comparedCurrency to set
	 */
	public static void setComparedCurrency(String compared) {
		comparedCurrency = compared;
	}
		
	
	    private static String deserialize(String response){    	  
         Gson gson = new GsonBuilder().setPrettyPrinting().create();		    	 	    	
	    try {
	   	        
	    	    
		        ExchangeRateSearch obj = gson.fromJson(response, ExchangeRateSearch.class);
		        String description = baseCurrency + ":" + comparedCurrency 
		        		          + "from " + startDate + " to " + endDate;
		        
		        String[] test = obj.getMaxRate();
		        String[] test2 = obj.getMinxRate();
		        //System.out.println("OBJECT : " + obj);
		        System.out.println(description);
		        System.out.println("Mean  : " + obj.getAverageRate());
		        System.out.println("Max Rate : " + test[0] + "  Date : " + test[1]);
		        System.out.println("Min Rate : " + test2[0] + " Date : " + test2[1]);
		        
		        String tStamp =  getTimeStamp();
		        SearchHistory sH = createSearchHistoryObject(tStamp, description, obj);
		        
	        
		        FileReader fileReader = new FileReader("data/pastSearchs.json");		        
	            BufferedReader buffered = new BufferedReader(fileReader);	 
	            FileRoot root =  gson.fromJson(buffered, FileRoot.class);
	            if(root != null && root.getSearchHistory() != null) {
	            	root.getSearchHistory().add(sH);
	            }else {
	            	List<SearchHistory> list = new ArrayList<>();
	            	list.add(sH);
	            	root = new FileRoot(list);
	            }
		        
	            
	            FileWriter Filewriter = new FileWriter("data/pastSearchs.json");
	            String jsonRepresentation = gson.toJson(root);
	            Filewriter.write(jsonRepresentation);
	            Filewriter.close();
	            
	            
	    	 } catch (Exception e) {
	             e.printStackTrace();
	         }
	    return null;

		}
		
	    public static String constructUlr() {
			
			myObj = new Scanner(System.in);
			System.out.println("Enter the start-date of your serch period in this form \"2017-01-03\" ");
			setStartDate(myObj.nextLine());
			System.out.println("Enter the end-date of your serch period in this form \"2017-12-29\" ");
			setEndDate(myObj.nextLine());
			System.out.println("Choose a base currency from the list ");
			System.out.println(" CAD HKD ISK PHP DKK HUF CZK AUD RON SEK IDR INR BRL RUB HRK JPY\n "
					          + "THB CHF SGD PLN BGN TRY CNY NOK NZD ZAR USD MXN ILS GBP KRW MYR ");
			setBaseCurrency(myObj.nextLine());
			System.out.println("Enter variable currency");
			setComparedCurrency(myObj.nextLine());
			String temp = "https://api.exchangeratesapi.io/history?start_at="
							+ startDate
							+ "&end_at="
							+ endDate
							+ "&base="
							+ baseCurrency
							+ "&symbols="
							+ comparedCurrency;
			return temp;			
		}	    
	    
	    
		public static void cennect()  {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ULR)).build();
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenApply(Portal::deserialize)
			//.thenAccept(System.out::println)
			.join();
		}
		
		
		public static SearchHistory createSearchHistoryObject(String timeStamp, String description, ExchangeRateSearch obj) {
	        String[] min = obj.getMinxRate();
	        MinRate minRate = new MinRate(Double.parseDouble(min[0]), min[1]);        
	        String[] max = obj.getMaxRate();
	        MaxRate maxRate = new MaxRate(Double.parseDouble(max[0]), max[1]);			        
			double mean = obj.getAverageRate();			
			
			return new  SearchHistory(timeStamp, description, mean, maxRate, minRate);	
		}
		
		
	   public static String getTimeStamp() {
		   String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		   return timeStamp;
	   }
		
	   
	   public static void viewSearchHistory() {
		   System.out.println("******************************************************************** ");
		   System.out.println("**************************Search History**************************** ");
		   Gson gson = new GsonBuilder().setPrettyPrinting().create();		    	 	    	
		    try {
		    	FileReader fileReader = new FileReader("data/pastSearchs.json");		        
	            BufferedReader buffered = new BufferedReader(fileReader);
		    	FileRoot obj = gson.fromJson(buffered, FileRoot.class);
		    	for(SearchHistory sh: obj.getSearchHistory()) {
		    		System.out.println(sh.toString());
		    		System.out.println("******************************************************************** ");
		    	}
		 	 } catch (Exception e) {
	             e.printStackTrace();
		 	 }
		    shaowMenue();
		}    
	
	   
	   public static void hardCodeInput() {
		    setStartDate("2014-12-29");
	    	setEndDate("2016-12-29");
	    	setBaseCurrency("GBP");
	    	setComparedCurrency("USD");
	    	String temp = "https://api.exchangeratesapi.io/history?start_at="
					+ startDate
					+ "&end_at="
					+ endDate
					+ "&base="
					+ baseCurrency
					+ "&symbols="
					+ comparedCurrency;
	    	setULR(temp);
	    	cennect();	    	
	   }
	   
	   
	   public static void terminalInput() {
	    	setULR(constructUlr());
	    	cennect();
	    	shaowMenue();
	   }
	   
	   
	   public static void shaowMenue() {
		   int num;
		   myObj = new Scanner(System.in);
		   System.out.println("******************************************************************** ");
		   System.out.println("                                                                     ");
		   System.out.println("# Enter 1 for hard code input; (hint change variables inside this method --hardCodeInput()-- )");
		   System.out.println("# Enter 2 for terminal input ");
		   System.out.println("# Enter 3 to view search history ");
		   System.out.println("# Enter-1 to exit the program ");
		   num = myObj.nextInt();
		   if (num==1) {hardCodeInput();}
		   if (num==2) {terminalInput();}
		   if (num==3) {viewSearchHistory();}
		   if (num==-1) {System.exit(0);}
		   else {System.out.println("Input not defined"); }
	   }
	   
	   
	    public static void main(String args[]) {	    	
	    	//Test serialize
	    	//serialize();	    	
	    	 shaowMenue();
	     }

	    
	    
	    
	    
	    
	

	    //Test serialize function
//		private static void serialize(){
//			Map<String, Map<String, Double>> rates = new HashMap<String, Map<String, Double>>();
//			Map<String, Double> Item1 = new HashMap<>();
//		    Map<String, Double> Item2 = new HashMap<>();
//		    Map<String, Double> Item3 = new HashMap<>();
//		    Map<String, Double> Item4 = new HashMap<>();
//		    Map<String, Double> Item5 = new HashMap<>();
//		    
//			Item1.put("SCR", .555);
//	        Item2.put("SCR", .554);
//	        Item3.put("SCR", .550);
//	        Item4.put("SCR", .53);
//	        Item5.put("SCR", .52);		
//	        
//	        rates.put("1233", Item1);
//	        rates.put("1245", Item2);
//	        rates.put("1235", Item3);
//	        rates.put("1334", Item4);
//	        rates.put("2345", Item5);
//	        
//	        ExchangeRateHistory eRH = new ExchangeRateHistory(
//	        		rates, 
//	        		"some Start date",
//	        		"USD" , 
//	        		"Some_end_date"
//	        		);
//	        String[] test = eRH.getMaxRate();
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();		
//           
//			// convert to json
//            String jsonStringFromObj = gson.toJson(eRH);
//            System.out.println("JSON : " + jsonStringFromObj);
//            System.out.println("test[0] : " + test[0] + "test[0] : " + test[1]);  
//		}
	    
	 }

