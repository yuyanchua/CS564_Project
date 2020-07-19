package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchPage {
	
	Button btRanking, btSearchActMovie, btSearchActor, btSearchDir, btSearchDirMovie, btRate, btBack;
	Button btDisplay;
	VBox btBox;
	HBox hbox;
	TextField keyField;
	Stage stage;
	Scene prevScene;
	
	public SearchPage(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		setupText();
		setupButton();
		
		BorderPane border= new BorderPane();
		
		border.setCenter(btBox);
		
		Scene scene = new Scene(border, 500, 650);
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void setupText() {
		Label lbText = new Label();
		lbText.setText("Keyword: ");
		
		keyField = new TextField();
		
		
		hbox = new HBox();
		
		hbox.setSpacing(5);
		hbox.getChildren().addAll(lbText, keyField);
		hbox.setAlignment(Pos.CENTER);
		
	}
	
	private void setupButton() {
		
//		btRanking = new Button("Show Top Nth Ranking Actors, default = 5");
		btSearchActMovie = new Button("Search Actor by Movie");
		btSearchActor = new Button("Search Movie by Actor");
		btSearchDir = new Button("Search Director by Movie");
		btSearchDirMovie = new Button("Search Movie by Director");
		btRate = new Button("Get Average Rating of A Movie");
		
		btDisplay = new Button("Show all data");
		btBack = new Button("Back");
		
		btBox = new VBox();
		btBox.setSpacing(5);
		btBox.getChildren().addAll(hbox, btSearchActMovie, btSearchActor, 
				btSearchDir, btSearchDirMovie, btRate, btDisplay, btBack);
		btBox.setAlignment(Pos.CENTER);
		
//		btRanking.setOnAction(e -> showRanking());
		
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	
//	private void showRanking() {
//		int num = 5;
//		try {
//			num = Integer.parseInt(keyField.getText());
//		}catch(Exception ex) {
////			ex.printStackTrace();
//			//errorMsg
//			System.out.println("Please enter a number!");
//		}
//		new RankPage(num).showTable();
//	}
	
}
