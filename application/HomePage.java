package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage {
	
	Button btInsert, btUpdate, btList, btSearch, btCompare, btBack;
	
	HBox btBox;
	
	Text bgInfo, inst;
	
	Scene prevScene, currScene;
	Stage stage;
	
	public HomePage(Stage stage, Scene previous) {
		
		this.stage = stage;
		prevScene = previous;
		
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
		
		currScene = new Scene(border, 500, 400);
		
		stage.setScene(currScene);
		stage.setTitle("Home Page");
		stage.show();
	}
	
	private void setupText() {
		
		bgInfo = new Text();
		bgInfo.setText("Movie Universe Information\n");
		
		inst = new Text();
		inst.setText("Program Instruction");
		
	}
	
	private void setupBt() {
		btInsert = new Button("Insert");
		btUpdate = new Button("Update");
//		btList = new Button("List");
		btSearch = new Button("Search");
		btCompare = new Button("Compare");
		btBack = new Button("Back");
		
		btInsert.setOnAction(e -> new InsertPage(stage, currScene));
		
//		btList.setOnAction(e -> );
		
		btSearch.setOnAction(e -> new SearchPage(stage, currScene));
		
		btCompare.setOnAction(e -> new ComparePage(stage, currScene));
		
		btBack.setOnAction(e -> {
			stage.setTitle("Movie Universe");
			stage.setScene(prevScene);
		});
		
		btBox = new HBox();
		btBox.setSpacing(5);
		btBox.setPadding(new Insets(5, 5, 15, 5));
		btBox.setAlignment(Pos.CENTER);
		
		btBox.getChildren().addAll(btInsert, btUpdate, btSearch, btCompare, btBack);
		
		
	}
}
