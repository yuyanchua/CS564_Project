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
		Label lbMovie = new Label("Movie: ");
		Label lbRating = new Label("Rating: ");
		
		grid.add(lbMovie, 0, 0);
		grid.add(lbRating, 0, 2);
		
		tfTitle = new TextField();
		String score[] = {"5","4","3","2","1","0"};
		tfRating = new ComboBox<>(FXCollections.observableArrayList(score));
		
		grid.add(tfTitle, 1, 0);
		grid.add(tfRating, 1, 2);
	}
	
	private void insertRating() {
		String movieName = tfTitle.getText();
		String ratingText = tfRating.getValue();

		if (!movieName.isEmpty() && ratingText != null) {
			double rating = Double.parseDouble(ratingText);
			
			int movieId = getMovieId(movieName);
			if (movieId == -1) {
				errorMsg.setText("This movie does not exist in our database.");
				errorMsg.setFill(Color.RED);
				errorMsg.setVisible(true);
				return;
			}
			
			Rate rate = new Rate(movieId, account.userId, rating);
			try {
				if(new Database().insertRating(rate) > 0)
					stage.close();
			}catch(SQLException e) {
				errorMsg.setText("You have already rated this movie");
				errorMsg.setFill(Color.RED);
				errorMsg.setVisible(true);
			}
			
		}else {
			errorMsg.setText("Please don't leave any field empty.");
			errorMsg.setFill(Color.RED);
			errorMsg.setVisible(true);
		}
		
	}

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
		btAdd.setOnAction(e -> insertRating());
		btBack.setOnAction(e -> stage.close());
	}
	
	
}
