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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Typing Tutor application.
 * Shows a text to type, a virtual keyboard that reacts to the physical
 * keyboard, and counts correct and incorrect keystrokes.
 *
 * @author Ian
 */
public class App extends Application {

    /** Texts the user practices with. */
    private static final String[] SAMPLE_TEXTS = {
        "Try typing this text. Do it as quickly and accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog.",
        "Five big quacking zephyrs jolt my wax bed.",
        "Sympathizing would fix Quaker objectives.",
        "A large fawn jumped quickly over white zinc boxes."
    };

    /** Labels of the virtual keys, one array per keyboard row. */
    private static final String[][] KEY_LABELS = {
        {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "Backspace"},
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\"},
        {"A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'", "Enter"},
        {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/"},
        {"Space"}
    };

    /** Key codes matching KEY_LABELS position by position. */
    private static final KeyCode[][] KEY_CODES = {
        {BACK_QUOTE, DIGIT1, DIGIT2, DIGIT3, DIGIT4, DIGIT5, DIGIT6, DIGIT7, DIGIT8, DIGIT9, DIGIT0, MINUS, EQUALS, BACK_SPACE},
        {Q, W, E, R, T, Y, U, I, O, P, OPEN_BRACKET, CLOSE_BRACKET, BACK_SLASH},
        {A, S, D, F, G, H, J, K, L, SEMICOLON, QUOTE, ENTER},
        {SHIFT, Z, X, C, V, B, N, M, COMMA, PERIOD, SLASH},
        {SPACE}
    };

    // UI components
    BorderPane root;
    TextField txtTarget, txtTyped;
    Label lblTitle, lblCounter, lblKeyCaption, lblKeyPressed, lblCorrect, lblIncorrect;
    Button btnNext, btnReset;

    /** Links each physical key code to its virtual key button. */
    Map<KeyCode, Button> keyMap = new HashMap<>();

    // Application state
    int currentIndex = 0, correctCount = 0, incorrectCount = 0;

    /**
     * Builds the interface, registers the keyboard events and shows the window.
     *
     * @param stage the main window
     */
    @Override
    public void start(Stage stage) {
        // Title and counter
        lblTitle = new Label("TypingTutor");
        lblTitle.getStyleClass().add("title-label");

        lblCounter = new Label("1 of 6");
        lblCounter.getStyleClass().add("counter-label");

        // Text to type (read only)
        txtTarget = new TextField();
        txtTarget.setEditable(false);
        txtTarget.setFocusTraversable(false);
        txtTarget.getStyleClass().add("text-display");
        txtTarget.setPrefWidth(700);

        // User's typed text (filled by the key events, not by the user directly)
        txtTyped = new TextField();
        txtTyped.setEditable(false);
        txtTyped.setFocusTraversable(false);
        txtTyped.getStyleClass().add("text-display");
        txtTyped.setPrefWidth(700);

        // Key pressed display and statistics
        lblKeyCaption = new Label("Key pressed:");
        lblKeyPressed = new Label("-");
        lblKeyPressed.getStyleClass().add("status-ok");

        lblCorrect = new Label("Correct: 0");
        lblIncorrect = new Label("Incorrect: 0");
        lblCorrect.getStyleClass().add("stat-label");
        lblIncorrect.getStyleClass().add("stat-label");

        // Control buttons (not focusable so Space does not trigger them)
        btnNext = new Button("Next");
        btnReset = new Button("Reset");
        btnNext.setFocusTraversable(false);
        btnReset.setFocusTraversable(false);

        root = new BorderPane();

        // Layout of the top area
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

        // Virtual keyboard, built row by row
        VBox keyBoardBox = new VBox();
        keyBoardBox.setSpacing(5);
        keyBoardBox.setAlignment(Pos.CENTER);
        keyBoardBox.setPadding(new Insets(10));

        for (int r = 0; r < KEY_LABELS.length; r++) {
            keyBoardBox.getChildren().add(createKeyRow(KEY_LABELS[r], KEY_CODES[r]));
        }

        // Main layout
        root.setTop(topBox);
        root.setCenter(keyBoardBox);
        root.setPadding(new Insets(20));
        BorderPane.setMargin(keyBoardBox, new Insets(20, 0, 0, 0));

        // Initial text and button actions
        loadText(0);
        btnNext.setOnAction(e -> loadText(currentIndex + 1));
        btnReset.setOnAction(e -> resetApp());

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Keyboard events: filters catch every key, whatever control has the focus
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);
        scene.addEventFilter(KeyEvent.KEY_TYPED, this::handleKeyTyped);

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();

        root.requestFocus();
    }

