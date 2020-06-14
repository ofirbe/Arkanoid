package levels;
import java.util.ArrayList;
import java.util.List;
import game.GameLevel;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
import sprites.Background1;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the DirectHit class.
 */
public class DirectHit implements LevelInformation {
    private final int winWidth = 800; // windows size
    private final int winHight = 600;
    private int numberOfBalls = 1;
    private double ballSpeed = 7;
    private final Velocity ballVel = new Velocity(0, 0).fromAngleAndSpeed(0, ballSpeed);
    private int paddleSpeed = 3;
    private int paddleWidth = 80;
    private String levelname = "Direct Hit";
    private Sprite getBackground = new Background1(levelname);
    private int numberOfBlocksToRemove = 1;
    private int radios = 5;
    /**
     * return the number of balls.
     * @return **the number of balls**
     */
    public int numberOfBalls() {
        return numberOfBalls;
    }
    /**
     * return the velocities of balls.
     * @return **List of the balls velocities**
     */
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<Velocity>();
        list.add(ballVel);
        return list;
    }
    /**
     * return the spped of the Paddle.
     * @return **the paddleSpeed**
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * return the width of the Paddle.
     * @return **the paddleWidth**
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * return the name of this level.
     * @return **String- the level name**
     */
    public String levelName() {
        return this.levelname;
    }
    /**
     * return the Background of this level.
     * @return **Sprite object**
     */
    public Sprite getBackground() {
        return getBackground;
    }
    /**
     * return list of the Blocks at this level.
     * @return **List of blocks**
     */
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<Block>();
        Block b1 = new Block(this.winWidth / 2 - 10, winHight / 3 - 10, 20, 20, java.awt.Color.black, radios);
        b1.addClr(-1, java.awt.Color.red);
        b1.setMaxHit(1);
        list.add(b1);
        return list;
    }
    /**
     * Number of levels that should be removed.
     * @return **int- number of block to remove**
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
    /**
     * remove Sprites from the game.
     * @param theGame **GameLevel object**
     */
    public void removeFromGame(GameLevel theGame) {
        theGame.removeSprite(this.getBackground);
    }
}
