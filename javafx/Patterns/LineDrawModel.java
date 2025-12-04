import java.util.ArrayList;
import java.util.List;

// 1. THE CONTRACT
// Any View that wants to watch the Model must implement this.
interface LineDrawListener {
    // Called BEFORE the change happens.
    // Allows the View to see the OLD line (in model) and NEW coords (in args).
    void lineWillChange(int index, int newX1, int newY1, int newX2, int newY2);

    // Called for major changes (like delete) where tracking specific lines is too hard.
    void modelHasChanged();
}

// 2. THE DATA OBJECT
class Line {
    int x1, y1, x2, y2;
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
    }
}

// 3. THE MODEL
public class LineDrawModel {
    // Private data: The Controller cannot touch this directly.
    private ArrayList<Line> linesToDraw = new ArrayList<>();
    
    // The "Phone Book": A list of everyone watching this model.
    private ArrayList<LineDrawListener> listeners = new ArrayList<>();

    // --- REGISTRATION METHODS ---
    
    public void addListener(LineDrawListener newListener) {
        listeners.add(newListener);
    }

    public void removeListener(LineDrawListener listener) {
        listeners.remove(listener);
    }

    // --- MANIPULATION METHODS (Called by Controller) ---

    public void addLine(int x1, int y1, int x2, int y2) {
        // Pre-notification: Tell listeners a line is coming at the end of the list
        notifyLineWillChange(linesToDraw.size(), x1, y1, x2, y2);
        
        // actual data change
        linesToDraw.add(new Line(x1, y1, x2, y2));
    }

    public void moveLine(int index, int newX1, int newY1, int newX2, int newY2) {
        // Pre-notification is CRITICAL here.
        // The View uses this moment to erase the line at the OLD position 
        // (which is still currently stored in 'linesToDraw').
        notifyLineWillChange(index, newX1, newY1, newX2, newY2);

        // Now we actually update the data
        Line line = linesToDraw.get(index);
        line.x1 = newX1; line.y1 = newY1; 
        line.x2 = newX2; line.y2 = newY2;
    }

    public void deleteLine(int index) {
        // actual data change
        linesToDraw.remove(index);
        
        // Post-notification: Indexes have shifted, so we just tell the View 
        // to refresh the whole "Scene".
        notifyModelHasChanged();
    }

    // --- HELPER METHODS (Internal use only) ---

    // Loops through the "Phone Book" and calls lineWillChange on everyone
    private void notifyLineWillChange(int index, int x1, int y1, int x2, int y2) {
        for (LineDrawListener listener : listeners) {
            listener.lineWillChange(index, x1, y1, x2, y2);
        }
    }

    // Loops through the "Phone Book" and calls modelHasChanged on everyone
    private void notifyModelHasChanged() {
        for (LineDrawListener listener : listeners) {
            listener.modelHasChanged();
        }
    }
    
    // Accessor for the View to read data when redrawing
    public Line getLine(int index) {
        return linesToDraw.get(index);
    }
}