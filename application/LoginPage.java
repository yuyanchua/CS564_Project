package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginPage {
	
	VBox vbox;
	HBox btBox;
	BorderPane border;
	GridPane grid;
	
	Label lbUser, lbPass, lbErr;
	TextField tfUser;
	PasswordField pfPass;
	
	Button btLogin, btRegis, btBack;
	
	Stage stage;
	Scene prevScene, scene;
	
	public LoginPage(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		setLabel();
		setTextField();
		setGrid();
		setButton();
		setVBox();
		
		border = new BorderPane();
		border.setCenter(vbox);
		
		scene = new Scene(border, 500, 600);
		scene.setOnKeyPressed(getKey);
		stage.setScene(scene);
		stage.setTitle("Login Screen");
		stage.show();
	}
	
	private void setLabel() {
		lbUser = new Label("Username: ");
		lbPass = new Label("Password: ");
		lbErr = new Label("Username or Password is incorrect!");
		lbErr.setTextFill(Color.RED);
		lbErr.setVisible(false);
	}
	
	private void setTextField() {
		tfUser = new TextField();
		pfPass = new PasswordField();
	}
	
	private void setGrid() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.add(lbUser, 0, 0);
		grid.add(tfUser, 1, 0);
		grid.add(lbPass, 0, 1);
		grid.add(pfPass, 1, 1);
	}
	
	private void setVBox() {
		btBox = new HBox();
		btBox.setSpacing(5);
		btBox.getChildren().addAll(btRegis, btLogin, btBack);
		btBox.setAlignment(Pos.CENTER);
		
		vbox = new VBox();
		vbox.setSpacing(5);
		vbox.getChildren().addAll(grid, lbErr, btBox);
		vbox.setAlignment(Pos.CENTER);
	}
	
	private void setButton() {
		btLogin = new Button("Login");
		btRegis = new Button("Create New Account");
		btBack = new Button("Back");
		
		btLogin.setOnAction(e -> login());
		btRegis.setOnAction(e -> new CreateAccPage(stage, scene));
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void login() {
		String user = tfUser.getText();
		String pass = pfPass.getText();
		tfUser.clear();
		pfPass.clear();
		login(user, pass);
	}
	
	private void login(String user, String pass) {
		//Validation
		boolean authorize = isAuthorize(user, pass);

		if(authorize) {
			if(isAdmin(user))
				new HomePage(stage, scene, true);
			else
				new HomePage(stage, scene, false);
		}else
			lbErr.setVisible(true);
	}
	
	private boolean isAuthorize(String user, String pass) {
		if(user.equals("user") && pass.equals("pass"))
			return true;
		if(user.equals("admin") && pass.equals("pass"))
			return true;
		return false;
	}
	
	private boolean isAdmin(String user) {
		if(user.equals("admin"))
			return true;
		return false;
	}
	
	EventHandler<KeyEvent> getKey = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent key) {
			String keyPressed = key.getCode().toString();
			if(keyPressed.equals("ENTER"))
				login();
		}
	};
	
}
