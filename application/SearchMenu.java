package application;


import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
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


public class SearchMenu {
	
	Button btRanking, btSearchActMovie, btSearchActor, btSearchDir, btSearchDirMovie, btRate, btBack;
	Button btDisplay;
	VBox btBox;
	HBox hbox;
	TextField keyField;
	Stage stage;
	Scene prevScene, scene;
	
	public SearchMenu(Stage stage, Scene prevScene) {
		this.stage = stage;
		this.prevScene = prevScene;
		
		stage.setTitle("Search");
		setupButton();
		
		BorderPane border= new BorderPane();
		
	//	Text text = new Text();
		//text.setText("Movie Search");
		//text.setFill(Color.web("#1849af"));
		//text.setFont(Font.font("Abhaya",FontPosture.ITALIC, 40));
		//border.setTop(text);
		border.setCenter(btBox);
		Insets top = new Insets(80, 0, 0, 0);
		BorderPane.setMargin(btBox, top);
		
		
		Scene scene = new Scene(border, 500, 400);
		border.setStyle("-fx-background-image: url('top.png');"
				+ "-fx-background-color: #f8eadb;"
				+ "-fx-background-size: 500 150;"
				+ "-fx-background-repeat: no-repeat;");
		stage.setScene(scene);
		stage.show();
		
	}
	

	
	private void setupButton() {
		
		btSearchActMovie = new Button("Find Actor's Filmography");
		btSearchActMovie.setMinSize(200,30);
		btSearchActMovie.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearchActMovie.setStyle("-fx-text-base-color: #1849af;");
		
		btSearchActor = new Button("Find Movie Cast");
		btSearchActor.setMinSize(200,30);
		btSearchActor.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearchActor.setStyle("-fx-text-base-color: #1849af;");
		
		btSearchDir = new Button("Find Movie Director");
		btSearchDir.setMinSize(200,30);
		btSearchDir.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearchDir.setStyle("-fx-text-base-color: #1849af;");
		
		btSearchDirMovie = new Button("Find Director's Filmography");
		btSearchDirMovie.setMinSize(200,30);
		btSearchDirMovie.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btSearchDirMovie.setStyle("-fx-text-base-color: #1849af;");
		
		btRate = new Button("Find Movie Rating");
		btRate.setMinSize(200,30);
		btRate.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btRate.setStyle("-fx-text-base-color: #1849af;");

		btDisplay = new Button("Show all data");
		btDisplay.setMinSize(200,30);
		btDisplay.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btDisplay.setStyle("-fx-text-base-color: #1849af;");
		
		
		btBack = new Button("Back");
		btBack.setFont(Font.font("Abhaya", FontWeight.SEMI_BOLD, 15));
		btBack.setStyle("-fx-text-base-color: #1849af;");
		
		
		
		//HBox hbox = new HBox();
		//hbox.setSpacing(50);
		//hbox.getChildren().add(text);
		
		btBox = new VBox();
		btBox.setSpacing(5);
		btBox.getChildren().addAll(btSearchActMovie, btSearchDirMovie, btSearchActor, 
				btSearchDir, btRate, btDisplay, btBack);
		btBox.setAlignment(Pos.CENTER);
		
		
		// TODO

		
		btSearchActMovie.setOnAction(e -> new SearchPage(stage, scene,
				"Search Filmography", "Actor's Name").start());
		btSearchActor.setOnAction(e -> new SearchPage(stage, scene,
				"Search Movie Cast", "Movie Name").start());
		btSearchDir.setOnAction(e -> new SearchPage(stage, scene,
				"Search Movie Director", "Movie Name").start());
		btSearchDirMovie.setOnAction(e -> new SearchPage(stage, scene, 
				"Search Filmography", "Director's Name").start());
		btRate.setOnAction(e -> new SearchPage(stage, scene, 
				"Search Rating", "Movie Name").start());
		
//		btSearchActor.setOnAction(e -> searchActor());
//		btSearchDir.setOnAction(e -> searchDirMovie());
//		btSearchDirMovie.setOnAction(e -> searchDirector());
//		btRate.setOnAction(e -> searchRating());
		
		btBack.setOnAction(e -> stage.setScene(prevScene));
	}
	
	private void searchDirector() {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Search Filmography");
		dialog.setHeaderText("Enter the director's name :");

		// Set the button types.
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 150, 10, 10));

		TextField movie = new TextField();
		movie.setPromptText("Director name");

		gridPane.add(movie, 0, 0);
		
		Text print = new Text();
		print.setText("You haven't enter any input. Please try again.\n");
