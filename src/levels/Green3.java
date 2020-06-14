package levels;
import java.util.ArrayList;
import java.util.List;
import game.GameLevel;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
import sprites.Background3;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Green3 class.
 */
public class Green3 implements LevelInformation {
    private final int winWidth = 800; // windows size
    private int numberOfBalls = 2;
    private double ballSpeed = 7;
    private Velocity ballVel = new Velocity(0, 0).fromAngleAndSpeed(0, ballSpeed);
    private int paddleSpeed = 8;
    private int paddleWidth = 80;
    private String levelname = "Green 3";
    private Sprite getBackground = new Background3(levelname);
    private int numberOfBlocksToRemove = 57;
    private int radios = 5;
    private final int numOfBlockRow = 6; // number of rows
    private final int numOfBlocks = 12; // number of blocks at the first row
    private final double widthOfBlock = 35;
    private final double highOfBlock = 23;
    private final double upperX = winWidth - 20 - (numOfBlocks * widthOfBlock); // first block(top left point)
    private final double upperY = 70; // The y coordination of the first block(top left point)
    private final java.awt.Color[] color = {java.awt.Color.GRAY, java.awt.Color.RED, java.awt.Color.YELLOW,
            java.awt.Color.BLUE, java.awt.Color.PINK, java.awt.Color.GREEN };
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
        for (int i = 0; i < 2; i++) {
            ballVel = new Velocity(0, 0).fromAngleAndSpeed(-45 + (90 * i), ballSpeed);
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
        double plusX = 0;
        double plusY = 0;
        int nOb = numOfBlocks; // number of blocks at every row
        ArrayList<Block> list = new ArrayList<Block>();
        for (int j = 0; j < numOfBlockRow; j++) {
            nOb = numOfBlocks - j;
            plusX = (plusX + (widthOfBlock * j));
            for (int i = 0; i < nOb; i++) {
                Block block = new Block(upperX + plusX, upperY + plusY, widthOfBlock, highOfBlock, java.awt.Color.black,
                        radios);
                block.addClr(-1, color[j % 6]);
                list.add(block);
                if (j == 0) { // max of hits is 2 at the first row
                    block.setMaxHit(2);
                } else { // for the rest of the block the max of hits is 1
                    block.setMaxHit(1);
                }
                plusX += widthOfBlock;
            }
            plusX = 0;
            plusY += highOfBlock;
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
