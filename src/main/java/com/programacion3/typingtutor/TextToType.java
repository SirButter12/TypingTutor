/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.programacion3.typingtutor;

import javafx.geometry.HPos;
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
public class TextToType extends StackPane{
    public static String text = "achu";
    public static int letterToType = 0;
    
    private GridPane lettersLayer = new GridPane();
    private GridPane markerLayer = new GridPane();
    private Rectangle marker = new Rectangle(30, 30);
    private int[] markerPosition = new int[]{0 , 0};
     
    public TextToType() {
        formatLayer(lettersLayer);
        formatLayer(markerLayer);
        marker.getStyleClass().add("instruction-key");
        
        if (!text.isEmpty()) {
            updateMarkerPosition();
        }
        
        distributeText();
        
        super.getChildren().add(markerLayer);
        super.getChildren().add(lettersLayer);
    }
    
    private void updateMarkerPosition() {
        markerLayer.getChildren().clear();
        
        markerPosition[0] = letterToType % 20;
        markerPosition[1] = letterToType / 20;

        markerLayer.add(marker, markerPosition[0], markerPosition[1]);
    }

    private void formatLayer(GridPane gridPane) {
        for (int i = 0; i < 20; i++) {
            ColumnConstraints column = new ColumnConstraints(30, 30, 30);
            column.setHalignment(HPos.CENTER);
            gridPane.getColumnConstraints().add(column);
        }
        
        for (int i = 0; i < (text.length() / 20 + 1); i++) {
            RowConstraints row = new RowConstraints(30, 30, 30);
            row.setValignment(VPos.CENTER);
            gridPane.getRowConstraints().add(row);
        }
    }
    
    public void distributeText() {
        lettersLayer.getChildren().clear();
        
        int len = text.length();
        int row = 0;
        int column = 0;
        
        for (int i = 0; i < len; i++) {
            if (column == 20) {
                row++;
                column = 0;
            }            
            
            Label label = new Label(text.substring(i, i + 1));
            
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            
            lettersLayer.add(label, column, row);
            
            column++;
        }
    }    
    
    
    public static String getText() {
        return text;
    }
    
    public static String getInstruction() {
        if (letterToType < text.length()) {
            return text.substring(letterToType , letterToType + 1);
        } else if (letterToType < 0) {
            letterToType = 0;
            return getInstruction();
        }
        
        letterToType = text.length() - 1;
        return getInstruction();
    }
    
    public void setText(String text) {
        this.text = text;
        distributeText();
    }

    
    public void keyTyped(String charTyped) {
        if (charTyped.isEmpty()) {
            return;
        }

        if (letterToType >= text.length()) {
            return;
        }
        
        
        if (text.charAt(letterToType) == charTyped.charAt(0)) {

            letterToType++;
            
            updateMarkerPosition();
        }
    }    
    
    public void keyPressed(KeyCode key) {
        if (key == KeyCode.BACK_SPACE && letterToType > 0) {
            
            letterToType--;
            updateMarkerPosition();
        }
    }
}
