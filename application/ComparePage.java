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

public class ComparePage {
	Stage stage;
	Scene prevScene;
	
	Button btRanking, btCompare, btBack;
	
	TextInputDialog dialog;
	EventHandler<ActionEvent> event;
	VBox btBox;
	
	public ComparePage(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		setupDialog();
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
		
		btRanking.setOnAction(event);
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void setupDialog() {
		dialog = new TextInputDialog();
		dialog.setHeaderText("Show Actor Ranking of Top?");
		dialog.setContentText("Enter a number");
		event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				dialog.showAndWait();
				showRanking();
			}
		};
	}
	
	private void showRanking() {
		int num = 0;
		try {
			num = Integer.parseInt(dialog.getEditor().getText());
		}catch(Exception ex) {
			System.out.println("Please enter a number");
		}
		
		new RankPage(num).showTable();
	}
}
