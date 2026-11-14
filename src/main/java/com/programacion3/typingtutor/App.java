package com.programacion3.typingtutor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    
    @Override
    public void start(Stage stage) {
        
    }

    public static void main(String[] args) {
        launch();
    }

}