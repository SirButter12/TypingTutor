package com.programacion3.typingtutor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
        
        top.getChildren().add(title);
        
        VBox center = new VBox();
        StackPane sampleTextSection = new StackPane();
        GridPane lettersLayer = new GridPane();
        GridPane markerLayer = new GridPane();
        
        String textToType = "asdadasdsgsadgfhsdadfadgshdgbhf";
        
        for (int i = 0; i < 20; i++) {
            ColumnConstraints column = new ColumnConstraints(30, 30, 30);
            lettersLayer.getColumnConstraints().add(column);
        }
        
        for (int i = 0; i < (textToType.length() / 20); i++) {
            RowConstraints row = new RowConstraints(30, 30, 30);
            lettersLayer.getRowConstraints().add(row);
        }
        
        
        distributeString(textToType, lettersLayer);
        
        Rectangle marker = new Rectangle(30, 30);
        markerLayer.add(marker, 0, 0);
       
        center.getChildren().add(sampleTextSection);
        StackPane virtualKeyBoardSection = new StackPane();
        GridPane instructionLayer = new GridPane();
        GridPane pressedKeyLayer = new GridPane();
        GridPane keyBoardLayer = new GridPane();
        
        
        sampleTextSection.getChildren().add(markerLayer);
        sampleTextSection.getChildren().add(lettersLayer);
        
        virtualKeyBoardSection.getChildren().add(instructionLayer);
        virtualKeyBoardSection.getChildren().add(pressedKeyLayer);
        virtualKeyBoardSection.getChildren().add(keyBoardLayer);
        
        root.setTop(top);
        root.setCenter(center);
        
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void distributeString(String txt, GridPane letters) {
        int len = txt.length();
        int row = 0;
        int column = 0;
        
        for (int i = 0; i < len; i++) {
            if (column == 20) {
                row++;
                column = 0;
            }            
            
            letters.add(new Label(txt.substring(i, i + 1)), column, row);
            
            column++;
        }
    }    
}