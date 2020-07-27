package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RankPage {
	
	Text title;
	Button btOk;
	VBox result;
	Stage stage;
	
	public RankPage(String movie, String actor) {
		title = new Text();
		title.setText("Movie Ranking");
		title.setFill(Color.web("#1849af"));
		title.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 25));
		
		String name = "Movie Name: " + movie;
		
		Text nameText = new Text(name);
		nameText.setFill(Color.web("#1849af"));
		nameText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		
		Text actorText = new Text();
		actorText.setFill(Color.web("#1849af"));
		actorText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		actorText.setText("Top Actor in the movie: " + actor);
		
		btOk = new Button("Ok");
		btOk.setAlignment(Pos.CENTER);
		btOk.setOnAction(e -> stage.close());
		
		btOk.setMinSize(90,20);
		btOk.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btOk.setStyle("-fx-text-base-color: #1849af;");
		
		result = new VBox();
		result.setSpacing(15);
		result.setPadding(new Insets(15, 15, 15, 15));
		result.setAlignment(Pos.CENTER);
		result.getChildren().addAll(title, nameText, actorText, btOk);
		
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color: #f8eadb;");
		border.setCenter(result);
		
		Scene scene = new Scene(border, 500, 500);
		stage = new Stage();
		stage.setTitle("Ranking");
		stage.setScene(scene);
		stage.show();
	}
}
