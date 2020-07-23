package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountMenu {
	Button btDetails, btUpdate, btBack;
	
	VBox btBox;
	
	Stage stage;
	Scene prevScene;
	public AccountMenu(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		setupButton();
		setupBox();
		
		BorderPane border = new BorderPane();
		border.setCenter(btBox);
		
		Scene scene = new Scene(border, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Account");
		stage.show();
	}
	
	private void setupButton() {
		btDetails = new Button("Account Details");
		btUpdate = new Button("Update Password");
		btBack = new Button("Back");
		
		btUpdate.setOnAction(e -> new UpdatePassword());
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void setupBox() {
		btBox = new VBox();
		btBox.setSpacing(15);
		btBox.setPadding(new Insets(15, 15, 15, 15));
		btBox.setAlignment(Pos.CENTER);
		btBox.getChildren().addAll(btDetails, btUpdate, btBack);
	}
	
	
	
}
