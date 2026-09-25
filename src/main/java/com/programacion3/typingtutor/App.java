package com.programacion3.typingtutor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        VBox top = new VBox();
        Label title = new Label("Typing tutor");
        
        top.getChildren().add(title);
        
        VBox center = new VBox();
        center.setAlignment(Pos.TOP_CENTER);
        
        TextToType textToType = new TextToType();
        VirtualKeyBoard virtualKeyBoard = new VirtualKeyBoard();
        Output output = new Output();
         
        center.getChildren().add(textToType);
        center.getChildren().add(virtualKeyBoard);
        center.getChildren().add(output);
        
        root.setTop(top);
        root.setCenter(center);
        
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 640, 480);
        String css = getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        scene.setOnKeyPressed(event -> {
            virtualKeyBoard.keyPressed(event.getCode());
            textToType.keyPressed(event.getCode());
            output.keyPressed(event.getCode());
        });
        
        scene.setOnKeyTyped(event -> {
            textToType.keyTyped(event.getCharacter());
            output.keyTyped(event.getCharacter());
        });

        
        scene.setOnKeyReleased(event -> {
            virtualKeyBoard.keyReleased(event.getCode());
        }); 
        
        
        
        
        stage.setScene(scene);
        stage.show();
    }   

    public static void main(String[] args) {
        launch();
    }
}