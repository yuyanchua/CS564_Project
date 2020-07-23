package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompareMenu {
	Stage stage;
	Scene prevScene;
	
	Button btRanking, btCompare, btBack;
	
	TextInputDialog rankDialog, compDialog;
	EventHandler<ActionEvent> rankEvent, compEvent;
	VBox btBox;
	
	public CompareMenu(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		setupRankingDialog();
		setupCompareDialog();
		setupButton();
		
		
		BorderPane border = new BorderPane();
		border.setCenter(btBox);
		border.setStyle("-fx-background-color: #f8eadb;");
		
		Scene scene = new Scene(border, 500, 400);
		stage.setScene(scene);
		stage.show();
	}
	
	private void setupButton() {
		btRanking = new Button("Show Top Nth Ranking Actors");
		btCompare = new Button("Compare Movie");
		btBack = new Button("Back");
		
		btBox = new VBox();
		btBox.setSpacing(5);
		btBox.getChildren().addAll(btRanking, btCompare, btBack);
		btBox.setAlignment(Pos.CENTER);
		
		btRanking.setOnAction(rankEvent);
		btCompare.setOnAction(compEvent);
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void setupRankingDialog() {
		rankDialog = new TextInputDialog();
		rankDialog.setHeaderText("Show Actor Ranking of Top?");
		rankDialog.setContentText("Enter a number");
		
		rankEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
//				while(true) {
					rankDialog.showAndWait();
//					int num = Integer.parseInt(rankDialog.getEditor().getText());
//					if(num > 0 && num < 5)
//						break;
//				
//				}
				showRanking();
					
			}
		};
	}
	
	private void showRanking() {
		int num = 0;
		try {
			num = Integer.parseInt(rankDialog.getEditor().getText());
		}catch(Exception ex) {
			System.out.println("Please enter a number");
		}
		
		if(num > 0 && num < 5)
			new RankPage(num).showTable();
		else
			return;
	}
	
	private void setupCompareDialog() {
		compDialog = new TextInputDialog();
		compDialog.setHeaderText("Show Number of Movie to Compare");
		compDialog.setContentText("Enter a number");
		compEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				compDialog.showAndWait();
				showCompare();
			}
		};
	}
	
	private void showCompare() {
		int num = 0; 
		try {
			num = Integer.parseInt(compDialog.getEditor().getText());
		}catch(Exception ex) {
			
		}
		
		if(num > 0 && num < 5)
			new ComparePage(num).showTable();
		else
			return;
	}
}
