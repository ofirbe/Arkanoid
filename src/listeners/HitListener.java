package listeners;
import sprites.Ball;
import sprites.Block;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit **the Block that hit**
     * @param hitter **the Ball that's doing the hitting**
     */
    void hitEvent(Block beingHit, Ball hitter);
}