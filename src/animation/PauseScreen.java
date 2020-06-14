package animation;
import biuoop.DrawSurface;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the PauseScreen class.
 */
public class PauseScreen implements Animation {
    private boolean stop;
    /**
     * constructoor of PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
    }
    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     * @param d **surface**
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.darkGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.white);
        d.drawText(d.getWidth() / 2 - 130, d.getHeight() / 2 - 50, "Paused." , 32);
        d.drawText(d.getWidth() / 2 - 230, d.getHeight() / 2 , "Press space to continue", 32);
    }
    /**
     * shouldStop() is in charge of stopping condition.
     * @return **boolean - should stop or not**
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
