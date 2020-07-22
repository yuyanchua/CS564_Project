package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InsertMenu {
	Button btMovie, btActor, btDir, btRate, btBack;
	VBox btBox;
	
	Stage stage;
	Scene prevScene, scene;
	boolean admin;
	
	public InsertMenu(Stage stage, Scene prevScene, boolean admin) {
		this.stage = stage;
		this.prevScene = prevScene;
		this.admin = admin;
		
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
		btMovie = new Button("Insert Movie");
		btActor = new Button("Insert Actor");
		btDir = new Button("Insert Director");
		btRate = new Button("Insert Movie Rating");
		btBack = new Button("Back");
		
//		if(!admin) {
//			btMovie.setVisible(false);
//			btActor.setVisible(false);
//			btDir.setVisible(false);
//		}
		
		
		btMovie.setOnAction(e -> new InsertPage(stage, scene, 0).start());
		btActor.setOnAction(e -> new InsertPage(stage, scene, 1).start());
		btDir.setOnAction(e -> new InsertPage(stage, scene, 2).start());
		btRate.setOnAction(e -> new InsertPage(stage, scene, 3).start());
		
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void setupVbox() {
		btBox =  new VBox();
		btBox.setSpacing(15);
		btBox.setPadding(new Insets(5, 5, 5, 5));
		if(admin)
			btBox.getChildren().addAll(btMovie, btActor, btDir, btRate, btBack);
		else
			btBox.getChildren().addAll(btRate, btBack);
		btBox.setAlignment(Pos.CENTER);
	}
}
