package com.programacion3.typingtutor;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
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
    }
    

    public static void main(String[] args) {
        launch();
    }

}