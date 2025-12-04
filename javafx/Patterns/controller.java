import java.awt.event.*;
import java.awt.Component;

// The View acts as:
// 1. The Painter (Component)
// 2. The Model Watcher (LineDrawListener)
// 3. The Input Handler (MouseListener, MouseMotionListener) -> This is the CONTROLLER part
public class LineDrawView extends Component 
    implements LineDrawListener, MouseListener, MouseMotionListener {

    private LineDrawModel myModel;

    // --- STATE VARIABLES ---
    // These "remember" what is happening between millisecond-to-millisecond events.
    private boolean creatingLine = false; // Are we currently dragging?
    private int activeLineIdx = -1;       // Which line are we manipulating?
    private int startX, startY;           // Where did the click happen?

    public LineDrawView(LineDrawModel model) {
        this.myModel = model;
        
        // 1. Listen to the Model (so we know when to redraw)
        myModel.addListener(this);
        
        // 2. Listen to the Mouse (so we know when the user interacts)
        // We register 'this' class to receive the events.
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // --- CONTROLLER LOGIC (The State Machine) ---

    // EVENT 1: MOUSE PRESSED (Start the action)
    @Override
    public void mousePressed(MouseEvent evt) {
        // TRANSITION: Ready -> Preparing for Create
        creatingLine = true;
        
        // Capture context
        startX = evt.getX();
        startY = evt.getY();
        
        // ACTION: Add a "zero length" line to the model to start
        activeLineIdx = myModel.addLine(startX, startY, startX, startY);
    }

    // EVENT 2: MOUSE DRAGGED (Update the action)
    // Note: The text uses 'mouseMoved' with a flag, but 'mouseDragged' 
    // is the standard method for "movement while button is down".
    @Override
    public void mouseDragged(MouseEvent evt) {
        if (creatingLine) {
            // STATE: Moving / Dragging
            // We update the end coordinates of the line to match the mouse
            myModel.moveLine(
                activeLineIdx, 
                startX, startY,    // Start point stays the same
                evt.getX(), evt.getY() // End point follows mouse
            );
        }
    }

    // EVENT 3: MOUSE RELEASED (Finish the action)
    @Override
    public void mouseReleased(MouseEvent evt) {
        if (creatingLine) {
            // TRANSITION: Moving -> Ready
            creatingLine = false;
            
            // Final update to ensure the line ends exactly where released
            myModel.moveLine(activeLineIdx, startX, startY, evt.getX(), evt.getY());
        }
    }

    // --- UNUSED INTERFACE METHODS ---
    // We must implement these to satisfy the Interface contract, 
    // even if we don't use them.
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {} // distinct from Dragged
    
    // ... insert redraw() and Essential Geometry code from previous steps ...
}