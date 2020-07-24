//package application;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class UpdateMenu {
//	Button btMovie, btActor, btDir, btBack;
//	VBox btBox;
//	
//	Stage stage;
//	Scene prevScene, scene;
//	
//	public UpdateMenu(Stage stage, Scene prevScene) {
//		this.stage = stage;
//		this.prevScene = prevScene;
//		
//		setupButton();
//		setupVbox();
//		
//		BorderPane border = new BorderPane();
//		border.setCenter(btBox);
//		
//		Scene scene = new Scene(border, 500, 400);
//		border.setStyle("-fx-background-color: #f8eadb;");
//		stage.setTitle("Update Record");
//		stage.setScene(scene);
//		stage.show();
//	}
//	
//	private void setupButton() {
//		btMovie = new Button("Update Movie");
//		btActor = new Button("Update Actor");
//		btDir = new Button("Update Director");
//		btBack = new Button("Back");
//		
//		
//		btMovie.setOnAction(e -> new SearchPage(stage, scene, 
//			"Search Filmography", "Movie Name").start());
//		btActor.setOnAction(e -> new SearchPage(stage, scene,
//			"Search Actor", "Actor's Name").start());
//		btDir.setOnAction(e -> new SearchPage(stage, scene, 
//			"Search Director", "Director's Name").start());
//		btBack.setOnAction(e -> stage.setScene(prevScene));
//	}
//	
//	private void setupVbox() {
//		btBox =  new VBox();
//		btBox.setSpacing(15);
//		btBox.setPadding(new Insets(5, 5, 5, 5));
//		btBox.getChildren().addAll(btMovie, btActor, btDir, btBack);
//		btBox.setAlignment(Pos.CENTER);
//	}
//}
