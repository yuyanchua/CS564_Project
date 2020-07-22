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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchPage{
	Button btOk, btBack;
	
	Stage stage, newStage;
	Scene prevScene;
	
	String title, promptStr;

	Text promptTxt, errTxt;
	TextField input;
	
	HBox btBox;
	VBox textBox;
	
//	int searchType;
	
	public SearchPage(Stage stage, Scene prevScene, String title, String promptStr){
		//Initialize value
		
		this.stage = stage;
		this.prevScene = prevScene;
//		this.searchType = searchType;
		this.title = title;
		this.promptStr = promptStr;
//		
//		switch(searchType) {
//			//setTitle, searchFunction
//		case 0: //Search Actor's film
////			System.out.println("Search Actor's film");
//			title = "Search Filmography";
//			promptStr = "Actor's Name";
//			break;
//	
//		case 1: //Search movie cast
//			title = "Search Movie Cast";
//			promptStr = "Movie Name";
//			break;
//			
//		case 2: //Search movie Director
//			title = "Search Movie Director";
//			promptStr = "Movie Name";
//			break;
//	
//		case 3: //Search Director's film
//			title = "Search Filmography";
//			promptStr = "Director's Name";
//			break;
//			
//		case 4: //Find Movie Rating
//			title = "Search Rating";
//			promptStr = "Movie Name";
//		}
		
	}
	
	public void start() {
		
		BorderPane border  = new BorderPane();
		
		setupText();
		setupButton();
		
		border.setCenter(textBox);
		border.setBottom(btBox);
		
		Scene scene = new Scene(border, 500, 200);
		
		newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle(title);
		newStage.show();
	}
	
	private void setupButton() {
		btOk = new Button("Ok");
		btBack = new Button("Back");
		
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
		
		
		errTxt = new Text();
		errTxt.setText("Invalid input. Please try again!");
		errTxt.setVisible(false);
		errTxt.setFill(Color.RED);
		
		textBox = new VBox();
		textBox.setPadding(new Insets(15, 15, 15, 15));
		textBox.setSpacing(5);
		textBox.setAlignment(Pos.CENTER_LEFT);
		textBox.getChildren().addAll(promptTxt, input, errTxt);
		
	}
	
	private void searchInput() {
//		System.out.println(input.getText());
		if(input.getText().isEmpty()) {
//			System.out.println("null");
			errTxt.setVisible(true);
			return;
		}
		
		String inputText = input.getText();
		
		//retrieve database based on Title + Prompt
		
	}
	
	
	
	
	
	
}