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
	Stage stage, newStage;
	
	public DeleteAccount(Account account, Stage stage) {
		this.account = new Database().retrieveAccount(account.getUsername());
		newStage = new Stage();
		this.stage = stage;
		
		setupText();
		setupButton();
		
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color: #f8eadb;");
		
		border.setCenter(textBox);
		border.setBottom(btBox);
		
		Scene scene = new Scene(border, 500, 400);
		
		newStage.setTitle("Delete Account");
		newStage.setScene(scene);
		newStage.setAlwaysOnTop(true);
		newStage.show();

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
		btCancel.setOnAction(e -> newStage.close());
		
		btBox = new HBox();
		btBox.setSpacing(15);
		btBox.setAlignment(Pos.CENTER);
		btBox.setPadding(new Insets(15, 15, 15, 15));
		btBox.getChildren().addAll(btConfirm, btCancel);
	}
	
	private void deleteAccount() {
		String password = pfPass.getText();
		if(password.equals(account.password)) {
			new Database().deleteAccount(account);
			confirmDelete();
			newStage.close();
			stage.close();
		}else {
			errorMsg.setVisible(true);
		}
		
		return;
	}
	
	private void confirmDelete() {
		BorderPane border = new BorderPane();
		VBox vbox = new VBox();
		Button btOk = new Button("Ok");
		Text text =  new Text();
		Stage stage = new Stage();
		
		text.setText("The Account has been successfully deleted. Program end");
		text.setFill(Color.web("#1849af"));
		text.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
	
		btOk.setAlignment(Pos.CENTER);
		btOk.setOnAction(e -> stage.close());
		btOk.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btOk.setStyle("-fx-text-base-color: #1849af;");
		
		vbox.setSpacing(15);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(15, 15, 15, 15));
		vbox.getChildren().addAll(text, btOk);
		
		border.setStyle("-fx-background-color: #f8eadb;");
		border.setCenter(vbox);
		
		Scene scene = new Scene(border, 600, 200);
		stage.setScene(scene);
		stage.setTitle("Account deleted");
		stage.show();
	}
	
}
