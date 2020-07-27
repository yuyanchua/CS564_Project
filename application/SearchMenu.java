package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SearchMenu {
	
	Button btRanking, btSearchActMovie, btSearchActor, btSearchDir, btSearchDirMovie, btRate, btBack;
	Button btDisplay;
	VBox btBox;
	HBox hbox;
	TextField keyField;
	Stage stage;
	Scene prevScene, scene;
	
	public SearchMenu(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		stage.setTitle("Search");
		setupButton();
		
		BorderPane border= new BorderPane();
		

		border.setCenter(btBox);
		
		
		Scene scene = new Scene(border, 500, 400);
		Text top = new Text();
		top.setText("            Search Movie");
		border.setTop(top);
		top.setFill(Color.web("#1849af"));
		top.setFont(Font.font("Abhaya",FontPosture.ITALIC, 41));
		border.setPadding(new Insets(43, 0, 0, 4));
		border.setStyle("-fx-background-image: url('title.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 150 150;"
				+ "-fx-background-repeat: no-repeat;");
		stage.setScene(scene);
		stage.show();
		
	}
	

	
	private void setupButton() {
		
		btSearchActMovie = new Button("Find Actor's Filmography");
		btSearchActMovie.setMinSize(200,30);
		btSearchActMovie.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearchActMovie.setStyle("-fx-text-base-color: #1849af;");
		
		btSearchActor = new Button("Find Movie Cast");
		btSearchActor.setMinSize(200,30);
		btSearchActor.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearchActor.setStyle("-fx-text-base-color: #1849af;");
		
		btSearchDir = new Button("Find Movie Director");
		btSearchDir.setMinSize(200,30);
		btSearchDir.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearchDir.setStyle("-fx-text-base-color: #1849af;");
		
		btSearchDirMovie = new Button("Find Director's Filmography");
		btSearchDirMovie.setMinSize(200,30);
		btSearchDirMovie.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearchDirMovie.setStyle("-fx-text-base-color: #1849af;");
		
		btRate = new Button("Find Movie Rating");
		btRate.setMinSize(200,30);
		btRate.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btRate.setStyle("-fx-text-base-color: #1849af;");

		btBack = new Button("Back");
		btBack.setMinSize(90,30);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		btBox = new VBox();
		btBox.setSpacing(5);
		btBox.getChildren().addAll(btSearchActMovie, btSearchDirMovie, btSearchActor, 
				btSearchDir, btRate, btBack);
		btBox.setAlignment(Pos.CENTER);
		
		btSearchActMovie.setOnAction(e -> new SearchPage(stage, scene,
				"Search Filmography", "Actor's Name").start());
		btSearchActor.setOnAction(e -> new SearchPage(stage, scene,
				"Search Movie Cast", "Movie Name").start());
		btSearchDir.setOnAction(e -> new SearchPage(stage, scene,
				"Search Movie Director", "Movie Name").start());
		btSearchDirMovie.setOnAction(e -> new SearchPage(stage, scene, 
				"Search Filmography", "Director's Name").start());
		btRate.setOnAction(e -> new SearchPage(stage, scene, 
				"Search Rating", "Movie Name").start());
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
}
