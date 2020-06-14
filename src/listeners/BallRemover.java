package listeners;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the BallRemover class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * a BallRemover is in charge of removing balls from the game, as well as keeping count
     * of the number of balls that remain.
     * @param game **Game**
     * @param removedBalls **Counter**
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    /**
     * Balls that fall out of the screen should be removed
     * Whenever the Ball hit the "death-region", the BallRemover will remove the ball from the game.
     * @param beingHit **Block**
     * @param hitter **Ball**
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}
