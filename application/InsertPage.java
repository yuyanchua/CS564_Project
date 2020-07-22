package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Movie Id -> increment
//Director Id -> auto-gen
//Actor id -> auto-gen

public class InsertPage {
	
	Stage prevStage, stage;
	Scene prevScene;
	
	Button btAdd, btBack;
	
	TextField tfTitle, tfYear, tfCountry, tfRTCRating, tfRTAurRating;
	TextField tfAudRateNum, tfDirectorName, tfActorName;
	TextField tfRanking, tfUserId, tfRating;
	
	Text errorMsg;
	
	GridPane grid;
	
	HBox btBox;
	
	int insertType; 
	
	public InsertPage(Stage prevStage, Scene prevScene, int insertType) {
		this.prevStage = prevStage;
		this.prevScene = prevScene ;
		this.insertType = insertType;
		
		setupGrid();
		
		switch(insertType) {
		case 0:
			//Insert Movie
			setupMovieInput();
			break;
		case 1:
			//Insert Actor
			setupActorInput();
			break;
		case 2:
			//Insert Director
			setupDirectorInput();
			break;
		case 3:
			//Insert Rating
			setupUserInput();
			break;
		}
		
	}
	
	public void start() {
//		setupInputField();
		setupBt();
		setupError();
		
		BorderPane border = new BorderPane();
		VBox vbox = new VBox();
		
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(grid, errorMsg, btBox);

		border.setCenter(vbox);
//		border.setBottom(btBox);
		border.setStyle("-fx-background-color: #f8eadb;");
		
		Scene scene = new Scene(border, 800, 750);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	private void setupGrid() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(15);
		grid.setVgap(15);
	}
	
	private void setupError() {
		errorMsg = new Text();
		errorMsg.setText("Invalid Input!");
		errorMsg.setFill(Color.RED);
		errorMsg.setVisible(false);
		
	}
	
	private void setupMovieInput() {
		Label lbTitle = new Label("Title: ");
		Label lbYear = new Label("Year: ");
		Label lbCountry = new Label("Country: ");
		Label lbRTCRating = new Label("RT Critics Rating: ");
		Label lbRTAurRating = new Label("RT Audience Rating: ");
		Label lbAudRateNum = new Label("RT Audience Rating Number: ");
		
		grid.add(lbTitle, 0, 0);
		grid.add(lbYear, 0, 1);
		grid.add(lbCountry, 0, 2);
		grid.add(lbRTCRating, 0, 3);
		grid.add(lbRTAurRating, 0, 4);
		grid.add(lbAudRateNum, 0, 5);
		
		
		tfTitle = new TextField();
		tfYear = new TextField();
		tfCountry = new TextField();
		tfRTCRating = new TextField();
		tfRTAurRating = new TextField();
		tfAudRateNum = new TextField();
		
		grid.add(tfTitle, 1, 0);
		grid.add(tfYear, 1, 1);
		grid.add(tfCountry, 1, 2);
		grid.add(tfRTCRating, 1, 3);
		grid.add(tfRTAurRating, 1, 4);
		grid.add(tfAudRateNum, 1, 5);
	}
	
	private void insertMovie() {
		
		int movieId = 0;
		String title = tfTitle.getText();
		int year = Integer.parseInt(tfTitle.getText());
		String country = tfTitle.getText();
		double RTCriticsRating = Double.parseDouble(tfRTCRating.getText());
		double RTAudienceRating = Double.parseDouble(tfRTAurRating.getText());
		int RTAudRateNum = Integer.parseInt(tfAudRateNum.getText());
		
		Movie movie = new Movie(movieId, title, year, country, RTCriticsRating, 
				RTAudienceRating, RTAudRateNum);
		
		//insert movie into database
		
	}
	
	private void setupDirectorInput() {
		Label lbDirectorName = new Label("Director Name: ");
		Label lbMovie = new Label("Directed Movie: ");
		
		grid.add(lbDirectorName, 0, 0);
		grid.add(lbMovie, 0, 1);
		
		tfDirectorName = new TextField();
		tfTitle = new TextField();
		
		grid.add(tfDirectorName, 1, 0);
		grid.add(tfTitle, 1, 1);
	}
	
	private void insertDirector() {
		String directorName = tfDirectorName.getText();
		String directorId = directorName.toLowerCase().replace(" ", "_");
		String movieName = tfTitle.getText();
		
		int movieId = getMovieId(movieName);
		
		Director director = new Director(directorId, directorName, movieId);
		
		
	}
	
	
	
	private void setupActorInput() {
		Label lbActorName = new Label("Actor Name: ");
		Label lbRanking = new Label("Ranking: ");
		Label lbMovie = new Label("Acted Movie: ");
		
		grid.add(lbActorName, 0, 0);
		grid.add(lbRanking, 0, 1);
		grid.add(lbMovie, 0, 2);
		
		tfActorName = new TextField();
		tfRanking = new TextField();
		tfTitle = new TextField();
		
		grid.add(tfActorName, 1, 0);
		grid.add(tfRanking, 1, 1);
		grid.add(tfTitle, 1, 2);
		
	}
	
