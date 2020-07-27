package application;

import java.util.ArrayList;
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

public class SearchCompareMovie {
	int compareNum;
	List<Movie> movieList;
	
	Button btConfirm,  btBack;
	Stage stage;
	
	VBox textBox;
	HBox btBox;
	
	Text [] promptText, errText;
	String title;
	String prompt;
	TextField [] input;
	
	public SearchCompareMovie(int compareNum) {
		this.title = "Search Filmography";
		this.prompt = "Movie Name: ";
		this.compareNum = compareNum;
		
		movieList = new ArrayList<>();
		System.out.println("Search for: " + compareNum);
	}
	
	public void start() {
		BorderPane border = new BorderPane();
		
		setupText();
		setupButton();
		
		border.setCenter(textBox);
		border.setBottom(btBox);
		border.setStyle("-fx-background-color: #f8eadb;");
		
		Scene scene = new Scene(border, 500, 650);
		
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
	
	private void setupButton() {
		btConfirm = new Button("Confirm");
		btBack = new Button("Back");
		
		btConfirm.setMinSize(90,20);
		btConfirm.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btConfirm.setStyle("-fx-text-base-color: #1849af;");
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		btConfirm.setOnAction(e -> compareMovie());
		btBack.setOnAction(e -> stage.close());
		
		btBox = new HBox();
		btBox.setPadding(new Insets(15, 15, 15, 15));
		btBox.setSpacing(15);
		btBox.getChildren().addAll(btConfirm, btBack);
		btBox.setAlignment(Pos.CENTER);
	}
	
	private void setupText() {
		promptText = new Text[compareNum];
		errText = new Text[compareNum];
		input = new TextField[compareNum];
		
		textBox = new VBox();
		textBox.setPadding(new Insets(15, 15, 15, 15));
		textBox.setSpacing(15);
		textBox.setAlignment(Pos.CENTER_LEFT);
		
		for(int i = 0; i < compareNum; i ++) {
			promptText[i] = new Text();
			promptText[i].setText("Enter " + prompt + (i + 1) + " : ");
			
			input[i] = new TextField();
			input[i].setPromptText(prompt);
			
			errText[i] = new Text();
			errText[i].setText("Movie " + (i+ 1) + " Not Found! ");
			errText[i].setFill(Color.RED);
			errText[i].setVisible(false);
			
			textBox.getChildren().addAll(promptText[i], input[i], errText[i]);
		}

	}
	
	private void compareMovie() {
		searchInput();
		if(movieList.size() == compareNum) {
			System.out.println(movieList.size());
			new ComparePage(this.movieList).showTable();
			stage.close();
		}
	}
	
	private void searchInput() {
		boolean repeat = false;
		
		for(int i = 0; i < compareNum; i ++) {
			
			if(input[i].getText().isEmpty()) {
				errText[i].setText("Empty input. Please try again!");
				errText[i].setVisible(true);
				return;
			}
			
			String inputText = input[i].getText();
			Movie movie = new Database().retrieveMovie(inputText);
			if(movie == null) {
				errText[i].setText("Movie " + (i + 1) + " not found. Please try again!");
				errText[i].setVisible(true);
				movieList.clear();
				break;
			}
			
			repeat = isContain(movie);
			
			
			if(!isContain(movie)) {
				movieList.add(movie);
			}else {
				errText[i].setText("Repeated movie. Please try again!");
				errText[i].setVisible(true);
			}
			
		}
		
		if(repeat)
			movieList.clear();
		
		
	}
	
	private boolean isContain(Movie movie) {
		for(int i = 0; i < movieList.size(); i ++) {
			Movie temp = movieList.get(i);
			if(temp.title.equals(movie.title) || temp.movieId == movie.movieId) {
				errText[i].setText("Repeated movie. Please try again!");
				errText[i].setVisible(true);
				return true;
			}
		}
		
		return false;
	}
	
	


	
}
