package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class CreateAccPage {
	
	Button btCreate, btBack;
	TextField tfUser;
	
	PasswordField pfPass, pfRepeat;
	GridPane grid;
	
	Text errMsg;
	
	HBox btBox;
	
	Stage stage;
	Scene prevScene;
	
	public CreateAccPage(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		setupField();
		setupButton();
		
		errMsg = new Text();
		errMsg.setFill(Color.RED);
		errMsg.setVisible(false);
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(grid, errMsg);
		
		BorderPane border = new BorderPane();
		border.setCenter(vbox);
		border.setBottom(btBox);
		
		Text top = new Text();
		top.setText("            Creat Account");
		border.setTop(top);
		top.setFill(Color.web("#1849af"));
		top.setFont(Font.font("Abhaya",FontPosture.ITALIC, 41));
		border.setPadding(new Insets(43, 0, 0, 4));
		border.setStyle("-fx-background-image: url('title.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 150 150;"
				+ "-fx-background-repeat: no-repeat;");
		
		Scene scene = new Scene(border, 500, 400);
		stage.setTitle("Create new Account");
		stage.setScene(scene);
		stage.show();
	}
	
	private void setupField() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);
		
		Label lbUser = new Label("Username: ");
		Label lbPass = new Label("Password: ");
		Label lbRepeat = new Label("Repeat password: ");
		
		grid.add(lbUser, 0, 0);
		grid.add(lbPass, 0, 1);
		grid.add(lbRepeat, 0, 2);
		
		tfUser = new TextField();
		pfPass = new PasswordField();
		pfRepeat = new PasswordField();
		
		grid.add(tfUser, 1, 0);
		grid.add(pfPass, 1, 1);
		grid.add(pfRepeat, 1, 2);
		
		
	}
	
	private void setupButton() {
		btCreate = new Button("Create");
		btBack = new Button("Back");
		
		btBox = new HBox();
		btBox.setAlignment(Pos.CENTER);
		btBox.setPadding(new Insets(10, 10, 10, 10));
		btBox.setSpacing(10);
		btBox.getChildren().addAll(btCreate, btBack);
		
		btCreate.setOnAction(e -> createAccount());
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void createAccount() {
		String username = tfUser.getText();

		if(!pfPass.getText().equals(pfRepeat.getText())) {
			errMsg.setText("Password not matched.");
			errMsg.setVisible(true);
			return;
		}
		
		if(!pfPass.getText().isEmpty() ||pfRepeat.getText().isEmpty()) {
			errMsg.setText("Username or password is not enterd.");
			errMsg.setVisible(true);
			return;
		}
		
		String password = pfPass.getText();
		
		//Insert username, password to database
		
		
		stage.setScene(prevScene);
		
		
			
		
	}
	
	
}
