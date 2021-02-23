package controller;



import java.net.URL;
import java.util.ResourceBundle;
//import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;


public class PortalController {

	    @FXML // ResourceBundle that was given to the FXMLLoader
	    private ResourceBundle resources;

	    @FXML // URL location of the FXML file that was given to the FXMLLoader
	    private URL location;

	    @FXML // fx:id="currencyFromChoiceBox"
	    private ChoiceBox<?> currencyFromChoiceBox; // Value injected by FXMLLoader

	    @FXML // fx:id="currencyToChoiceBox"
	    private ChoiceBox<?> currencyToChoiceBox; // Value injected by FXMLLoader

	    @FXML // fx:id="startDateDatePicker"
	    private DatePicker startDateDatePicker; // Value injected by FXMLLoader

	    @FXML // fx:id="endDateDatePicker"
	    private DatePicker endDateDatePicker; // Value injected by FXMLLoader

	    @FXML // fx:id="outptTextArea"
	    private TextArea outptTextArea; // Value injected by FXMLLoader

	    @FXML
	    void historyButtonHandle(ActionEvent event) {

	    }

	    @FXML
	    void saveButtonHandle(ActionEvent event) {

	    }
	    
	    

	    @FXML // This method is called by the FXMLLoader when initialization is complete
	    void initialize() {
	        assert currencyFromChoiceBox != null : "fx:id=\"currencyFromChoiceBox\" was not injected: check your FXML file 'MainPage.fxml'.";
	        assert currencyToChoiceBox != null : "fx:id=\"currencyToChoiceBox\" was not injected: check your FXML file 'MainPage.fxml'.";
	        assert startDateDatePicker != null : "fx:id=\"startDateDatePicker\" was not injected: check your FXML file 'MainPage.fxml'.";
	        assert endDateDatePicker != null : "fx:id=\"endDateDatePicker\" was not injected: check your FXML file 'MainPage.fxml'.";
	        assert outptTextArea != null : "fx:id=\"outptTextArea\" was not injected: check your FXML file 'MainPage.fxml'.";

	    }
	    
	    
	    
	    
	    
	}

	
	

