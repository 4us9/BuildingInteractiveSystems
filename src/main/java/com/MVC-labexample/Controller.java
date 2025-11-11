package com.MVC-labexample;

import javafx.scene.input.MouseEvent;

public class Controller {
    private Model model;

    public void setModel(Model m) {
        model = m;
    }

    public void handleMousePressed(MouseEvent event) {
        if (model.contains(event.getX(), event.getY())) {
            Entity e = model.whichEntity(event.getX(), event.getY());
            model.deleteEntity(e);
        } else {
            model.createEntity(event.getX(), event.getY());
        }
    }
}

