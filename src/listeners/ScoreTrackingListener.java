package listeners;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * a ScoreTrackingListener is in charge of count the score of the player.
     * @param scoreCounter **Counter**
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * hitting a block is worth 5 points, and destroying a block is worth and additional 10 points.
     * @param beingHit **the Block that hit**
     * @param hitter **the Ball that's doing the hitting**
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        } else {
            this.currentScore.increase(5);
        }
    }
}