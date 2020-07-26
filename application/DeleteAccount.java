package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeleteAccount {
	
	Button btConfirm, btCancel;
	
	PasswordField pfPass;
	
	Text errorMsg;
	
	HBox btBox;
	VBox textBox;
	
	Account account;
	Stage stage;
	
	public DeleteAccount(Account account) {
		this.account = account;
		stage = new Stage();
		
		setupText();
		setupButton();
		
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color: #f8eadb;");
		
		border.setCenter(textBox);
		border.setBottom(btBox);
		
		Scene scene = new Scene(border, 500, 400);
		
		stage.setTitle("Delete Account");
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.show();

	}
	
	private void setupText() {
		errorMsg = new Text();
		errorMsg.setText("Incorrect Password");
		errorMsg.setFill(Color.RED);
		errorMsg.setVisible(false);
		errorMsg.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		
		Text ques = new Text();
		ques.setText("Do you want to delete your account?");
		ques.setFill(Color.web("#1849af"));
		ques.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		
		Text prompt = new Text();
		prompt.setText("Enter password to confirm");
		prompt.setFill(Color.web("#1849af"));
		prompt.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		pfPass = new PasswordField();
		pfPass.setPromptText("Password");
		pfPass.setMinSize(200, 20);
		
		textBox = new VBox();
		textBox.setAlignment(Pos.CENTER);
		textBox.setPadding(new Insets(15, 15, 15, 15));
		textBox.setSpacing(15);
		textBox.getChildren().addAll(ques, prompt, pfPass, errorMsg);
	}

	private void setupButton() {
		btConfirm = new Button("Confirm");
		btCancel = new Button("Cancel");
		
		btConfirm.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btConfirm.setStyle("-fx-text-base-color: #1849af;");
		btCancel.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btCancel.setStyle("-fx-text-base-color: #1849af;");
		
		btConfirm.setOnAction(e -> deleteAccount());
		btCancel.setOnAction(e -> stage.close());
		
		btBox = new HBox();
		btBox.setSpacing(15);
		btBox.setAlignment(Pos.CENTER);
		btBox.setPadding(new Insets(15, 15, 15, 15));
		btBox.getChildren().addAll(btConfirm, btCancel);
	}
	
	private void deleteAccount() {
		String password = pfPass.getText();
		if(password.equals(account.password)) {
			//System.out.println("here can?");
			new Database().deleteAccount(account);
			stage.close();
			
		}else {
			errorMsg.setVisible(true);
		}
		
		return;
	}
	
}
