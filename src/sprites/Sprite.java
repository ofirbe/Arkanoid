package sprites;
import biuoop.DrawSurface;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d **DrawSurface object**
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}