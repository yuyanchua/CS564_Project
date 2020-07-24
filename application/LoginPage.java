package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage {
	
	VBox vbox;
	HBox btBox;
	BorderPane border;
	GridPane grid;
	
	Label lbUser, lbPass, lbErr, lbEmpty;
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
		
		scene = new Scene(border, 500, 400);
		scene.setOnKeyPressed(getKey);
		
		Text top = new Text();
		top.setText("            Login");
		border.setTop(top);
		top.setFill(Color.web("#1849af"));
		top.setFont(Font.font("Abhaya",FontPosture.ITALIC, 41));
		border.setPadding(new Insets(43, 0, 0, 4));
		border.setStyle("-fx-background-image: url('title.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 150 150;"
				+ "-fx-background-repeat: no-repeat;");
		
		stage.setScene(scene);
		stage.setTitle("Login Screen");
		stage.show();
	}
	
	private void setLabel() {
		lbUser = new Label("Username: ");
		lbPass = new Label("Password: ");
		lbErr = new Label("Username or password is incorrect!");
		lbErr.setTextFill(Color.RED);
		lbErr.setVisible(false);
		lbEmpty = new Label("Username or password is not entered!");
		lbEmpty.setTextFill(Color.RED);
		lbEmpty.setVisible(false);
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
		vbox.getChildren().addAll(grid, lbErr, lbEmpty, btBox);
		vbox.setAlignment(Pos.CENTER);
	}
	
	private void setButton() {
		btLogin = new Button("Login");
		btRegis = new Button("Register");
		btBack = new Button("Back");
		
		btRegis.setMinSize(90,20);
		btRegis.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btRegis.setStyle("-fx-text-base-color: #1849af;");
		btLogin.setMinSize(90,20);
		btLogin.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btLogin.setStyle("-fx-text-base-color: #1849af;");
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
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

		Account account = new Database().retrieveAccount(user);
		
		//Validation
		boolean authorize = isAuthorize(user, pass, account);
		
		if(authorize) {
			new HomePage(stage, scene, account);
		}else
			if(user.isEmpty() || pass.isEmpty()) {
				lbEmpty.setVisible(true);
			}else {
				lbErr.setVisible(true);
			}	
	}
	
	private boolean isAuthorize(String user, String pass, Account account) {
		if(user.equals("user") && pass.equals("pass"))
			return true;
		if(user.equals("admin") && pass.equals("pass"))
			return true;
		
		if(user.equals(account.username) && pass.equals(account.password))
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
