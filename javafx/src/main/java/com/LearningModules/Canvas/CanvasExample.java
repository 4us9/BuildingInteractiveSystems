//Import the JavaFX classes we'll use for this app
package com.LearningModules.Canvas;

import javafx.application.Application;      // Base class for JavaFX apps
import javafx.scene.Scene;                 // Represents the "window contents"
import javafx.scene.canvas.Canvas;         // The drawing area
import javafx.scene.canvas.GraphicsContext;// The tool we use to draw on the canvas
import javafx.scene.layout.VBox;           // A vertical layout container
import javafx.scene.paint.Color;           // Represents colors for drawing
import javafx.stage.Stage;                 // The top-level application window

// Every JavaFX app must extend Application
public class CanvasExample extends Application {

    // Entry point of the program
    public static void main(String[] args) {
        launch(args);  // This calls the JavaFX runtime and eventually runs start()
    }

    // This method is automatically called when the app launches
    @Override
    public void start(Stage primaryStage) {

        // 🧱 STEP 1: Create a Canvas (the area we’ll draw on)
        // Think of Canvas like a blank piece of paper — nothing is drawn by default.
        Canvas canvas = new Canvas();

        // Set the canvas dimensions (width and height in pixels)
        canvas.setHeight(512);
        canvas.setWidth(512);

        // 🖌️ STEP 2: Get the GraphicsContext — your drawing tool
        // GraphicsContext gives you methods like fillRect(), strokeLine(), etc.
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();

        // 🟥 STEP 3: Draw a filled red rectangle
        // setFill() defines the *fill color* used by fillRect() and other fill operations
        graphicsContext2D.setFill(Color.valueOf("#ff0000")); // red color
        // Draw a rectangle starting at (100, 100), width=200, height=200
        graphicsContext2D.fillRect(100, 100, 200, 200);

        // 🟦 STEP 4: Draw a blue rectangle outline (not filled)
        // setStroke() defines the *stroke color* used for lines and borders
        graphicsContext2D.setStroke(Color.valueOf("#0000ff")); // blue color
        // Draw only the border of a rectangle starting at (200, 200)
        graphicsContext2D.strokeRect(200, 200, 200, 200);

        // 📦 STEP 5: Put the canvas inside a VBox layout container
        // Layout containers help you position and organize UI elements.
        VBox vbox = new VBox(canvas);

        // 🎨 STEP 6: Create a Scene and attach it to the stage (the app window)
        Scene scene = new Scene(vbox);

        // 🔲 STEP 7: Attach the scene to the main window (Stage)
        primaryStage.setScene(scene);

        // 🪟 STEP 8: Show the window
        primaryStage.setTitle("JavaFX Canvas Example");
        primaryStage.show();
    }
}
