package com.programacion3.typingtutor;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
        
        BorderPane root = new BorderPane();
        
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
        
        VBox keyBoardBox = new VBox();
        keyBoardBox.setSpacing(5);
        keyBoardBox.setAlignment(Pos.CENTER);
        keyBoardBox.setPadding(new Insets(10));
        
        for(int r = 0; r < KEY_LABELS.length; r++) {
            keyBoardBox.getChildren().add(createKeyRow(KEY_LABELS[r], KEY_CODES[r]));
        }
        
        root.setTop(topBox);
        root.setCenter(keyBoardBox);
        
        root.setPadding(new Insets(20));
        BorderPane.setMargin(keyBoardBox, new Insets(20, 0, 0, 0));
        
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);
        scene.addEventFilter(KeyEvent.KEY_TYPED, this::handleKeyTyped);
        
        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
        
        root.requestFocus();
    }
    
    private void handleKeyPressed(KeyEvent e) {
        KeyCode code = e.getCode();

        if (code == KeyCode.BACK_SPACE && txtTyped.getLength() > 0) {
            String typed = txtTyped.getText();
            txtTyped.setText(typed.substring(0, typed.length() - 1));
            updateErrorIndicator();
        }

        Button key = keyMap.get(code);

        if (key != null) {
            if (!key.getStyleClass().contains("key-pressed")) {
                key.getStyleClass().add("key-pressed");
            }

            lblKeyPressed.setText(key.getText());
            lblKeyPressed.getStyleClass().removeAll("status-error", "status-ok");
            lblKeyPressed.getStyleClass().add("status-ok");

        } else {
            lblKeyPressed.setText("Not handled");
            lblKeyPressed.getStyleClass().removeAll("status-error", "status-ok");
            lblKeyPressed.getStyleClass().add("status-error");
        }
    }
    
    private void handleKeyReleased(KeyEvent e) {
        Button key = keyMap.get(e.getCode());

        if (key != null) {
            key.getStyleClass().remove("key-pressed");
        }
    }
    
    private void handleKeyTyped(KeyEvent e) {
        String ch = e.getCharacter();

        if (ch.isEmpty() || ch.charAt(0) < 32 || ch.charAt(0) == 127) {
            return;
        }

        if (txtTyped.getLength() >= txtTarget.getLength()) {
           return;
        }

        char expected = txtTarget.getText().charAt(txtTyped.getLength());
        if (ch.charAt(0) == expected) {
           correctCount++;
        } else {
            incorrectCount++;
        }

        txtTyped.appendText(ch);
         updateStats();
        updateErrorIndicator();
    }

   
    private void loadText(int index) {
        currentIndex = index;

        txtTarget.setText(SAMPLE_TEXTS[index]);
        txtTyped.clear();
        txtTyped.getStyleClass().remove("typed-error");

        lblCounter.setText((index + 1) + " of " + SAMPLE_TEXTS.length);

        btnNext.setDisable(index == SAMPLE_TEXTS.length - 1);
    }
    
    private void updateStats() {
        lblCorrect.setText("Correct: " + correctCount);
        lblIncorrect.setText("Incorrect: " + incorrectCount);
    }

    private void updateErrorIndicator() {
        boolean hasError = !txtTarget.getText().startsWith(txtTyped.getText());
 
        txtTyped.getStyleClass().remove("typed-error");
        if (hasError) {
            txtTyped.getStyleClass().add("typed-error");
        }
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