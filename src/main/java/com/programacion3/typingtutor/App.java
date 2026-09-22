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
    
    public static Map<KeyCode, int[]> keyMapping = new HashMap(); 
    
    public static Map<String, int[]> charMapping = new HashMap(); 
    
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
        
        
        
        keyMapping.put(KeyCode.DIGIT1, new int[]{0, 0});
        keyMapping.put(KeyCode.DIGIT2, new int[]{1, 0});
        keyMapping.put(KeyCode.DIGIT3, new int[]{2, 0});
        keyMapping.put(KeyCode.DIGIT4, new int[]{3, 0});
        keyMapping.put(KeyCode.DIGIT5, new int[]{4, 0});
        keyMapping.put(KeyCode.DIGIT6, new int[]{5, 0});
        keyMapping.put(KeyCode.DIGIT7, new int[]{6, 0});
        keyMapping.put(KeyCode.DIGIT8, new int[]{7, 0});
        keyMapping.put(KeyCode.DIGIT9, new int[]{8, 0});
        keyMapping.put(KeyCode.DIGIT0, new int[]{9, 0});
        
        keyMapping.put(KeyCode.TAB, new int[]{0, 1});
        keyMapping.put(KeyCode.Q, new int[]{1, 1});
        keyMapping.put(KeyCode.W, new int[]{2, 1});
        keyMapping.put(KeyCode.E, new int[]{3, 1});
        keyMapping.put(KeyCode.R, new int[]{4, 1});
        keyMapping.put(KeyCode.T, new int[]{5, 1});
        keyMapping.put(KeyCode.Y, new int[]{6, 1});
        keyMapping.put(KeyCode.U, new int[]{7, 1});
        keyMapping.put(KeyCode.I, new int[]{8, 1});
        keyMapping.put(KeyCode.O, new int[]{9, 1});
        keyMapping.put(KeyCode.P, new int[]{10, 1});
        
        keyMapping.put(KeyCode.SHIFT, new int[]{0, 2});
        keyMapping.put(KeyCode.A, new int[]{1, 2});
        keyMapping.put(KeyCode.S, new int[]{2, 2});
        keyMapping.put(KeyCode.D, new int[]{3, 2});
        keyMapping.put(KeyCode.F, new int[]{4, 2});
        keyMapping.put(KeyCode.G, new int[]{5, 2});
        keyMapping.put(KeyCode.H, new int[]{6, 2});
        keyMapping.put(KeyCode.J, new int[]{7, 2});
        keyMapping.put(KeyCode.K, new int[]{8, 2});
        keyMapping.put(KeyCode.L, new int[]{9, 2});
        
        keyMapping.put(KeyCode.SEMICOLON, new int[]{10, 2});
        keyMapping.put(KeyCode.ALT, new int[]{0, 3});
        keyMapping.put(KeyCode.Z, new int[]{1, 3});
        keyMapping.put(KeyCode.X, new int[]{2, 3});
        keyMapping.put(KeyCode.C, new int[]{3, 3});
        keyMapping.put(KeyCode.V, new int[]{4, 3});
        keyMapping.put(KeyCode.B, new int[]{5, 3});
        keyMapping.put(KeyCode.N, new int[]{6, 3});
        keyMapping.put(KeyCode.M, new int[]{7, 3});
        keyMapping.put(KeyCode.COMMA, new int[]{8, 3});
        keyMapping.put(KeyCode.PERIOD, new int[]{9, 3});
        keyMapping.put(KeyCode.SLASH, new int[]{10, 3});
        
        charMapping.put("1", new int[]{0, 0});
        charMapping.put("2", new int[]{1, 0});
        charMapping.put("3", new int[]{2, 0});
        charMapping.put("4", new int[]{3, 0});
        charMapping.put("5", new int[]{4, 0});
        charMapping.put("6", new int[]{5, 0});
        charMapping.put("7", new int[]{6, 0});
        charMapping.put("8", new int[]{7, 0});
        charMapping.put("9", new int[]{8, 0});
        charMapping.put("0", new int[]{9, 0});

        charMapping.put("tab", new int[]{0, 1});
        charMapping.put("q", new int[]{1, 1});
        charMapping.put("w", new int[]{2, 1});
        charMapping.put("e", new int[]{3, 1});
        charMapping.put("r", new int[]{4, 1});
        charMapping.put("t", new int[]{5, 1});
        charMapping.put("y", new int[]{6, 1});
        charMapping.put("u", new int[]{7, 1});
        charMapping.put("i", new int[]{8, 1});
        charMapping.put("o", new int[]{9, 1});
        charMapping.put("p", new int[]{10, 1});

        charMapping.put("shift", new int[]{0, 2});
        charMapping.put("a", new int[]{1, 2});
        charMapping.put("s", new int[]{2, 2});
        charMapping.put("d", new int[]{3, 2});
        charMapping.put("f", new int[]{4, 2});
        charMapping.put("g", new int[]{5, 2});
        charMapping.put("h", new int[]{6, 2});
        charMapping.put("j", new int[]{7, 2});
        charMapping.put("k", new int[]{8, 2});
        charMapping.put("l", new int[]{9, 2});
        charMapping.put(";", new int[]{10, 2});

        charMapping.put("alt", new int[]{0, 3});
        charMapping.put("z", new int[]{1, 3});
        charMapping.put("x", new int[]{2, 3});
        charMapping.put("c", new int[]{3, 3});
        charMapping.put("v", new int[]{4, 3});
        charMapping.put("b", new int[]{5, 3});
        charMapping.put("n", new int[]{6, 3});
        charMapping.put("m", new int[]{7, 3});
        charMapping.put(",", new int[]{8, 3});
        charMapping.put(".", new int[]{9, 3});
        charMapping.put("/", new int[]{10, 3});
        

        charMapping.put("!", new int[]{0, 0});
        charMapping.put("@", new int[]{1, 0});
        charMapping.put("#", new int[]{2, 0});
        charMapping.put("$", new int[]{3, 0});
        charMapping.put("%", new int[]{4, 0});
        charMapping.put("^", new int[]{5, 0});
        charMapping.put("&", new int[]{6, 0});
        charMapping.put("*", new int[]{7, 0});
        charMapping.put("(", new int[]{8, 0});
        charMapping.put(")", new int[]{9, 0});

        charMapping.put("Q", new int[]{1, 1});
        charMapping.put("W", new int[]{2, 1});
        charMapping.put("E", new int[]{3, 1});
        charMapping.put("R", new int[]{4, 1});
        charMapping.put("T", new int[]{5, 1});
        charMapping.put("Y", new int[]{6, 1});
        charMapping.put("U", new int[]{7, 1});
        charMapping.put("I", new int[]{8, 1});
        charMapping.put("O", new int[]{9, 1});
        charMapping.put("P", new int[]{10, 1});

        charMapping.put("A", new int[]{1, 2});
        charMapping.put("S", new int[]{2, 2});
        charMapping.put("D", new int[]{3, 2});
        charMapping.put("F", new int[]{4, 2});
        charMapping.put("G", new int[]{5, 2});
        charMapping.put("H", new int[]{6, 2});
        charMapping.put("J", new int[]{7, 2});
        charMapping.put("K", new int[]{8, 2});
        charMapping.put("L", new int[]{9, 2});
        charMapping.put(":", new int[]{10, 2});

        charMapping.put("Z", new int[]{1, 3});
        charMapping.put("X", new int[]{2, 3});
        charMapping.put("C", new int[]{3, 3});
        charMapping.put("V", new int[]{4, 3});
        charMapping.put("B", new int[]{5, 3});
        charMapping.put("N", new int[]{6, 3});
        charMapping.put("M", new int[]{7, 3});
        charMapping.put("<", new int[]{8, 3});
        charMapping.put(">", new int[]{9, 3});
        charMapping.put("?", new int[]{10, 3});
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SHIFT) {
                updateKeyboard(shiftKeys, keyBoardLayer);
            }
        }); 
        
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SHIFT) {
                updateKeyboard(keys, keyBoardLayer);
            }
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
            
            letters.add(new Label(txt.substring(i, i + 1)), column, row);
            
            column++;
        }
    }    
    
    public static void formatLayers(GridPane gridPane) {
        for (int i = 0; i < 20; i++) {
            ColumnConstraints column = new ColumnConstraints(30);
            column.setHalignment(HPos.CENTER);
            gridPane.getColumnConstraints().add(column);
        }
        
        for (int i = 0; i < (textToType.length() / 20); i++) {
            RowConstraints row = new RowConstraints(30);
            row.setValignment(VPos.CENTER);
            gridPane.getRowConstraints().add(row);
        }
    }
    
    public static void updateKeyboard(List<String> keyboard, GridPane keyBoardLayer) {
    keyBoardLayer.getChildren().clear();

    int row = 0;
    int column = 0;

    for (String key : keyboard) {
        if (key.equals("\n")) {
            row++;
            column = 0;
            continue;
        }

        Label label = new Label(key);

        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);

        keyBoardLayer.add(label, column, row);

        column++;
    }
}
    
   public static void fillKeyboard(List<String> keyBoard, GridPane keyBoardLayer) {
    for (int i = 0; i < 12; i++) {
        ColumnConstraints column = new ColumnConstraints(30, 30, 999);
        keyBoardLayer.getColumnConstraints().add(column);
    }

    for (int i = 0; i < 4; i++) {
        RowConstraints row = new RowConstraints(30, 30, 30);
        keyBoardLayer.getRowConstraints().add(row);
    }

    updateKeyboard(keyBoard, keyBoardLayer);
}

    public static void main(String[] args) {
        launch();
    }
}