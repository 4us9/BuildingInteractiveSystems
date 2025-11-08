package com.mvc_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.control.*;

public class App2 extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        VBox root = new VBox();

        TextField tf = new TextField();

        Button btn = new Button("Click Me");

        root.getChildren().addAll(tf, btn);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("MVC Demo");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
