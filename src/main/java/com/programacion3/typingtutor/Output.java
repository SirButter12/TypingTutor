/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.programacion3.typingtutor;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

/**
 *
 * @author ian
 */
public class Output extends VBox{
    private String typedStr = "";
    private int charsInLine = 0;
    private int skipLines = 0;
    private Label typed = new Label(typedStr);
    private Label errorMessage = new Label();
    
    public Output() {
        super.getChildren().add(typed);
        super.getChildren().add(errorMessage);
    }
    
    public void keyTyped(String charTyped) {
        if (TextToType.letterToType == TextToType.text.length()) {
            endOfTheText();
            return;
        }
        
        if (KeyBoardData.keys.contains(charTyped) || KeyBoardData.shiftKeys.contains(charTyped) || charTyped.equals(" ")) {  
            if (charsInLine > 48) {
                typedStr = typedStr + "\n";
                skipLines++;
                charsInLine = 0;
            }
            charsInLine++;
            
            typedStr = typedStr + charTyped;
            
            typed.setText(typedStr);
            
            errorMessage.setText("");
        } 
        
        else if (charTyped.equals("\b")) {
            errorMessage.setText("");
            return;    
        } else {
            errorMessage.setText(String.format("I don't like the < %s >", charTyped));
        }
    }
    
    public void keyPressed(KeyCode key) {
        if (!typedStr.isEmpty()) {
            if (key == KeyCode.BACK_SPACE) {
                typedStr = typedStr.substring(0, typedStr.length() - 1);
            }    

            typed.setText(typedStr);
        }    
    }
    
    public void endOfTheText() {
        int idk = typedStr.length() - skipLines;
        int textLen = TextToType.getText().length();
        double grade = textLen / idk;
        super.getChildren().add(new Label(String.format("Congratulations! you wrote: %d characters\n"
                + "from which %d were correct, you got a %.2f!", idk, textLen, grade * 100)));
    }
}