	private void insertActor() {
		try {
			String movie = tfTitle.getText();
			String actorName = tfActorName.getText();
			int ranking = Integer.parseInt(tfRanking.getText());
			String actorId = actorName.toLowerCase().replace(" ", "_");

			int movieId = getMovieId(movie);
			
			Actor actor = new Actor(movieId, actorId, actorName, ranking);
		}catch(Exception ex) {
			errorMsg.setVisible(true);
		}

	}
	
	private int getMovieId(String movie) {
		return -1;
	}
	
	private void setupUserInput() {
		//search movie or input movie name?
		Label lbMovie = new Label("Movie: ");
		Label lbUser = new Label("User: ");
		Label lbRating = new Label("Rating: ");
		
		grid.add(lbMovie, 0, 0);
		grid.add(lbUser, 0, 1);
		grid.add(lbRating, 0, 2);
		
		tfTitle = new TextField();
		tfUserId = new TextField();
		tfRating = new TextField();
		
		grid.add(tfTitle, 1, 0);
		grid.add(tfUserId, 1, 1);
		grid.add(tfRating, 1, 2);
	}
	
	private void insertRating() {
		String movieName = tfTitle.getText();
		String userId = tfUserId.getText();
		double rating = Double.parseDouble(tfRating.getText());
		
		int movieId = getMovieId(movieName);
		
		Rate rate = new Rate(movieId, userId, rating);
	}
	
	

	
//	private void setupInputField() {
//		grid = new GridPane();
//		grid.setAlignment(Pos.CENTER);
//		grid.setPadding(new Insets(5, 5, 5, 5));
//		grid.setHgap(15);
//		grid.setVgap(15);
//		
//		Label lbMovieId = new Label("Movie Id: ");
//		Label lbTitle = new Label("Title: ");
//		Label lbYear = new Label("Year: ");
//		Label lbCountry = new Label("Country: ");
//		Label lbRTCRating = new Label("RT Critics Rating: ");
//		Label lbRTAurRating = new Label("RT Audience Rating: ");
//		Label lbAudRateNum = new Label("RT Audience Rating Number: ");
//		Label lbDirectorId = new Label("Director Id: ");
//		Label lbDirectorName = new Label("Director Name: ");
//		Label lbActorId = new Label("Actor Id: ");
//		Label lbActorName = new Label("Actor Name: ");
//		Label lbRanking = new Label("Ranking: ");
//		Label lbUserId = new Label("User Id: ");
//		Label lbRating = new Label("Rating: ");
//		
//		
//		grid.add(lbMovieId, 0, 0);
//		grid.add(lbTitle, 0, 1);
//		grid.add(lbYear, 0, 2);
//		grid.add(lbCountry, 0, 3);
//		grid.add(lbRTCRating, 0, 4);
//		grid.add(lbRTAurRating, 0, 5);
//		grid.add(lbAudRateNum, 0, 6);
//		grid.add(lbDirectorId, 0, 7);
//		grid.add(lbDirectorName, 0, 8);
//		grid.add(lbActorId, 0, 9);
//		grid.add(lbActorName, 0, 10);
//		grid.add(lbRanking, 0, 11);
//		grid.add(lbUserId, 0, 12);
//		grid.add(lbRating, 0, 13);
//		
////		tfMovieId = new TextField();
//		tfTitle = new TextField();
//		tfYear = new TextField();
//		tfCountry = new TextField();
//		tfRTCRating = new TextField();
//		tfRTAurRating = new TextField();
//		tfAudRateNum = new TextField();
////		tfDirectorId = new TextField();
//		tfDirectorName = new TextField();
////		tfActorId = new TextField();
//		tfActorName = new TextField();
//		tfRanking = new TextField();
//		tfUserId = new TextField();
//		tfRating = new TextField();
//		
////		grid.add(tfMovieId, 1, 0);
//		grid.add(tfTitle, 1, 1);
//		grid.add(tfYear, 1, 2);
//		grid.add(tfCountry, 1, 3);
//		grid.add(tfRTCRating, 1, 4);
//		grid.add(tfRTAurRating, 1, 5);
//		grid.add(tfAudRateNum, 1, 6);
////		grid.add(tfDirectorId, 1, 7);
//		grid.add(tfDirectorName, 1, 8);
////		grid.add(tfActorId, 1, 9);
//		grid.add(tfActorName, 1, 10);
//		grid.add(tfRanking, 1, 11);
//		grid.add(tfUserId, 1, 12);
//		grid.add(tfRating, 1, 13);
//	}
	
	private void setupBt() {
		btBox = new HBox();
		
		btAdd = new Button("Add");
		btBack = new Button("Back");
		
		btBox.getChildren().addAll(btAdd, btBack);
		btBox.setSpacing(5);
		btBox.setPadding(new Insets(5, 5, 5, 5));
		btBox.setAlignment(Pos.CENTER);
//		btBack.setOnAction(e -> prevStage.setScene(prevScene));
		btBack.setOnAction(e -> stage.close());
	}
	
	
}
