package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InsertMenu {
	Button btRate, btBack;
	VBox btBox;
	
	Stage stage;
	Scene prevScene, scene;
	boolean admin;
	Account account;
	
	public InsertMenu(Stage stage, Scene prevScene, Account account) {
		this.stage = stage;
		this.prevScene = prevScene;
		this.account = account;
		
		setupButton();
		setupVbox();
		
		BorderPane border = new BorderPane();
		border.setCenter(btBox);
		
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
		stage.setTitle("Insert Record");
		stage.setScene(scene);
		stage.show();
	}
	
	private void setupButton() {
		btRate = new Button("Insert Movie Rating");
		btBack = new Button("Back");
		
		btRate.setOnAction(e -> new InsertPage(stage, scene, account).start());
		
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
