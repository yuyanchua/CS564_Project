package application;

import java.util.List;

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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchPage{
	Button btOk, btBack;
	
	Stage stage, newStage;
	Scene prevScene;
	
	String title, promptStr;

	Text promptTxt, errTxt;
	TextField input;
	
	Text movieText;
	
	HBox btBox;
	VBox textBox;
	
	List<Movie> movieList;

	public SearchPage(Stage stage, Scene prevScene, String title, String promptStr){
		//Initialize value
		
		this.stage = stage;
		this.prevScene = prevScene;
		this.title = title;
		this.promptStr = promptStr;
	}
	
	public void start() {
		
		BorderPane border  = new BorderPane();
		
		setupText();
		setupButton();
		
		border.setCenter(textBox);
		border.setBottom(btBox);
		border.setStyle("-fx-background-color: #f8eadb;");
		Scene scene = new Scene(border, 500, 300);

		newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle(title);
		newStage.show();
	}
	
	private void setupButton() {
		btOk = new Button("Ok");
		btBack = new Button("Back");
		
		btOk.setMinSize(90,20);
		btOk.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btOk.setStyle("-fx-text-base-color: #1849af;");
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");

		btOk.setOnAction(e -> searchInput());
		btBack.setOnAction(e -> newStage.close());
		
		btBox = new HBox();
		btBox.setPadding(new Insets(5, 5, 5, 5));
		btBox.setSpacing(5);
		btBox.getChildren().addAll(btOk, btBack);
		btBox.setAlignment(Pos.CENTER);
		
	}
	
	private void setupText() {
		promptTxt = new Text();
		promptTxt.setText("Enter the " + promptStr + ": ");
		
		input = new TextField();
		input.setPromptText(promptStr);

		if(title.equals("Search Ranking")) {
			movieText = new Text();
			movieText.setText("Enter Movie Name: ");
		}
		
		errTxt = new Text();
		
		errTxt.setVisible(false);
		errTxt.setFill(Color.RED);
		
		textBox = new VBox();
		textBox.setPadding(new Insets(15, 15, 15, 15));
		textBox.setSpacing(5);
		textBox.setAlignment(Pos.CENTER_LEFT);
		textBox.getChildren().addAll(promptTxt, input, errTxt);
		
	}
	
	
	private void searchInput() {
		if(input.getText().isEmpty()) {
			errTxt.setText("Empty input. Please try again!");
			errTxt.setVisible(true);
			return;
		}
		
		String inputText = input.getText();
		
		//retrieve database based on Title + Prompt
		if(title.equals("Search Filmography") && promptStr.equals("Actor's Name")) {
			//search movie with actor
			List<String> list = new Database().retrieveMovie(inputText, true);
			if(list	 == null || list.isEmpty()) {
				notFound();
				return;
			}
			
			new ResultPage(list, inputText, true, true).start();
		}
		
		if(title.equals("Search Movie Cast") && promptStr.equalsIgnoreCase("Movie Name")) {
			List<String> actorList = new Database().retrieveActor(inputText);
			
			if(actorList == null || actorList.isEmpty()) {
				notFound();
				return;
			}
			new ResultPage(actorList, inputText, true, false).start();
		}
		
		if(title.equals("Search Movie Director") && promptStr.equalsIgnoreCase("Movie Name")) {
			List<String> dirList = new Database().retrieveDirector(inputText);
			
			if(dirList == null || dirList.isEmpty()) {
				notFound();
				return;
			}
			
			new ResultPage(dirList, inputText, false, false).start();
		}
		
		if(title.equals("Search Filmography") && promptStr.equalsIgnoreCase("Director's Name")) {

			List<String> list = new Database().retrieveMovie(inputText, false);
			if(list == null || list.isEmpty()) {
				notFound();
				return;
			}
			
			new ResultPage(list, inputText, false, true).start();
		}
		
		if(title.equals("Search Rating") && promptStr.equalsIgnoreCase("Movie Name")) {
			Movie movie = new Database().retrieveMovie(inputText);
			
			
			if(movie == null) {
				notFound();
				return;
			}
			
			double rating = new Database().retrieveRating(movie);
			new ResultPage(rating, inputText).start();
			
		}
		
		if(title.equals("Search Filmography") && promptStr.equalsIgnoreCase("Movie Name")) {
			Movie movie = new Database().retrieveMovie(inputText);
			//if contains
			movieList.add(movie);
			System.out.println(movie);
		}
		
		if (title.equals("Search Ranking")) {
			String actorName = new Database().retrieveRanking(inputText);
			if(actorName == null) {
				notFound();
				return;
			}
			new RankPage(inputText, actorName);
		}
		newStage.close();
	}
	
	private void notFound() {
		errTxt.setText("Result not found. Please try again!");
		errTxt.setVisible(true);
	}
	
}