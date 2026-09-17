package com.programacion3.typingtutor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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
    
        VBox center = new VBox();
        StackPane sampleTextSection = new StackPane();
        GridPane lettersLayer = new GridPane();
        GridPane markerLayer = new GridPane();
        
        Rectangle marker = new Rectangle(30, 30);
        markerLayer.add(marker, 0, 0);
       
        center.getChildren().add(sampleTextSection);
        StackPane virtualKeyBoardSection = new StackPane();
        GridPane instructionLayer = new GridPane();
        GridPane pressedKeyLayer = new GridPane();
        GridPane keyBoardLayer = new GridPane();
        
        top.getChildren().add(title);
        
        sampleTextSection.getChildren().add(markerLayer);
        sampleTextSection.getChildren().add(lettersLayer);
        
        virtualKeyBoardSection.getChildren().add(instructionLayer);
        virtualKeyBoardSection.getChildren().add(pressedKeyLayer);
        virtualKeyBoardSection.getChildren().add(keyBoardLayer);
        
        root.setTop(title);
        root.setCenter(center);
        
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}