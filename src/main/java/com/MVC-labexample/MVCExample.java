package com.MVC-labexample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MVCExample extends Application {

    public void start(Stage stage) throws IOException {
        MainUI root = new MainUI();
        Scene s = new Scene(root);
        stage.setScene(s);
        stage.show();
    }
}
