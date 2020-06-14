package listeners;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the BlockRemover class.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
     * of the number of blocks that remain.
     * @param game **Game**
     * @param removedBlocks **Counter**
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the game.
     * @param beingHit **Block**
     * @param hitter **Ball**
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(game);
            this.remainingBlocks.decrease(1);
            beingHit.removeHitListener(this); // remove this listener from the block that is being removed from the
                                              // game.
        }
    }
}