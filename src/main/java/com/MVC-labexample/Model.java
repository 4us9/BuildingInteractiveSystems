package com.MVC-labexample;

import java.util.ArrayList;

public class Model {
    private ArrayList<Subscriber> subs;
    private ArrayList<Entity> entities;

    public Model() {
        subs = new ArrayList<Subscriber>();
        entities = new ArrayList<Entity>();
    }

    public void createEntity(double x, double y) {
        Entity e = new Entity(x,y);
        entities.add(e);
        notifySubscribers();
    }

    public void deleteEntity(Entity e) {
        entities.remove(e);
        notifySubscribers();
    }

    public void addSubscriber(Subscriber sub) {
        subs.add(sub);
    }

    private void notifySubscribers() {
        subs.forEach(sub -> sub.update(entities));
    }

    public boolean contains(double x, double y) {
        for (Entity entity : entities) {
            if (entity.contains(x, y)) return true;
        }
        return false;
    }

    public Entity whichEntity(double x, double y) {
        for (Entity entity : entities) {
            if (entity.contains(x, y)) return entity;
        }
        return null;
    }

}
