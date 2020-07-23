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
	
	Button btInsert, btSearch, btCompare, btAccount, btBack;
	
	HBox btBox;
	
	Text bgInfo, inst;
	
	Scene prevScene, currScene;
	Stage stage;
	
	public HomePage(Stage stage, Scene previous) {
		
		this.stage = stage;
		this.prevScene = previous;
		
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
		Text top = new Text();
		top.setText("            Home Page");
		border.setTop(top);
		top.setFill(Color.web("#1849af"));
		top.setFont(Font.font("Abhaya",FontPosture.ITALIC, 41));
		border.setPadding(new Insets(43, 0, 0, 4));
		border.setStyle("-fx-background-image: url('title.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 150 150;"
				+ "-fx-background-repeat: no-repeat;");
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
		btInsert = new Button("Rate");
		btInsert.setMinSize(90,20);
		btInsert.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btInsert.setStyle("-fx-text-base-color: #1849af;");
		
//		btUpdate = new Button("Update");
//		btUpdate.setMinSize(90,20);
//		btUpdate.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 18));
//		btUpdate.setStyle("-fx-text-base-color: #1849af;");
//		
//		btDelete = new Button("Delete");
//		btDelete.setMinSize(90, 20);
//		btDelete.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 18));
//		btDelete.setStyle("-fx-text-base-color: #1849af;");
		
//		btList = new Button("List");
		btSearch = new Button("Search");
		btSearch.setMinSize(90,20);
		btSearch.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearch.setStyle("-fx-text-base-color: #1849af;");
		
		btCompare = new Button("Compare");
		btCompare.setMinSize(90,20);
		btCompare.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btCompare.setStyle("-fx-text-base-color: #1849af;");
		
		btAccount = new Button("Account");
		btAccount.setMinSize(90,  20);
		btAccount.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btAccount.setStyle("-fx-text-base-color: #1849af;");
		
		
		btBack = new Button("Back");
		btBack.setMinSize(90,20);
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		btInsert.setOnAction(e -> new InsertPage(stage, currScene).start());
		
//		btUpdate.setOnAction(e -> new UpdateMenu(stage, currScene));
//		
//		btDelete.setOnAction(e -> new DeleteMenu(stage, currScene));
		
		btSearch.setOnAction(e -> new SearchMenu(stage, currScene));
		
		btCompare.setOnAction(e -> new CompareMenu(stage, currScene));
		
		btAccount.setOnAction(e -> new AccountMenu(stage, currScene));
		
		
		btBack.setOnAction(e -> {
			stage.setTitle("Movie Universe");
			stage.setScene(prevScene);
		});
		
		btBox = new HBox();
		btBox.setSpacing(5);
		btBox.setPadding(new Insets(5, 5, 15, 5));
		btBox.setAlignment(Pos.CENTER);
		btBox.getChildren().addAll(btInsert, btSearch, btCompare, btAccount, btBack);
	}
}