//		print.setVisible(false);
		print.setFill(Color.RED);
		gridPane.add(print, 0, 1);


		dialog.getDialogPane().setContent(gridPane);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return new String(movie.getText());
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().equals("111")) {
			invalid();
		}
		if (result.isPresent() && result.get().isEmpty()) {
			invalid();
		}
		if (result.isPresent() && !result.get().isEmpty()) {
			director(result.get());
		}
	
	}
	
	private void director(String name) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Director's Filomography");
		alert.setHeaderText(name + " directs following movies:");
		String result = "Movie 3, Movie 4";
		alert.setContentText(result);
		alert.showAndWait();
	}
	
	private void searchActor() {
		// Create the custom dialog.


		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Search Filmography");
		dialog.setHeaderText("Enter the actor's name :");

		ButtonType submit = new ButtonType("ok", ButtonData.OTHER);
		dialog.getDialogPane().getButtonTypes().addAll(submit, ButtonType.CANCEL);
		
		Button sub = (Button) dialog.getDialogPane().lookupButton(submit);

		GridPane gridPane = new GridPane();
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 150, 10, 10));

		TextField movie = new TextField();
		movie.setPromptText("Actor name");
		gridPane.add(movie, 0, 0);
		
		
		final Text output = new Text();
		gridPane.add(output, 0, 1);
		
		sub.addEventFilter(ActionEvent.ACTION, e ->{
			if (movie.getText() != null && !movie.getText().isEmpty()) {
				dialog.setContentText("gessssupppp");
				//output.setText(movie.getText() + " participates in:\n" + "Movie1, Movie 2");
				//output.setFill(Color.BLACK);
			}
			else {
				output.setText("You haven't enter any input. Please try again.");
				output.setFill(Color.RED);
			}
		});
		
		Button ok = new Button("OK");
		Button cancel = new Button("Cancel");
		HBox hb = new HBox();
		hb.getChildren().addAll(ok,cancel);
		
		hb.setAlignment(Pos.BOTTOM_RIGHT);
		gridPane.add(hb, 1, 2);
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});
		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (movie.getText() != null && !movie.getText().isEmpty()) {
					output.setText(movie.getText() + " participates in:\n" + "Movie1, Movie 2");
					output.setFill(Color.BLACK);
				}
				else {
					output.setText("You haven't enter any input. Please try again.");
					output.setFill(Color.RED);
				}
			}
		});
		dialog.getDialogPane().setContent(gridPane);
		
		Optional<String> result = dialog.showAndWait();
	
	}
	
	private void actor(String name) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Actor's Filomography");
		alert.setHeaderText(name + " participates in following movies:");
		String result = "Movie 1, Movie 2";
		alert.setContentText(result);
		alert.showAndWait();
	}
	
	private void searchActMovie() {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Search Movie Cast");
		dialog.setHeaderText("Enter the movie name :");

		// Set the button types.
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 150, 10, 10));

		TextField movie = new TextField();
		movie.setPromptText("Movie name");

		gridPane.add(movie, 0, 0);

		dialog.getDialogPane().setContent(gridPane);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return new String(movie.getText());
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().equals("111")) {
			invalid();
		}
		if (result.isPresent() && result.get().isEmpty()) {
			invalid();
		}
		if (result.isPresent() && !result.get().isEmpty()) {
			starringAct(result.get());
		}
	
	}
	
	private void starringAct(String movie) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie Cast");
		alert.setHeaderText("Here is the cast of "+ movie);
		String result = "Actor A, Actor B, Actor C";
		alert.setContentText(result);
		alert.showAndWait();
	}
	
	private void searchDirMovie() {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Search Movie Director");
		dialog.setHeaderText("Enter the movie name :");

		// Set the button types.
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 150, 10, 10));

		TextField movie = new TextField();
		movie.setPromptText("Movie name");

		gridPane.add(movie, 0, 0);

		dialog.getDialogPane().setContent(gridPane);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return new String(movie.getText());
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().equals("111")) {
			invalid();
		}
		if (result.isPresent() && result.get().isEmpty()) {
			invalid();
		}
		if (result.isPresent() && !result.get().isEmpty()) {
			directingDir(result.get());
		}
	
	}
	
	private void directingDir(String movie) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie Director");
		alert.setHeaderText("Here is the director of "+ movie);
		String result = "Director John, Director Jane";
		alert.setContentText(result);
		alert.showAndWait();
	}
	
	private void searchRating() {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Search Rating");
		dialog.setHeaderText("Enter the movie name :");

		// Set the button types.
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 150, 10, 10));

		TextField movie = new TextField();
		movie.setPromptText("Movie name");

		gridPane.add(movie, 0, 0);

		dialog.getDialogPane().setContent(gridPane);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return new String(movie.getText());
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().equals("111")) {
			invalid();
		}
		if (result.isPresent() && result.get().isEmpty()) {
			invalid();
		}
		if (result.isPresent() && !result.get().isEmpty()) {
			rating(result.get());
		}
	
	}
	
	private void rating(String name) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie Rating");
		alert.setHeaderText(name + "'s overall rating is:");
		String result = "3";
		alert.setContentText(result);
		alert.showAndWait();
	}
	
	private void invalid() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid input");
		alert.setHeaderText(null);
		alert.setContentText("You haven't enter any input. Please try again.");

		alert.showAndWait();
	}
	
}
