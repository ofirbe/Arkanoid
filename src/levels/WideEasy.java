package levels;
import java.util.ArrayList;
import java.util.List;
import game.GameLevel;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
import sprites.Background2;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the WideEasy class.
 */
public class WideEasy implements LevelInformation {
    private int numberOfBalls = 10;
    private double ballSpeed = 7;
    private Velocity ballVel = new Velocity(0, 0).fromAngleAndSpeed(0, ballSpeed);
    private int paddleSpeed = 8;
    private int paddleWidth = 500;
    private String levelname = "WideEasy";
    private Sprite getBackground = new Background2(levelname);
    private int numberOfBlocksToRemove = 15;
    private int radios = 5;
    private final double upperX = 20; // first block(top left point)
    private final double upperY = 200; // The y coordination of the first block(top left point)
    private final java.awt.Color[] color = {java.awt.Color.YELLOW, java.awt.Color.getHSBColor(0.9f, 80f, 0.9f),
            java.awt.Color.getHSBColor(0.28f, 62f, 0.4f) };
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
        for (int i = 0; i < 10; i++) {
            ballVel = new Velocity(0, 0).fromAngleAndSpeed(-45 + (10 * i), ballSpeed);
            list.add(ballVel);
        }
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
        double plusX = 0;
        for (int i = 0; i < 15; i++) {
            Block block = new Block(upperX + plusX, upperY, 50.66, 20, java.awt.Color.black, radios);
            block.addClr(-1, color[i % 3]);
            block.setMaxHit(1);
            plusX += 50.66;
            list.add(block);
        }
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
