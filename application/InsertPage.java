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
import javafx.stage.Stage;

public class InsertPage {
	
	Stage prevStage, stage;
	Scene prevScene;
	
	Button btAdd, btBack;
	
	TextField tfMovieId, tfTitle, tfYear, tfCountry, tfRTCRating, tfRTAurRating;
	TextField tfAudRateNum, tfDirectorId, tfDirectorName, tfActorId, tfActorName;
	TextField tfRanking, tfUserId, tfRating;
	
	GridPane grid;
	
	HBox btBox;
	
	public InsertPage(Stage prevStage, Scene prevScene) {
		this.prevStage = prevStage;
		this.prevScene = prevScene ;
		
		setupInputField();
		setupBt();
		
		BorderPane border = new BorderPane();
		border.setCenter(grid);
		border.setBottom(btBox);
		
		Scene scene = new Scene(border, 800, 750);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void setupInputField() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.setHgap(15);
		grid.setVgap(15);
		
		Label lbMovieId = new Label("Movie Id: ");
		Label lbTitle = new Label("Title: ");
		Label lbYear = new Label("Year: ");
		Label lbCountry = new Label("Country: ");
		Label lbRTCRating = new Label("RT Critics Rating: ");
		Label lbRTAurRating = new Label("RT Audience Rating: ");
		Label lbAudRateNum = new Label("RT Audience Rating Number: ");
		Label lbDirectorId = new Label("Director Id: ");
		Label lbDirectorName = new Label("Director Name: ");
		Label lbActorId = new Label("Actor Id: ");
		Label lbActorName = new Label("Actor Name: ");
		Label lbRanking = new Label("Ranking: ");
		Label lbUserId = new Label("User Id: ");
		Label lbRating = new Label("Rating: ");
		
		
		grid.add(lbMovieId, 0, 0);
		grid.add(lbTitle, 0, 1);
		grid.add(lbYear, 0, 2);
		grid.add(lbCountry, 0, 3);
		grid.add(lbRTCRating, 0, 4);
		grid.add(lbRTAurRating, 0, 5);
		grid.add(lbAudRateNum, 0, 6);
		grid.add(lbDirectorId, 0, 7);
		grid.add(lbDirectorName, 0, 8);
		grid.add(lbActorId, 0, 9);
		grid.add(lbActorName, 0, 10);
		grid.add(lbRanking, 0, 11);
		grid.add(lbUserId, 0, 12);
		grid.add(lbRating, 0, 13);
		
		tfMovieId = new TextField();
		tfTitle = new TextField();
		tfYear = new TextField();
		tfCountry = new TextField();
		tfRTCRating = new TextField();
		tfRTAurRating = new TextField();
		tfAudRateNum = new TextField();
		tfDirectorId = new TextField();
		tfDirectorName = new TextField();
		tfActorId = new TextField();
		tfActorName = new TextField();
		tfRanking = new TextField();
		tfUserId = new TextField();
		tfRating = new TextField();
		
		grid.add(tfMovieId, 1, 0);
		grid.add(tfTitle, 1, 1);
		grid.add(tfYear, 1, 2);
		grid.add(tfCountry, 1, 3);
		grid.add(tfRTCRating, 1, 4);
		grid.add(tfRTAurRating, 1, 5);
		grid.add(tfAudRateNum, 1, 6);
		grid.add(tfDirectorId, 1, 7);
		grid.add(tfDirectorName, 1, 8);
		grid.add(tfActorId, 1, 9);
		grid.add(tfActorName, 1, 10);
		grid.add(tfRanking, 1, 11);
		grid.add(tfUserId, 1, 12);
		grid.add(tfRating, 1, 13);
	}
	
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
