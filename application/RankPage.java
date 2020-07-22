package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;  
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RankPage {

	List<Actor> actorList;
	TableView<Actor> actorTable;
	ObservableList<Actor> record;
	
	public RankPage(int num) {
		actorList = getActorList(num);
		record = FXCollections.observableArrayList(actorList);
		System.out.println(record.get(0));
		
		setupTable();
		
	}
	
	public void showTable() {
		Stage tableStage = new Stage();
		
		BorderPane border = new BorderPane();
		border.setCenter(actorTable);
		
		border.setPadding(new Insets(10, 10, 10, 10));
		border.setStyle("-fx-background-color: #f8eadb;");
		
		Scene scene = new Scene(border, 800, 800);
		
		tableStage.setScene(scene);
		tableStage.sizeToScene();
		tableStage.show();
	}
	
	@SuppressWarnings("unchecked")
	private void setupTable() {
		actorTable = new TableView<>();
		
		System.out.println(record.get(0));
		actorTable.setEditable(false);
		TableColumn<Actor, Integer> ranking = new TableColumn<>("Ranking");
		ranking.setCellValueFactory(
				new PropertyValueFactory<Actor, Integer>("ranking"));
		
		TableColumn<Actor, String> actorName = new TableColumn<>("Actor Name");
		actorName.setCellValueFactory(
				new PropertyValueFactory<Actor, String>("actorName"));
		
		
		actorTable.setItems(record);
		actorTable.getColumns().addAll( ranking, actorName);
		
		actorTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		actorTable.setPlaceholder(new Label("Blank Table"));
	}
	
	private List<Actor> getActorList(int num){
		//Get list from database
		List<Actor> list = new ArrayList<>();
		list.add(new Actor(1, "james", "James", 1));
		return list;
	}
}
