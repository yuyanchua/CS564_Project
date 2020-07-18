package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			BorderPane root = new BorderPane();
	
			Scene scene = new Scene(root, 500, 400);
			
			Text text = new Text();
			text.setText("Welcome to Movie Universe");
			
			Button startBt = new Button();
			startBt.setText("Start");
			startBt.setOnAction(e -> new HomePage(primaryStage, scene));
			
			VBox vbox = new VBox();
			vbox.setSpacing(5);
			vbox.getChildren().addAll(text, startBt);
			vbox.setAlignment(Pos.CENTER);
			
			root.setPadding(new Insets(10, 10, 10, 10));
			root.setCenter(vbox);
			
			
	
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
