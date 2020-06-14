package sprites;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the SpriteCollection class.
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * SpriteCollection object constructor..
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * add new Sprite object to the ArrayList.
     * @param s **Sprite object**
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * remove Sprite object from the ArrayList.
     * @param s **Sprite object**
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spirteCopy = new ArrayList<Sprite>(this.sprites); // to avoid problems during run time.
        if (sprites.isEmpty()) {
            return;
        }
        for (Sprite s : spirteCopy) {
            s.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d **DrawSurface object**
     */
    public void drawAllOn(DrawSurface d) {
        if (sprites.isEmpty()) {
            return;
        }
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}