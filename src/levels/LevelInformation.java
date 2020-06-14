package levels;
import java.util.List;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * return number of balls.
     * @return **int - the number of balls**
     */
    int numberOfBalls();
    /**
     * The initial velocity of each ball.
     * (initialBallVelocities().size() == numberOfBalls())
     * @return ** List- of velocitys**
     */
    List<Velocity> initialBallVelocities();
    /**
     * return the Speed of the paddle.
     * @return **int - speed**
     */
    int paddleSpeed();
    /**
     * return the width of the paddle.
     * @return **int - the paddle width**
     */
    int paddleWidth();
    /**
     * the level name will be displayed at the top of the screen.
     * @return **String- the name of the level**
     */
    String levelName();
    /**
     * Returns a sprite with the background of the level.
     * @return **Sprite- background**
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return **List- of the blocks **
     */
    List<Block> blocks();
    /**
     * Number of levels that should be removed.
     * @return **int- number of block to remove**
     */
    int numberOfBlocksToRemove();
}