package tasks;
import biuoop.GUI;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the ExitTask class.
 */
public class ExitTask implements Task<Void> {
    private GUI gui;
    /**
     * constuctor.
     * @param g **GUI object**
     */
    public ExitTask(GUI g) {
        this.gui = g;
    }
    /**
     * close the gui screen.
     * @return **null**
     */
    public Void run() {
        this.gui.close();
        return null;
    }
}