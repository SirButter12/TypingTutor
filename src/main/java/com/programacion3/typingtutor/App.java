package com.programacion3.typingtutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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
    public static String textToType = "Tu madre tiene una polla que ya la quisiera yo";
    
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
        
        distributeString(textToType, lettersLayer);
        
        
        Rectangle marker = new Rectangle(30, 30);
        markerLayer.add(marker, 0, 0);
       
        center.getChildren().add(sampleTextSection);
        VirtualKeyBoard virtualKeyBoard = new VirtualKeyBoard();
         
        
        
        sampleTextSection.getChildren().add(markerLayer);
        sampleTextSection.getChildren().add(lettersLayer);
        
        
        center.getChildren().add(virtualKeyBoard);
        
        root.setTop(top);
        root.setCenter(center);
        
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 640, 480);
        
        
        scene.setOnKeyPressed(event -> {
            virtualKeyBoard.keyPressed(event.getCode());
        });
        
        scene.setOnKeyReleased(event -> {
            virtualKeyBoard.keyReleased(event.getCode());
        }); 
        
        
        
        stage.setScene(scene);
        stage.show();
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
            
            Label label = new Label(txt.substring(i, i + 1));
            
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            
            letters.add(label, column, row);
            
            column++;
        }
    }    

    public static void main(String[] args) {
        launch();
    }
}