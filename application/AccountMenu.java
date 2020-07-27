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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccountMenu {
	Button btDetails, btUpdate, btDelete, btBack;
	
	Account account;
	
	VBox btBox;
	
	Stage stage;
	Scene prevScene;
	
	public AccountMenu(Stage stage, Scene prevScene, Account account) {
		this.stage = stage;
		this.prevScene = prevScene;
		this.account = account;
		
		setupButton();
		setupBox();
		
		BorderPane border = new BorderPane();
		border.setCenter(btBox);
		
		Scene scene = new Scene(border, 500, 400);
		Text top = new Text();
		top.setText("            Manage Account");
		border.setTop(top);
		top.setFill(Color.web("#1849af"));
		top.setFont(Font.font("Abhaya",FontPosture.ITALIC, 41));
		border.setPadding(new Insets(43, 0, 0, 4));
		border.setStyle("-fx-background-image: url('title.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 150 150;"
				+ "-fx-background-repeat: no-repeat;");
		stage.setScene(scene);
		stage.setTitle("Account");
		stage.show();
	}
	
	private void setupButton() {
		btDetails = new Button("Account Details");
		btUpdate = new Button("Update Password");
		btDelete = new Button("Delete Account");
		btBack = new Button("Back");

		btDetails.setMinSize(90,20);
		btDetails.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btDetails.setStyle("-fx-text-base-color: #1849af;");
		btUpdate.setMinSize(90,20);
		btUpdate.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btUpdate.setStyle("-fx-text-base-color: #1849af;");
		btDelete.setMinSize(90,20);
		btDelete.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btDelete.setStyle("-fx-text-base-color: #1849af;");
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		btDetails.setOnAction(e -> new DisplayAccount(account));
		btDelete.setOnAction(e -> new DeleteAccount(account, this.stage));
		btUpdate.setOnAction(e -> new UpdatePassword(account));
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void setupBox() {
		btBox = new VBox();
		btBox.setSpacing(15);
		btBox.setPadding(new Insets(15, 15, 15, 15));
		btBox.setAlignment(Pos.CENTER);
		btBox.getChildren().addAll(btDetails, btUpdate, btDelete, btBack);
	}
	
	
	
}
