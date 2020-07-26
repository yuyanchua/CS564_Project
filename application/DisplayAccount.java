package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayAccount {
	
	Account account;
	
	VBox textBox;
	
	Button btBack;
	
	public DisplayAccount(Account account) {
//		this.account = account;
		this.account = new Database().retrieveAccount(account.username);
		
		Stage stage = new Stage();
		
		
		btBack = new Button("Back");
		btBack.setOnAction(e -> stage.close());
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color: #f8eadb;");
		
		setupText();
		border.setCenter(textBox);
		
		
		Scene scene = new Scene(border, 500, 400);
		stage.setScene(scene);
		stage.setTitle("Account Details");
		stage.show();
		
	}
	
	private void setupText() {
		Text userText = new Text();
		userText.setText("Username: " + account.username);
		userText.setFill(Color.web("#1849af"));
		userText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		
		Text idText = new Text();
		idText.setText("User Id: " + account.userId);
		idText.setFill(Color.web("#1849af"));
		idText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		Text passText = new Text();
		passText.setText("Password: " + account.password);
		passText.setFill(Color.web("#1849af"));
		passText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		textBox = new VBox();
		textBox.setAlignment(Pos.CENTER);
		textBox.setSpacing(15);
		textBox.setPadding(new Insets(15, 15, 15, 15));
		textBox.getChildren().addAll(userText, idText, passText, btBack);
		
	}
	
	
}