    /**
     * Highlights the virtual key that matches the pressed key and shows its
     * value, or "Not handled" in red. Also handles Backspace.
     *
     * @param e the key event
     */
    private void handleKeyPressed(KeyEvent e) {
        KeyCode code = e.getCode();

        // Backspace removes the last typed character.
        // Counters are not decreased: they count keystrokes.
        if (code == KeyCode.BACK_SPACE && txtTyped.getLength() > 0) {
            String typed = txtTyped.getText();
            txtTyped.setText(typed.substring(0, typed.length() - 1));
            updateErrorIndicator();
        }

        Button key = keyMap.get(code);

        if (key != null) {
            // Check first to avoid duplicates while the key is held down
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

    /**
     * Returns the virtual key to its normal look when the key is released.
     *
     * @param e the key event
     */
    private void handleKeyReleased(KeyEvent e) {
        Button key = keyMap.get(e.getCode());

        if (key != null) {
            key.getStyleClass().remove("key-pressed");
        }
    }

    /**
     * Adds the typed character to the typed field and counts it as
     * correct or incorrect.
     *
     * @param e the key event
     */
    private void handleKeyTyped(KeyEvent e) {
        String ch = e.getCharacter();

        // Ignore control characters (Backspace, Enter, Esc, Delete...)
        if (ch.isEmpty() || ch.charAt(0) < 32 || ch.charAt(0) == 127) {
            return;
        }

        // Ignore input once the whole text is typed
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

    /**
     * Resets the application to its initial state: text 1, empty typed
     * field, counters at zero and no highlighted keys.
     */
    private void resetApp() {
        correctCount = 0;
        incorrectCount = 0;
        updateStats();

        lblKeyPressed.setText("-");
        lblKeyPressed.getStyleClass().removeAll("status-error", "status-ok");
        lblKeyPressed.getStyleClass().add("status-ok");

        for (Button key : keyMap.values()) {
            key.getStyleClass().remove("key-pressed");
        }

        loadText(0);

        root.requestFocus();
    }

    /**
     * Shows the sample text at the given index, clears the typed field
     * and updates the counter. Next is disabled on the last text.
     *
     * @param index position in SAMPLE_TEXTS (starts at 0)
     */
    private void loadText(int index) {
        currentIndex = index;

        txtTarget.setText(SAMPLE_TEXTS[index]);
        txtTyped.clear();
        txtTyped.getStyleClass().remove("typed-error");

        lblCounter.setText((index + 1) + " of " + SAMPLE_TEXTS.length);

        btnNext.setDisable(index == SAMPLE_TEXTS.length - 1);
    }

    /**
     * Updates the correct and incorrect keystroke labels.
     */
    private void updateStats() {
        lblCorrect.setText("Correct: " + correctCount);
        lblIncorrect.setText("Incorrect: " + incorrectCount);
    }

    /**
     * Shows a red border on the typed field when it does not match
     * the start of the target text.
     */
    private void updateErrorIndicator() {
        boolean hasError = !txtTarget.getText().startsWith(txtTyped.getText());

        txtTyped.getStyleClass().remove("typed-error");
        if (hasError) {
            txtTyped.getStyleClass().add("typed-error");
        }
    }

    /**
     * Creates one row of the virtual keyboard and registers each key
     * in keyMap.
     *
     * @param labels text of each key in the row
     * @param codes key code of each key, in the same order
     * @return the row of keys
     */
    private HBox createKeyRow(String[] labels, KeyCode[] codes) {
        HBox row = new HBox(5);
        row.setAlignment(Pos.CENTER);

        for (int i = 0; i < labels.length; i++) {
            Button key = new Button(labels[i]);
            key.getStyleClass().add("key");
            key.setFocusTraversable(false);
            keyMap.put(codes[i], key);

            // Wider keys
            switch (codes[i]) {
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

    /**
     * Launches the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
