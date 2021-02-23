package view;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/*https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-09-01&base=USD&symbols=ILS*/

	public class MainApp extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				primaryStage.setTitle("Alantra-Exchange");
				Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("MainPageCss.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
		public static void main(String[] args) {
			launch(args);
		}
	
	}

    


   
    
   
    
   
   
    
    
