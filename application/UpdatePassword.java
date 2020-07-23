package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdatePassword {
	Button btConfirm, btCancel;
	
	PasswordField pfOld, pfNew, pfConfirm;
	
	Text errorMsg;
	
	GridPane grid;
	HBox btBox;
	
	Stage stage;

	
	public UpdatePassword() {
		
		setupGrid();
		setupField();
		setupButton();
		setupBox();
		
		errorMsg = new Text();
		errorMsg.setFill(Color.RED);
		errorMsg.setVisible(false);
		
		VBox vbox = new VBox();
		vbox.setSpacing(15);
		vbox.setPadding(new Insets(15, 15, 15, 15));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(grid, errorMsg);
		
		BorderPane border = new BorderPane();
		border.setCenter(vbox);
		border.setBottom(btBox);
		border.setPadding(new Insets(15, 15, 15, 15));
		
		Text top = new Text();
		top.setText("            Change Password");
		border.setTop(top);
		top.setFill(Color.web("#1849af"));
		top.setFont(Font.font("Abhaya",FontPosture.ITALIC, 41));
		border.setPadding(new Insets(43, 0, 0, 4));
		border.setStyle("-fx-background-image: url('title.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 150 150;"
				+ "-fx-background-repeat: no-repeat;");
		
		stage = new Stage();
		Scene scene = new Scene(border, 500, 400);
		stage.setTitle("Change Password");
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void setupGrid() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		grid.setPadding(new Insets(15, 15, 15, 15));
	}
	
	private void setupField() {
		Label lbOld = new Label("Old Password: ");
		Label lbNew = new Label("New Password: ");
		Label lbConfirm = new Label("Reconfirm Password: ");
		
		grid.add(lbOld, 0, 0);
		grid.add(lbNew, 0, 1);
		grid.add(lbConfirm, 0, 2);
		
		pfOld = new PasswordField();
		pfNew = new PasswordField();
		pfConfirm = new PasswordField();
		
		grid.add(pfOld, 1, 0);
		grid.add(pfNew, 1, 1);
		grid.add(pfConfirm, 1, 2);
	}
	
	private void setupButton() {
		btConfirm = new Button("Confirm");
		btCancel = new Button("Cancel");
		
		btConfirm.setMinSize(90,20);
		btConfirm.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btConfirm.setStyle("-fx-text-base-color: #1849af;");
		btCancel.setMinSize(90,20);
		btCancel.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btCancel.setStyle("-fx-text-base-color: #1849af;");
		
		btConfirm.setOnAction(e -> changePass());
		btCancel.setOnAction(e -> stage.close());
		
	}
	
	private void changePass() {
		String oldPass = pfOld.getText();
		String newPass = pfNew.getText();
		String confirmPass = pfConfirm.getText();
		
		if(validate(oldPass))
			if(validate(newPass, confirmPass))
				updatePass(newPass);
			else
				errorMsg.setText("Incorrect New Password");
		else
			errorMsg.setText("Incorrect Old Password");
		
		errorMsg.setVisible(true);
		if (!oldPass.isEmpty() && !newPass.isEmpty() && !confirmPass.isEmpty()) {
			
		}
		else {
			errorMsg.setText("Please don't leave any field empty.");
		}
	}
	
	private boolean validate(String oldPass) {
		return false;
	}
	
	private boolean validate(String newPass, String confirmPass) {
		return (newPass.equals(confirmPass));
	}
	
	private void updatePass(String newPass) {
		return;
	}
	
	private void setupBox() {
		btBox = new HBox();
		btBox.setSpacing(15);
		btBox.setPadding(new Insets(15, 15, 15, 15));
		btBox.setAlignment(Pos.CENTER);
		
		btBox.getChildren().addAll(btConfirm, btCancel);
	}
	
}


