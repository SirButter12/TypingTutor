package com.programacion3.typingtutor;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 * 
 * @author Ian
 */
public class App extends Application {
    private static final String[] SAMPLE_TEXTS = {
        "Try typing this text. Do it as quickly and accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog.",
        "Five big quacking zephyrs jolt my wax bed.",
        "Sympathizing would fix Quaker objectives.",
        "A large fawn jumped quickly over white zinc boxes."
    };
    
    private static final String[][] KEY_LABELS = {
        {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "Backspace"},
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\"},
        {"A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'", "Enter"},
        {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/"},
        {"Space"}
    };

    private static final KeyCode[][] KEY_CODES = {
        {BACK_QUOTE, DIGIT1, DIGIT2, DIGIT3, DIGIT4, DIGIT5, DIGIT6, DIGIT7, DIGIT8, DIGIT9, DIGIT0, MINUS, EQUALS, BACK_SPACE},
        {Q, W, E, R, T, Y, U, I, O, P, OPEN_BRACKET, CLOSE_BRACKET, BACK_SLASH},
        {A, S, D, F, G, H, J, K, L, SEMICOLON, QUOTE, ENTER},
        {SHIFT, Z, X, C, V, B, N, M, COMMA, PERIOD, SLASH},
        {SPACE}
    };
    
    TextField txtTarget, txtTyped;
    Label lblTitle, lblCounter, lblKeyCaption, lblKeyPressed, lblCorrect, lblIncorrect;
    Button btnNext, btnReset;
    Map<KeyCode, Button> keyMap = new HashMap<>();
    int currentIndex = 0, correctCount = 0, incorrectCount = 0;
    
    @Override
    public void start(Stage stage) {
        lblTitle = new Label("TypingTutor");
        lblTitle.getStyleClass().add("title-label");
        
        lblCounter = new Label("1 of 6");
        lblCounter.getStyleClass().add("counter-label");
        
        txtTarget = new TextField();
        txtTarget.setEditable(false);
        txtTarget.setFocusTraversable(false);
        txtTarget.getStyleClass().add("text-display");
        txtTarget.setPrefWidth(700);
     
        txtTyped = new TextField();
        txtTyped.setEditable(false);
        txtTyped.setFocusTraversable(false);
        txtTyped.getStyleClass().add("text-display");
        txtTyped.setPrefWidth(700);
        
        lblKeyCaption = new Label("Key pressed:");
        lblKeyPressed = new Label("-");
        lblKeyPressed.getStyleClass().add("status-ok");
        
        lblCorrect = new Label("Correct: 0");
        lblIncorrect = new Label("Incorrect: 0");
        
        lblCorrect.getStyleClass().add("stat-label");
        lblIncorrect.getStyleClass().add("stat-label");
        
        btnNext = new Button("Next");
        btnReset = new Button("Reset");
        
        btnNext.setFocusTraversable(false);
        btnReset.setFocusTraversable(false);
        
        HBox statsBox = new HBox();
        HBox controlsBox = new HBox();
        VBox topBox = new VBox();
        
        statsBox.getChildren().addAll(lblKeyCaption, lblKeyPressed, lblCorrect, lblIncorrect);
        statsBox.setSpacing(20);
        statsBox.setAlignment(Pos.CENTER);
    
        controlsBox.getChildren().addAll(btnNext, btnReset);
        controlsBox.setSpacing(10);
        controlsBox.setAlignment(Pos.CENTER);
        
        topBox.getChildren().addAll(lblTitle, lblCounter, txtTarget, txtTyped, statsBox, controlsBox);
        
    }
    
    private HBox createKeyRow(String[] labels, KeyCode[] codes) {
        HBox row = new HBox(5);
        row.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < labels.length; i++) {
            Button key = new Button(labels[i]);
            key.getStyleClass().add("key");
            key.setFocusTraversable(false);
            keyMap.put(codes[i], key);
            
            switch(codes[i]) {
                case BACK_SPACE:
                case ENTER:
                case SHIFT:    
                    key.getStyleClass().add("key-wide");
                break;
                case SPACE:
                    key.getStyleClass().add("key-space");
                break;
                default:
                break;    
            }
            
            row.getChildren().add(key);
        }
        
        return row;
    } 

    public static void main(String[] args) {
        launch();
    }

}