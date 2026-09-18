package com.programacion3.typingtutor;

import java.util.ArrayList;
import java.util.List;
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
    public static String textToType = "";
    
    public static List<String> keys = new ArrayList<>(List.of(
            "1","2","3","4","5", "6", "7", "8", "9", "0", "\n"
            ,"tab" , "q", "w", "e", "r", "t", "y", "u", "i" , "o", "p","tab" ,"\n"
            ,"shift", "a", "s", "d", "f", "g", "h", "j", "k" , "l", ";","shift" ,"\n"
            ,"alt" , "z", "x", "c", "v", "b", "n", "m", "," , ".", "/","alt"
    ));
    
    public static List<String> shiftKeys = new ArrayList<>(List.of(
            "!","@","#","$","%", "^", "&", "*", "(", ")", "\n"
            ,"tab" , "Q", "W", "E", "R", "T", "Y", "U", "I" , "O", "P","tab" ,"\n"
            ,"shift" , "A", "S", "D", "F", "G", "H", "J", "K" , "L", ":","shift" ,"\n"
            ,"alt" ,"Z", "X", "C", "V", "B", "N", "M", "<" , ">", "?","alt"
    ));
    
    
    
    
    
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
        formatLayers(markerLayer);
        formatLayers(lettersLayer);
        
        Rectangle marker = new Rectangle(30, 30);
        markerLayer.add(marker, 0, 0);
       
        center.getChildren().add(sampleTextSection);
        StackPane virtualKeyBoardSection = new StackPane();
        GridPane instructionLayer = new GridPane();
        GridPane pressedKeyLayer = new GridPane();
        GridPane keyBoardLayer = new GridPane();     
        
        fillKeyboard(keys, keyBoardLayer);
        
        sampleTextSection.getChildren().add(markerLayer);
        sampleTextSection.getChildren().add(lettersLayer);
        
        virtualKeyBoardSection.getChildren().add(instructionLayer);
        virtualKeyBoardSection.getChildren().add(pressedKeyLayer);
        virtualKeyBoardSection.getChildren().add(keyBoardLayer);
        center.getChildren().add(virtualKeyBoardSection);
        
        root.setTop(top);
        root.setCenter(center);
        
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 640, 480);
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
            
            letters.add(new Label(txt.substring(i, i + 1)), column, row);
            
            column++;
        }
    }    
    
    public static void formatLayers(GridPane gridPane) {
        for (int i = 0; i < 20; i++) {
            ColumnConstraints column = new ColumnConstraints(30);
            gridPane.getColumnConstraints().add(column);
        }
        
        for (int i = 0; i < (textToType.length() / 20); i++) {
            RowConstraints row = new RowConstraints(30);
            gridPane.getRowConstraints().add(row);
        }
    }
    
    public static void fillKeyboard(List<String> keyBoard, GridPane keyBoardLayer) {
        for (int i = 0; i < 12; i++) {
            ColumnConstraints column = new ColumnConstraints(30, 30, 999);
            keyBoardLayer.getColumnConstraints().add(column);
        }
        
        for (int i = 0; i < (textToType.length() / 20); i++) {
            RowConstraints row = new RowConstraints(30, 30, 30);
            keyBoardLayer.getRowConstraints().add(row);
        }
        
        int row = 0;
        int column = 0;
        for (String key: keyBoard) {
            if (key.equals("\n")){
                row++;
                column = 0;
                
                continue;
            }
            
            keyBoardLayer.add(new Label(key), column, row);
            
            column++;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}