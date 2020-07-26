package application;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Movie Id -> increment
//Director Id -> auto-gen
//Actor id -> auto-gen

public class InsertPage {
	
	Stage prevStage, stage;
	Scene prevScene;
	Account account;
	
	Button btAdd, btBack;
	
	TextField tfTitle, tfYear, tfCountry, tfRTCRating, tfRTAurRating;
	TextField tfAudRateNum, tfDirectorName, tfActorName;
	TextField tfRanking;
	//, tfUserId;
	ComboBox<String> tfRating;
	
	Text errorMsg;
	
	GridPane grid;
	
	HBox btBox;
	
	int insertType; 
	
	public InsertPage(Stage prevStage, Scene prevScene, Account account) {
		this.prevStage = prevStage;
		this.prevScene = prevScene ;
		this.account = account;
		
		errorMsg = new Text();
		errorMsg.setFill(Color.RED);
		errorMsg.setVisible(false);
		setupGrid();
		
		setupUserInput();
		
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
		Text top = new Text();
		top.setText("            Rate A Movie");
		border.setTop(top);
		top.setFill(Color.web("#1849af"));
		top.setFont(Font.font("Abhaya",FontPosture.ITALIC, 41));
		border.setPadding(new Insets(43, 0, 0, 4));
		border.setStyle("-fx-background-image: url('title.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 150 150;"
				+ "-fx-background-repeat: no-repeat;");
		Scene scene = new Scene(border, 500, 400);
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
	

	
	private int getMovieId(String movieName) {
//		List<Movie> movieList = new 
		Movie movie = new Database().retrieveMovie(movieName);
		if (movie == null) {
			errorMsg.setText("This movie does not exist in our database.");
			errorMsg.setFill(Color.RED);
			errorMsg.setVisible(true);
			return -1;
		}
		
		return movie.getMovieId();
	}
	
	private void setupUserInput() {
		//search movie or input movie name?
		Label lbMovie = new Label("Movie: ");
		//Label lbUser = new Label("User: ");
		Label lbRating = new Label("Rating: ");
		
		grid.add(lbMovie, 0, 0);
		//grid.add(lbUser, 0, 1);
		grid.add(lbRating, 0, 2);
		
		tfTitle = new TextField();
		//tfUserId = new TextField();
		String score[] = {"5","4","3","2","1","0"};
		tfRating = new ComboBox<>(FXCollections.observableArrayList(score));
		
		grid.add(tfTitle, 1, 0);
		//grid.add(tfUserId, 1, 1);
		grid.add(tfRating, 1, 2);
	}
	
	private void insertRating() {
		String movieName = tfTitle.getText();
		//int userId = 0;
//		try {
//			userId = Integer.parseInt(tfUserId.getText());
//		}catch (IllegalArgumentException e){
//			errorMsg.setText("User ID has to be an number.");
//			errorMsg.setFill(Color.RED);
//			errorMsg.setVisible(true);
//		}
		String ratingText = tfRating.getValue();
		//System.out.println("ratingText"+ratingText);
		
		//if (!movieName.isEmpty() && !tfUserId.getText().isEmpty() && !ratingText.isEmpty()) {
		if (!movieName.isEmpty() && ratingText != null) {
			double rating = Double.parseDouble(ratingText);
			
			int movieId = getMovieId(movieName);
			
			Rate rate = new Rate(movieId, account.userId, rating);
			try {
				new Database().insertRating(rate);
			}catch(SQLException e) {
				errorMsg.setText("You have already rated this movie");
				errorMsg.setFill(Color.RED);
				errorMsg.setVisible(true);
			}
			
		}else {
			//System.out.println("here");
			errorMsg.setText("Please don't leave any field empty.");
			errorMsg.setFill(Color.RED);
			errorMsg.setVisible(true);
		}
		
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
		
		btAdd.setMinSize(90,20);
		btAdd.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btAdd.setStyle("-fx-text-base-color: #1849af;");
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		btBox.getChildren().addAll(btAdd, btBack);
		btBox.setSpacing(5);
		btBox.setPadding(new Insets(5, 5, 5, 5));
		btBox.setAlignment(Pos.CENTER);
//		btBack.setOnAction(e -> prevStage.setScene(prevScene));
		btAdd.setOnAction(e -> insertRating());
		btBack.setOnAction(e -> stage.close());
	}
	
	
}
