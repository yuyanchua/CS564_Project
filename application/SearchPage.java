package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchPage {
	
	Button btRanking, btSearchActMovie, btSearchActor, btSearchDir, btSearchDirMovie, btRate, btBack;
	Button btDisplay;
	VBox vbox;
	
	Stage stage;
	Scene prevScene;
	
	public SearchPage(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		setupButton();
		
		BorderPane border= new BorderPane();
		
		border.setCenter(vbox);
		
		Scene scene = new Scene(border, 500, 400);
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void setupButton() {
		
		
		btRanking = new Button("Show Top Nth Ranking Actors");
		btSearchActMovie = new Button("Search Actor by Movie");
		btSearchActor = new Button("Search Movie by Actor");
		btSearchDir = new Button("Search Director by Movie");
		btSearchDirMovie = new Button("Search Movie by Director");
		btRate = new Button("Get Average Rating of A Movie");
		
		btDisplay = new Button("Show all data");
		btBack = new Button("Back");
		
		vbox = new VBox();
		vbox.setSpacing(5);
		vbox.getChildren().addAll(btRanking, btSearchActMovie, btSearchActor, 
				btSearchDir, btSearchDirMovie, btRate, btDisplay, btBack);
		vbox.setAlignment(Pos.CENTER);
		
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
}
