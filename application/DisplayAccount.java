package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayAccount {
	
	Account account;
	
	HBox textBox;
	
	public DisplayAccount(Account account) {
		this.account = account;
		
		Stage stage = new Stage();
		setupText();
		
		Button btBack = new Button("Back");
		btBack.setOnAction(e -> stage.close());
		
		BorderPane border = new BorderPane();
		border.setCenter(textBox);
		border.setBottom(btBack);
		
		Scene scene = new Scene(border, 500, 400);
		stage.setScene(scene);
		stage.setTitle("Account Details");
		stage.show();
		
	}
	
	private void setupText() {
		Text userText = new Text();
		userText.setText("Username: " + account.username);
		
		Text idText = new Text();
		idText.setText("User Id: " + account.userId);
		
		Text passText = new Text();
		passText.setText("Password: " + account.password);
		
		textBox = new HBox();
		textBox.setAlignment(Pos.CENTER);
		textBox.setSpacing(15);
		textBox.setPadding(new Insets(15, 15, 15, 15));
		textBox.getChildren().addAll(userText, idText, passText);
		
	}
}
