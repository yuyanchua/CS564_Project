package application;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultPage {

	Text title;
	Button btOk;
	VBox result;
	Stage stage;
	
	public ResultPage(double rating, String movie) {
		String name = "Movie Name: " + movie;
		String rate = "Average Rating: " + rating;
		
		Text nameText = new Text(name);
		Text rateText = new Text(rate);
		
		result = new VBox();
		result.setSpacing(15);
		result.setPadding(new Insets(15, 15, 15, 15));
		result.setAlignment(Pos.CENTER);
		result.getChildren().addAll(nameText, rateText);
		
		setupButton();
	}
	
	public ResultPage(Movie movie, String input, boolean isActor) {
		String name = "";
		if(isActor)
			name = "Actor Name: " + input;
		else
			name = "Director Name: " + input;
		
		String movieName = "Movie Directed: " + movie.getTitle();
		
		Text nameText = new Text(name);
		Text movieText = new Text(movieName);
		
		result = new VBox();
		result.setSpacing(15);
		result.setPadding(new Insets(15, 15, 15, 15));
		result.setAlignment(Pos.CENTER);
		result.getChildren().addAll(nameText, movieText);
		
		setupButton();
	}
	
	public ResultPage(Director director, String movie) {
		String movieName = "Movie Name: " + movie;
		String directorName = "Directed by: " + director.directorName;
		
		Text movieText = new Text(movieName);
		Text dirText = new Text(directorName);
		
		result = new VBox();
		result.setSpacing(15);
		result.setPadding(new Insets(15, 15, 15, 15));
		result.setAlignment(Pos.CENTER);
		result.getChildren().addAll(movieText, dirText);
		
		setupButton();
	}
	
	public ResultPage(List<Actor> list, String movie) {
		String movieName = "Movie Name:" + movie;
		String actorName = "Movie Cast: " + convertToString(list);
		
		Text movieText = new Text(movieName);
		Text actorText = new Text(actorName);
		
		result = new VBox();
		result.setSpacing(15);
		result.setPadding(new Insets(15, 15, 15, 15));
		result.setAlignment(Pos.CENTER);
		result.getChildren().addAll(movieText, actorText);
		
		setupButton();
		
	}
	
	
	
	private String convertToString(List<Actor> list) {
		String actorStr = "";
		for(Actor actor: list) {
			actorStr += actor.getActorName() + ", ";
		}
		
		return actorStr;
	}
	
	public void start() {
		
		BorderPane border = new BorderPane();
		
		title = new Text();
		title.setText("Search Result");
		
		border.setTop(title);
		border.setCenter(result);
		border.setBottom(btOk);
		
		Scene scene = new Scene(border, 500, 500);
		stage = new Stage();
		stage.setTitle("Result");
		stage.setScene(scene);
		stage.show();
	}
	
	private void setupButton() {
		btOk = new Button("Ok");
		btOk.setAlignment(Pos.CENTER);
		btOk.setOnAction(e -> stage.close());
	}
	
	
}
