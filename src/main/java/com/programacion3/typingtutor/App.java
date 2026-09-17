package com.programacion3.typingtutor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        VBox top = new VBox();
        Label title = new Label("Typing tutor");
        top.getChildren().add(title);
        root.setTop(title);
    
        VBox center = new VBox();
        StackPane sampleTextSection = new StackPane();
        GridPane lettersSection = new GridPane();
        GridPane markerSection = new GridPane();
        
        sampleTextSection.getChildren().add(markerSection);
        sampleTextSection.getChildren().add(lettersSection);
        
        
        center.getChildren().add(sampleTextSection);
        GridPane virtualKeyBoard = new GridPane();
        center.getChildren().add(virtualKeyBoard);
        
        root.setCenter(center);
        
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}