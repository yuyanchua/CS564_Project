package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ComparePage {
	
	List<Movie> movieList;
	TableView<Movie> movieTable;
	ObservableList<Movie> records;
	
	int numRecord;
	
	public ComparePage(List<Movie> movieList) {
		this.movieList = movieList;
		this.records = FXCollections.observableArrayList(movieList);
		setupTable();
		
	}
		
	
	public void showTable() {
		Stage tableStage = new Stage();
		
		BorderPane border = new BorderPane();
		border.setCenter(movieTable);
		
		border.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(border, 500, 400);
		tableStage.setScene(scene);
		tableStage.sizeToScene();
		tableStage.show();
	}
	
	@SuppressWarnings("unchecked")
	private void setupTable() {
		movieTable = new TableView<>();
		movieTable.setEditable(false);
		
		TableColumn<Movie, Integer> movieId = new TableColumn<>("MovieId");
		movieId.setCellValueFactory(new PropertyValueFactory<>("movieId"));
		movieId.setMaxWidth(100);
		movieId.setMinWidth(90);
		TableColumn<Movie, String> title = new TableColumn<>("Title");
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		TableColumn<Movie, Integer> year = new TableColumn<>("Year");
		year.setCellValueFactory(new PropertyValueFactory<>("year"));
		
		TableColumn<Movie, String> country = new TableColumn<>("Country");
		country.setCellValueFactory(new PropertyValueFactory<>("country"));
		
		TableColumn<Movie, Double> rtCritRate = new TableColumn<>("RT Critics Rating");
		rtCritRate.setCellValueFactory(new PropertyValueFactory<>("RTCriticsRating"));
		
		TableColumn<Movie, Double> rtAudRate = new TableColumn<>("RT Audience Rating");
		rtAudRate.setCellValueFactory(new PropertyValueFactory<>("RTAudienceRating"));
		
		TableColumn<Movie, Integer> rtAudRateNum = new TableColumn<>("RT Audience Rating Number");
		rtAudRateNum.setCellValueFactory(new PropertyValueFactory<>("RTAudRateNum"));
		
		movieTable.setItems(records);
		movieTable.getColumns().addAll(movieId, title, year, country, rtCritRate, rtAudRate, rtAudRateNum);
		
		movieTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	
	}
}
