package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RankPage {
	
	Text title;
	Button btOk;
	VBox result;
	Stage stage;
	
	public RankPage(String movie) {
		title = new Text();
		title.setText("Movie Ranking");
		title.setFill(Color.web("#1849af"));
		title.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 25));
		
		String name = "Movie Name: " + movie;
		String topActor = new Database().retrieveRanking(movie);
		
		Text nameText = new Text(name);
		nameText.setFill(Color.web("#1849af"));
		nameText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		
		Text actorText = new Text();
		actorText.setFill(Color.web("#1849af"));
		actorText.setFont(Font.font("Abhaya",FontWeight.SEMI_BOLD, 20));
		
		actorText.setText("Top Actor in the movie: " + topActor);
		
		btOk = new Button("Ok");
		btOk.setAlignment(Pos.CENTER);
		btOk.setOnAction(e -> stage.close());
		
		btOk.setMinSize(90,20);
		btOk.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btOk.setStyle("-fx-text-base-color: #1849af;");
		
		result = new VBox();
		result.setSpacing(15);
		result.setPadding(new Insets(15, 15, 15, 15));
		result.setAlignment(Pos.CENTER);
		result.getChildren().addAll(title, nameText, actorText, btOk);
		
//		setupButton();
		
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color: #f8eadb;");
		
//		border.setTop(title);
		border.setCenter(result);
//		border.setBottom(btOk);
		
		Scene scene = new Scene(border, 500, 500);
		stage = new Stage();
		stage.setTitle("Ranking");
		stage.setScene(scene);
		stage.show();
	}
	
//	private void setupButton() {
//		btOk = new Button("Ok");
//		btOk.setAlignment(Pos.CENTER);
//		btOk.setOnAction(e -> stage.close());
//	}
//	
	
	
//	List<Actor> actorList;
//	TableView<Actor> actorTable;
//	ObservableList<Actor> record;
//	
//	String movieName;
//	
//	public RankPage(String movieName) {
//		this.movieName = movieName;
//		actorList = getActorList(5);
//		record = FXCollections.observableArrayList(actorList);
//		System.out.println(record.get(0));
//		
//		setupTable();
//		
//	}
//	
//	public void showTable() {
//		Stage tableStage = new Stage();
//		
//		BorderPane border = new BorderPane();
//		border.setCenter(actorTable);
//		
//		border.setPadding(new Insets(10, 10, 10, 10));
//		border.setStyle("-fx-background-color: #f8eadb;");
//		
//		Scene scene = new Scene(border, 500, 400);
//		
//		tableStage.setScene(scene);
//		tableStage.sizeToScene();
//		tableStage.show();
//	}
//
//	@SuppressWarnings("unchecked")
//	private void setupTable() {
//		actorTable = new TableView<>();
//		
////		System.out.println(record.get(0));
//		actorTable.setEditable(false);
//		TableColumn<Actor, Integer> ranking = new TableColumn<>("Ranking");
//		ranking.setCellValueFactory(
//				new PropertyValueFactory<Actor, Integer>("ranking"));
//		
//		TableColumn<Actor, String> actorName = new TableColumn<>("Actor Name");
//		actorName.setCellValueFactory(
//				new PropertyValueFactory<Actor, String>("actorName"));
//		
//		
//		actorTable.setItems(record);
//		actorTable.getColumns().addAll( ranking, actorName);
//		
//		actorTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//		actorTable.setPlaceholder(new Label("Blank Table"));
//	}
//	
//	private List<Actor> getActorList(int num){
//		//Get list from database
//		List<Actor> list = null;
//		try {
//			list = new Database().retrieveRanking(movieName);
//		}catch(Exception ex) {
//			list = new ArrayList<>();
//			list.add(new Actor(1, "james", "James", 1));
//		}
//		return list;
//	}
}
