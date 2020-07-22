package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage {
	
	Button btInsert, btUpdate, btDelete, btSearch, btCompare, btBack;
	
	HBox btBox;
	
	Text bgInfo, inst;
	
	Scene prevScene, currScene;
	Stage stage;
	boolean admin;
	
	public HomePage(Stage stage, Scene previous, boolean admin) {
		
		this.stage = stage;
		this.prevScene = previous;
		this.admin = admin;
		
		setupBt();
		setupText();
		
		BorderPane border = new BorderPane();
		
		VBox textBox = new VBox();
		textBox.setSpacing(5);
		textBox.setPadding(new Insets(5, 5, 5, 5));
		textBox.getChildren().addAll(bgInfo, inst);
		textBox.setAlignment(Pos.CENTER);
		
		border.setCenter(textBox);
		border.setBottom(btBox);
		
		currScene = new Scene(border, 600, 500);
		border.setStyle("-fx-background-color: #f8eadb;");
		stage.setScene(currScene);
		stage.setTitle("Home Page");
		stage.show();
	}
	
	private void setupText() {
		
		bgInfo = new Text();
		bgInfo.setText("Movie Universe Information\n");
		bgInfo.setFill(Color.web("#1849af"));
		bgInfo.setFont(Font.font("Abhaya",FontPosture.ITALIC, 20));
		
		inst = new Text();
		inst.setText("Program Instruction");
		inst.setFill(Color.web("#1849af"));
		inst.setFont(Font.font("Abhaya",FontPosture.ITALIC, 20));
		
	}
	
	private void setupBt() {
		btInsert = new Button("Insert");
		btInsert.setMinSize(90,20);
		btInsert.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 18));
		btInsert.setStyle("-fx-text-base-color: #1849af;");
		
		btUpdate = new Button("Update");
		btUpdate.setMinSize(90,20);
		btUpdate.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 18));
		btUpdate.setStyle("-fx-text-base-color: #1849af;");
		
		btDelete = new Button("Delete");
		btDelete.setMinSize(90, 20);
		btDelete.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 18));
		btDelete.setStyle("-fx-text-base-color: #1849af;");
		
//		btList = new Button("List");
		btSearch = new Button("Search");
		btSearch.setMinSize(90,20);
		btSearch.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 18));
		btSearch.setStyle("-fx-text-base-color: #1849af;");
		
		btCompare = new Button("Compare");
		btCompare.setMinSize(90,20);
		btCompare.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 18));
		btCompare.setStyle("-fx-text-base-color: #1849af;");
		
		btBack = new Button("Back");
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 18));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		btInsert.setOnAction(e -> new InsertMenu(stage, currScene, admin));
		
		btUpdate.setOnAction(e -> new UpdateMenu(stage, currScene));
		
		btDelete.setOnAction(e -> new DeleteMenu(stage, currScene));
		
		btSearch.setOnAction(e -> new SearchMenu(stage, currScene));
		
		btCompare.setOnAction(e -> new ComparePage(stage, currScene));
		
		btBack.setOnAction(e -> {
			stage.setTitle("Movie Universe");
			stage.setScene(prevScene);
		});
		
		btBox = new HBox();
		btBox.setSpacing(5);
		btBox.setPadding(new Insets(5, 5, 15, 5));
		btBox.setAlignment(Pos.CENTER);
		if(admin)
			btBox.getChildren().addAll(btInsert, btUpdate, btDelete, btSearch, btCompare, btBack);
		else
			btBox.getChildren().addAll(btInsert, btSearch, btCompare, btBack);
		
	}
}
