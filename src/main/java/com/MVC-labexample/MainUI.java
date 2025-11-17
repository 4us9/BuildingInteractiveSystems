package com.MVC-labexample;

import javafx.scene.layout.Region;

public class MainUI extends Region {
    public MainUI()
    {
        // set up MVC architecture
        Controller controller = new Controller();
        View view = new View();
        Model model = new Model();

        view.setupEvents(controller);
        controller.setModel(model);
        model.addSubscriber(view);

        this.getChildren().add(view);
    }
    
}
