 package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
	
			Scene scene = new Scene(root, 500, 400);
			
			Text text = new Text();
			text.setText("Welcome to Movie Universe");
			text.setFill(Color.web("#1849af"));
			text.setFont(Font.font("Abhaya",FontPosture.ITALIC, 15));
			
			
			Button startBt = new Button();
			startBt.setText("Start");
			startBt.setMinSize(200,45);
			startBt.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 20));
			startBt.setStyle("-fx-text-base-color: #1849af;");
			startBt.setOnAction(e -> new LoginPage(primaryStage, scene));
			
			VBox vbox = new VBox();
			vbox.setSpacing(5);
			vbox.getChildren().addAll(text, startBt);
			vbox.setAlignment(Pos.CENTER);
			
			Image logo = new Image("logo.png");
			ImageView logoView = new ImageView(logo);
			logoView.setFitHeight(300);
			logoView.setFitWidth(300);
			
			root.setTop(logoView);
			BorderPane.setAlignment(logoView, Pos.CENTER);
			root.setPadding(new Insets(10, 10, 10, 10));
			root.setCenter(vbox);
			
			
			root.setStyle("-fx-background-color: #f8eadb;");
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(500);
			primaryStage.setTitle("Movie Universe");
			primaryStage.show();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
