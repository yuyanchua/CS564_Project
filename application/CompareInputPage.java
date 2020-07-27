package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompareInputPage {
	
	VBox vbox;
	Stage stage;
	Button btConfirm, btCancel;
	ComboBox<String> numBox;
	CompareMenu menu;
	int resultNum = 2;
	
	public CompareInputPage(CompareMenu menu) {
		vbox = new VBox();
		stage = new Stage();
		this.menu = menu;
		
		setupBox();
		setupUserInput();
		
		BorderPane border = new BorderPane();
		border.setCenter(vbox);
		border.setStyle("-fx-background-color: #f8eadb;");
		
		Scene scene = new Scene(border, 500, 400);
		stage.setTitle("Choose Number");
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public int getInputNum() {
		
		return this.resultNum;
	}
	
	private void setupBox() {
		btConfirm = new Button("Confirm");
		btConfirm.setMinSize(90, 20);
		btConfirm.setOnAction(e -> retrieveValue());
		
		btCancel = new Button("Cancel");
		btCancel.setMinSize(90, 20);
		btCancel.setOnAction(e -> stage.close());
		
		vbox.setSpacing(15);
		vbox.setPadding(new Insets(15, 15, 15, 15));
		vbox.setAlignment(Pos.CENTER);
		
	}
	
	private void setupUserInput() {
		
		Label lbPrompt = new Label();
		lbPrompt.setText("Choose the number of movie to compare: ");
		
		String [] input = {"2", "3", "4", "5"};
		
		numBox = new ComboBox<>();
		ObservableList <String> items = FXCollections.observableArrayList(input);
	       
		numBox.getItems().addAll(items);
		numBox.setValue(items.get(0));
		
		vbox.getChildren().addAll(lbPrompt, numBox, btConfirm);
		
	}
	
	private void retrieveValue() {
		this.resultNum = Integer.parseInt(numBox.getValue());
		new SearchCompareMovie(resultNum).start();
		
		stage.close();
		
		
	}
}
