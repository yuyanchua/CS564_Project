package application;

import java.util.List;

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

public class ResultPage {

	Text title;
	Button btOk;
	VBox result;
	Stage stage;
	
	public ResultPage(double rating, String movie) {
		
		title = new Text();
		title.setText("Search Result");
		title.setFill(Color.web("#1849af"));
		title.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 25));
		
		
		String name = "Movie Name: " + movie;
		String rate = "Average Rating: " + rating;
		
		Text nameText = new Text(name);
		nameText.setFill(Color.web("#1849af"));
		nameText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		Text rateText = new Text(rate);
		rateText.setFill(Color.web("#1849af"));
		rateText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		btOk = new Button("Ok");
		btOk.setAlignment(Pos.CENTER);
		btOk.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btOk.setStyle("-fx-text-base-color: #1849af;");
		
		btOk.setOnAction(e -> stage.close());
		
		result = new VBox();
		result.setSpacing(15);
		result.setPadding(new Insets(15, 15, 15, 15));
		result.setAlignment(Pos.CENTER);
		result.getChildren().addAll(title, nameText, rateText, btOk);
	}
	
	public ResultPage(List<String> list, String input, boolean isActor, boolean isMovie) {
		
		title = new Text();
		title.setText("Search Result");
		title.setFill(Color.web("#1849af"));
		title.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 25));
		
		
		result = new VBox();
		result.setSpacing(15);
		result.setPadding(new Insets(15, 15, 15, 15));
		result.setAlignment(Pos.CENTER);

		btOk = new Button("Ok");
		btOk.setAlignment(Pos.CENTER);
		btOk.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btOk.setStyle("-fx-text-base-color: #1849af;");
		
		btOk.setOnAction(e -> stage.close());
		
		if(isMovie) {
			String name = "";
			if(isActor)
				name = "Actor Name: " + input;
			else
				name = "Director Name: " + input;

			String movieName = "Movie Directed: " + convertToString(list);

			Text nameText = new Text(name);
			nameText.setFill(Color.web("#1849af"));
			nameText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
			
			Text movieText = new Text(movieName);
			movieText.setFill(Color.web("#1849af"));
			movieText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
			

			result.getChildren().addAll(title, nameText, movieText, btOk);

		}else {
			String movieName = "Movie Name:" + input;
			String name = "";
			if(isActor)
				name = "Movie Cast: " + convertToString(list);
			else
				name = "Director by: " + convertToString(list);
			Text movieText = new Text(movieName);
			movieText.setFill(Color.web("#1849af"));
			movieText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
			
			Text nameText = new Text(name);
			nameText.setFill(Color.web("#1849af"));
			nameText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
			
			result.getChildren().addAll(title, movieText, nameText, btOk);
		}
	}
	
	
	
	private String convertToString(List<String> list) {
		String str = "";
		for(String temp: list) {
			str += temp + ", \t\n";
		}
		
		return str;
	}
	
	public void start() {
		
		BorderPane border = new BorderPane();
		
		border.setCenter(result);
		border.setStyle("-fx-background-color: #f8eadb;");
		
		Scene scene = new Scene(border, 500, 600);
		
		stage = new Stage();
		stage.setTitle("Result");
		stage.setScene(scene);
		stage.show();
	}
}
