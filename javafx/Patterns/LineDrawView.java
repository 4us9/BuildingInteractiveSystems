import java.awt.Graphics;
import java.awt.Rectangle;

// 1. EXTENDS WIDGET: It's a UI component.
// 2. IMPLEMENTS LISTENER: It has the "phone" to listen to the Model.
public class LineDrawView extends Widget implements LineDrawListener {
    
    private LineDrawModel myModel;

    // --- CONSTRUCTOR ---
    public LineDrawView(LineDrawModel model) {
        this.myModel = model;
        
        // CRITICAL: The View registers itself with the Model.
        // "Hey Model, call me if anything changes."
        myModel.addListener(this);
    }

    // --- THE PAINTER ---
    // This is called by the Window System (OS), NOT by your code directly.
    // It is called only when the OS determines the screen needs refreshing.
    public void redraw(Graphics g) {
        // Loop through the model and paint every line
        for (int i = 0; i < myModel.nLines(); i++) {
            Line line = myModel.getLine(i);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }

    // --- NOTIFICATION HANDLER 1: PRECISE UPDATE ---
    // Called by Model when a single line moves/changes.
    public void lineWillChange(int index, int newX1, int newY1, int newX2, int newY2) {
        
        // A. HANDLE THE OLD POSITION
        // We look at the model to find where the line IS right now (before change).
        Line line = myModel.getLine(index);
        
        if (line != null) {
            // Create a rectangle around the OLD line.
            // (width/height + 1 is a common graphics trick to catch edge pixels)
            Rectangle oldRegion = new Rectangle(
                line.x1, line.y1, 
                (line.x2 - line.x1) + 1, (line.y2 - line.y1) + 1
            );
            
            // Tell OS: "Please erase this old spot later."
            this.damage(oldRegion);
        }

        // B. HANDLE THE NEW POSITION
        // Create a rectangle around where the line WILL BE.
        Rectangle newRegion = new Rectangle(
            newX1, newY1, 
            (newX2 - newX1) + 1, (newY2 - newY1) + 1
        );
        
        // Tell OS: "Please paint this new spot later."
        this.damage(newRegion);
    }

    // --- NOTIFICATION HANDLER 2: NUCLEAR OPTION ---
    // Called by Model when something big happens (like a delete).
    public void modelHasChanged() {
        // We don't try to calculate specific rectangles.
        // Just tell the OS the ENTIRE widget is dirty.
        this.damage(); 
    }
}