package com.MVC-labexample;

public class IModel {
    Entity selected;

    public IModel() {
        selected = null;
    }

    public void select(Entity e) {
        selected = e;
    }

    public Entity getSelected() {
        return selected;
    }

    public void unselect() {
        selected = null;
    }
}
