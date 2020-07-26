package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CompareMenu {
	Stage stage;
	Scene prevScene;
	
	Button btRanking, btCompare, btBack;
	
	TextInputDialog rankDialog, compDialog;
	EventHandler<ActionEvent> rankEvent, compEvent;
	VBox btBox;
	
	Scene scene;
	
	int resultNum = 0;
	List<Movie> compareList;
	
	public CompareMenu(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		//setupRankingDialog();
		//setupCompareDialog();
		setupButton();
		
		
		BorderPane border = new BorderPane();
		border.setCenter(btBox);
		Text top = new Text();
		top.setText("            Highlights");
		border.setTop(top);
		top.setFill(Color.web("#1849af"));
		top.setFont(Font.font("Abhaya",FontPosture.ITALIC, 41));
		border.setPadding(new Insets(43, 0, 0, 4));
		border.setStyle("-fx-background-image: url('title.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 150 150;"
				+ "-fx-background-repeat: no-repeat;");
		
		scene = new Scene(border, 500, 400);
		stage.setScene(scene);
		stage.show();
	}
	
	private void setupButton() {
		btRanking = new Button("Show Movie Star");
		btCompare = new Button("Compare Movie");
		btBack = new Button("Back");
		
		btRanking.setMinSize(90,20);
		btRanking.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btRanking.setStyle("-fx-text-base-color: #1849af;");
		btCompare.setMinSize(90,20);
		btCompare.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btCompare.setStyle("-fx-text-base-color: #1849af;");
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		btBox = new VBox();
		btBox.setSpacing(5);
		btBox.getChildren().addAll(btRanking, btCompare, btBack);
		btBox.setAlignment(Pos.CENTER);
		
		btRanking.setOnAction(e -> new SearchPage(stage, scene,
				"Search Ranking", "Movie Name").start());;
//		btCompare.setOnAction(e -> new SearchPage(stage, scene,
//				"Compare Movie", "number of Movie to Compare").start());;
				
//		btCompare.setOnAction(e -> compareMovie());
		btCompare.setOnAction(e -> setCompare());
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void setCompare() {
		new CompareInputPage(this);
//		int index = 0;
//		compareList = new ArrayList<>();
		
		
//		do {
//			new SearchPage(this).start();
//			index ++;
//			
//		}while(index < resultNum);
	}
	
	public void setMovieNum(int resultNum) {
		this.resultNum = resultNum;
		//get num
//		CompareInputPage inputPage = new CompareInputPage();
//		int resultNum = inputPage.getInputNum();
		
		System.out.println(resultNum);
		
//		SearchPage searchNum = new SearchPage(stage, scene, "Compare Movie", "Number of Movie to Compare");
//		
//		int resultNum = searchNum.getResultNum();
		//for each num, get movie
		
		
		//parse movie into compare page

	}
	
	public void addMovie(Movie movie) {
		compareList.add(movie);
		System.out.println(movie);
	}

}
