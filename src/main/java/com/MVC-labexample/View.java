package com.MVC-labexample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;

public class View extends StackPane implements Subscriber {
    private Canvas myCanvas;
    private GraphicsContext gc;

    public View() {
        myCanvas = new Canvas(500, 500);
        gc = myCanvas.getGraphicsContext2D();
        this.getChildren().add(myCanvas);
    }

    public void setupEvents(Controller controller) {
        setOnMousePressed(controller::handleMousePressed);
    }

    private void draw(List<Entity> entities) {
        gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        gc.setFill(Color.INDIGO);
        entities.forEach(entity -> {
            gc.fillOval(entity.getX() - 10, entity.getY() - 10, 20, 20);
        });
    }

    @Override
    public void update(List<Entity> entities) {
        draw(entities);
    }
}
