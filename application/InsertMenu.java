package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InsertMenu {
	Button btRate, btBack;
	VBox btBox;
	
	Stage stage;
	Scene prevScene, scene;
	boolean admin;
	
	public InsertMenu(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		setupButton();
		setupVbox();
		
		BorderPane border = new BorderPane();
		border.setCenter(btBox);
		
		Scene scene = new Scene(border, 500, 400);
		stage.setTitle("Insert Record");
		stage.setScene(scene);
		stage.show();
	}
	
	private void setupButton() {
		btRate = new Button("Insert Movie Rating");
		btBack = new Button("Back");
		
		btRate.setOnAction(e -> new InsertPage(stage, scene).start());
		
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void setupVbox() {
		btBox =  new VBox();
		btBox.setSpacing(15);
		btBox.setPadding(new Insets(5, 5, 5, 5));
		btBox.getChildren().addAll(btRate, btBack);
		btBox.setAlignment(Pos.CENTER);
	}
}
