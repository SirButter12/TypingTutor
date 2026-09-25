/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.programacion3.typingtutor;

import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ian
 */
public class VirtualKeyBoard extends StackPane {
    private GridPane instructionLayer = new GridPane();
    private GridPane pressedKeyLayer = new GridPane();
    private GridPane keyBoardLayer = new GridPane();    
    Rectangle pressedMarker = new Rectangle(30, 30);
    Rectangle instructionMarker = new Rectangle(30, 30);
    
    public VirtualKeyBoard() {
        createKeyBoard();
    }
    
    private void createKeyBoard() {
        formatLayer(instructionLayer);
        formatLayer(pressedKeyLayer);
        formatLayer(keyBoardLayer);
        
        updateKeyboard(KeyBoardData.keys);
        updateInstruction();    
        
        super.getChildren().add(instructionLayer);
        super.getChildren().add(pressedKeyLayer);
        super.getChildren().add(keyBoardLayer);
    }
    
    public void updateKeyboard(List<String> keyboard) {
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
    
    private void formatLayer(GridPane gridPane) {
        gridPane.setAlignment(Pos.TOP_CENTER);
        
        for (int i = 0; i < 12; i++) {
            ColumnConstraints column = new ColumnConstraints(30, 30, 30);
            column.setHalignment(HPos.CENTER);
            gridPane.getColumnConstraints().add(column);
        }
        
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints(30, 30, 30);
            row.setValignment(VPos.CENTER);
            gridPane.getRowConstraints().add(row);
        }
    }
    
    public void updateInstruction() {
        instructionLayer.getChildren().clear();
        String instruction = TextToType.getInstruction();
        
        if (KeyBoardData.characterMapping.keySet().contains(instruction)) {
            instructionLayer.add(instructionMarker, KeyBoardData.characterMapping.get(instruction)[0], KeyBoardData.characterMapping.get(instruction)[1]);
        } else if (KeyBoardData.shiftMapping.keySet().contains(instruction)) {
            instructionLayer.add(new Rectangle(30 , 30), 0, 2);
            instructionLayer.add(instructionMarker, KeyBoardData.shiftMapping.get(instruction)[0], KeyBoardData.shiftMapping.get(instruction)[1]);
        }
    }
    
    public void keyPressed(KeyCode key) {
        pressedKeyLayer.getChildren().clear();
        
        if (key == KeyCode.SHIFT) {
            updateKeyboard(KeyBoardData.shiftKeys);
        }
            
        int[] position = KeyBoardData.keyMapping.get(key);

        if (position != null) {
            pressedKeyLayer.add(
                pressedMarker,
                position[0],
                position[1]
            );
        }
        
        
    }
    
    public void keyReleased(KeyCode key) {
        if (key == KeyCode.SHIFT) {
                updateKeyboard(KeyBoardData.keys);
            }
            
        updateInstruction();
        pressedKeyLayer.getChildren().clear();
    }
}
